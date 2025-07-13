package org.caesar.media.service.aidanmu;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.caesar.media.config.AiDanmuProperties;
import org.caesar.media.dto.AiDanmuStatusVO;
import org.caesar.media.entity.PlatformUser;
import org.caesar.media.enums.AiDanmuStatus;
import org.caesar.media.enums.ErrorCode;
import org.caesar.media.exception.BusinessException;
import org.caesar.media.service.DouyinService;
import org.caesar.media.service.PlatformUserService;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * AI弹幕管理服务
 * 管理所有用户的AI弹幕状态和执行
 *
 * @author peng.guo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AiDanmuManagerService {

    private final AiDanmuProperties aiDanmuProperties;
    private final AiDanmuGenerateService aiDanmuGenerateService;
    private final DouyinService douyinService;
    private final PlatformUserService platformUserService;

    /**
     * 用户AI弹幕状态映射 key: userId, value: AiDanmuClientState
     */
    private final Map<Long, AiDanmuClientState> clientStates = new ConcurrentHashMap<>();

    /**
     * 定时任务调度器
     */
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(
        10, r -> {
            Thread thread = new Thread(r, "ai-danmu-" + System.currentTimeMillis());
            thread.setDaemon(true);
            return thread;
        }
    );

    /**
     * 启动AI弹幕
     */
    public void startAiDanmu(Long userId, Long roomId, String roomDescription,
                            Integer randomSeconds, String aiPersonality) {

        // 验证用户是否存在
        PlatformUser user = platformUserService.getById(userId);
        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }

        // 检查并发限制
        long runningCount = clientStates.values().stream()
            .mapToLong(state -> state.isRunning() ? 1 : 0)
            .sum();

        if (runningCount >= aiDanmuProperties.getMaxConcurrentUsers()) {
            throw new BusinessException(ErrorCode.INVALID_PARAMETERS, "已达到最大并发用户数限制");
        }

        // 获取或创建客户端状态
        AiDanmuClientState clientState = clientStates.computeIfAbsent(userId, AiDanmuClientState::new);

        // 如果已经在运行，先停止
        if (clientState.isRunning()) {
            stopAiDanmu(userId);
        }

        // 启动AI弹幕
        clientState.start(roomId,
            roomDescription != null ? roomDescription : aiDanmuProperties.getDefaultConfig().getRoomDescription(),
            randomSeconds != null ? randomSeconds : aiDanmuProperties.getDefaultConfig().getRandomSeconds(),
            aiPersonality != null ? aiPersonality : aiDanmuProperties.getDefaultConfig().getAiPersonality());

        // 调度执行任务
        ScheduledFuture<?> scheduledTask = scheduler.scheduleWithFixedDelay(
            () -> executeDanmuCycle(clientState),
            0, 1, TimeUnit.SECONDS
        );

        clientState.setScheduledTask(scheduledTask);
        clientState.setRunning();

        log.info("用户{}的AI弹幕已启动，直播间:{}", userId, roomId);
    }

    /**
     * 停止AI弹幕
     */
    public void stopAiDanmu(Long userId) {
        AiDanmuClientState clientState = clientStates.get(userId);
        if (clientState != null) {
            clientState.stop();
            log.info("用户{}的AI弹幕已停止", userId);
        }
    }

    /**
     * 更新AI弹幕配置
     */
    public void updateConfig(Long userId, String roomDescription, Integer randomSeconds, String aiPersonality) {
        AiDanmuClientState clientState = clientStates.get(userId);
        if (clientState != null) {
            clientState.updateConfig(roomDescription, randomSeconds, aiPersonality);
            log.info("用户{}的AI弹幕配置已更新", userId);
        } else {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, "用户AI弹幕未启动");
        }
    }

    /**
     * 获取用户AI弹幕状态
     */
    public AiDanmuStatusVO getUserStatus(Long userId) {
        AiDanmuClientState clientState = clientStates.get(userId);
        PlatformUser user = platformUserService.getById(userId);

        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }

        AiDanmuStatusVO statusVO = new AiDanmuStatusVO();
        statusVO.setUserId(userId);
        // 使用name字段作为昵称
        statusVO.setNickname(user.getName());
        // PlatformUser没有avatar字段
        statusVO.setAvatar(null);
        // 根据status判断是否在线
        statusVO.setOnline(user.getStatus() == 1);

        if (clientState != null) {
            statusVO.setStatus(clientState.getStatus());
            statusVO.setRoomId(clientState.getRoomId());
            statusVO.setRoomDescription(clientState.getRoomDescription());
            statusVO.setRandomSeconds(clientState.getRandomSeconds());
            statusVO.setAiPersonality(clientState.getAiPersonality());
            statusVO.setSentCount(clientState.getSentCount().get());
            statusVO.setLastSentTime(clientState.getLastSentTime());
            statusVO.setLastSentContent(clientState.getLastSentContent());
            statusVO.setStartTime(clientState.getStartTime());
            statusVO.setRunDurationMinutes(clientState.getRunDurationMinutes());
            statusVO.setErrorMessage(clientState.getErrorMessage());
        } else {
            statusVO.setStatus(AiDanmuStatus.STOPPED);
            statusVO.setSentCount(0L);
        }

        return statusVO;
    }

    /**
     * 获取所有用户的AI弹幕状态
     */
    public List<AiDanmuStatusVO> getAllStatus() {
        List<PlatformUser> allUsers = platformUserService.list();

        return allUsers.stream()
            .map(user -> {
                try {
                    return getUserStatus(user.getId());
                } catch (Exception e) {
                    log.warn("获取用户{}状态失败", user.getId(), e);
                    return null;
                }
            })
            .filter(status -> status != null)
            .collect(Collectors.toList());
    }

    /**
     * 执行弹幕发送循环
     */
    private void executeDanmuCycle(AiDanmuClientState clientState) {
        if (!clientState.isRunning()) {
            return;
        }

        try {
            // 1. 随机等待
            int waitTime = clientState.generateRandomWaitTime();
            Thread.sleep(waitTime * 1000);

            // 检查状态是否仍在运行
            if (!clientState.isRunning()) {
                return;
            }

            // 2. 生成弹幕内容
            String danmuContent = aiDanmuGenerateService.generateDanmu(
                clientState.getRoomDescription(),
                clientState.getAiPersonality()
            );

            // 3. 内容过滤
            danmuContent = filterContent(danmuContent, clientState);
            if (danmuContent == null) {
                return; // 内容被过滤，跳过本次发送
            }

            // 4. 发送弹幕
            douyinService.sendMsg(clientState.getRoomId(), clientState.getUserId(), danmuContent);

            // 5. 记录发送
            clientState.recordSent(danmuContent);

            log.debug("用户{}发送AI弹幕: {}", clientState.getUserId(), danmuContent);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.info("用户{}的AI弹幕任务被中断", clientState.getUserId());
        } catch (Exception e) {
            log.error("用户{}的AI弹幕执行失败", clientState.getUserId(), e);
            clientState.setError("弹幕发送失败: " + e.getMessage());
        }
    }

    /**
     * 内容过滤
     */
    private String filterContent(String content, AiDanmuClientState clientState) {
        if (content == null || content.trim().isEmpty()) {
            return null;
        }

        AiDanmuProperties.ContentFilter filter = aiDanmuProperties.getContentFilter();
        if (!filter.isEnabled()) {
            return content;
        }

        // 长度检查
        if (content.length() < filter.getMinLength() || content.length() > filter.getMaxLength()) {
            return null;
        }

        // 敏感词检查
        for (String sensitiveWord : filter.getSensitiveWords()) {
            if (content.contains(sensitiveWord)) {
                return null;
            }
        }

        // 重复内容检查
        if (filter.isFilterDuplicate() && clientState.isDuplicateContent(content)) {
            return null;
        }

        return content;
    }

    /**
     * 批量启动AI弹幕
     */
    public Map<Long, Boolean> batchStart(List<Long> userIds, Long roomId, String roomDescription,
                                        Integer randomSeconds, String aiPersonality) {
        Map<Long, Boolean> results = new ConcurrentHashMap<>();

        userIds.parallelStream().forEach(userId -> {
            try {
                startAiDanmu(userId, roomId, roomDescription, randomSeconds, aiPersonality);
                results.put(userId, true);
            } catch (Exception e) {
                log.error("批量启动用户{}的AI弹幕失败", userId, e);
                results.put(userId, false);
            }
        });

        return results;
    }

    /**
     * 批量停止AI弹幕
     */
    public Map<Long, Boolean> batchStop(List<Long> userIds) {
        Map<Long, Boolean> results = new ConcurrentHashMap<>();

        userIds.parallelStream().forEach(userId -> {
            try {
                stopAiDanmu(userId);
                results.put(userId, true);
            } catch (Exception e) {
                log.error("批量停止用户{}的AI弹幕失败", userId, e);
                results.put(userId, false);
            }
        });

        return results;
    }

    /**
     * 应用关闭时清理资源
     */
    @PreDestroy
    public void shutdown() {
        log.info("AI弹幕管理服务正在关闭...");

        // 停止所有AI弹幕任务
        clientStates.values().parallelStream().forEach(AiDanmuClientState::stop);

        // 关闭调度器
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(10, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }

        log.info("AI弹幕管理服务已关闭");
    }
}
