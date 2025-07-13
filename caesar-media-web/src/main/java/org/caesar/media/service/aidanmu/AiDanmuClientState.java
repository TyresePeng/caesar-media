package org.caesar.media.service.aidanmu;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.caesar.media.enums.AiDanmuStatus;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

/**
 * AI弹幕客户端状态
 * 每个用户对应一个客户端状态实例
 * 
 * @author peng.guo
 */
@Slf4j
@Data
public class AiDanmuClientState {
    
    /**
     * 用户ID
     */
    private final Long userId;
    
    /**
     * 当前状态
     */
    private volatile AiDanmuStatus status = AiDanmuStatus.STOPPED;
    
    /**
     * 当前直播间ID
     */
    private volatile Long roomId;
    
    /**
     * 直播间描述
     */
    private volatile String roomDescription;
    
    /**
     * 随机等待秒数上限
     */
    private volatile int randomSeconds = 2;
    
    /**
     * AI人格设定
     */
    private volatile String aiPersonality = "热情的观众";
    
    /**
     * 已发送弹幕数量
     */
    private final AtomicLong sentCount = new AtomicLong(0);
    
    /**
     * 最后发送时间
     */
    private volatile LocalDateTime lastSentTime;
    
    /**
     * 最后发送内容
     */
    private volatile String lastSentContent;
    
    /**
     * 启动时间
     */
    private volatile LocalDateTime startTime;
    
    /**
     * 错误消息
     */
    private volatile String errorMessage;
    
    /**
     * 定时任务引用
     */
    private volatile ScheduledFuture<?> scheduledTask;
    
    /**
     * 弹幕历史记录（用于重复检测）
     */
    private final java.util.concurrent.ConcurrentLinkedQueue<String> danmuHistory = 
        new java.util.concurrent.ConcurrentLinkedQueue<>();
    
    public AiDanmuClientState(Long userId) {
        this.userId = userId;
    }
    
    /**
     * 更新配置
     */
    public void updateConfig(String roomDescription, Integer randomSeconds, String aiPersonality) {
        if (roomDescription != null) {
            this.roomDescription = roomDescription;
        }
        if (randomSeconds != null) {
            this.randomSeconds = randomSeconds;
        }
        if (aiPersonality != null) {
            this.aiPersonality = aiPersonality;
        }
        log.info("用户{}的AI弹幕配置已更新", userId);
    }
    
    /**
     * 启动AI弹幕
     */
    public void start(Long roomId, String roomDescription, Integer randomSeconds, String aiPersonality) {
        this.roomId = roomId;
        this.roomDescription = roomDescription;
        this.randomSeconds = randomSeconds;
        this.aiPersonality = aiPersonality;
        this.status = AiDanmuStatus.STARTING;
        this.startTime = LocalDateTime.now();
        this.errorMessage = null;
        this.sentCount.set(0);
        this.danmuHistory.clear();
        
        log.info("用户{}的AI弹幕已启动，直播间:{}", userId, roomId);
    }
    
    /**
     * 停止AI弹幕
     */
    public void stop() {
        this.status = AiDanmuStatus.STOPPED;
        if (scheduledTask != null && !scheduledTask.isCancelled()) {
            scheduledTask.cancel(false);
        }
        log.info("用户{}的AI弹幕已停止", userId);
    }
    
    /**
     * 设置为运行状态
     */
    public void setRunning() {
        this.status = AiDanmuStatus.RUNNING;
    }
    
    /**
     * 设置错误状态
     */
    public void setError(String errorMessage) {
        this.status = AiDanmuStatus.ERROR;
        this.errorMessage = errorMessage;
        log.error("用户{}的AI弹幕出现错误: {}", userId, errorMessage);
    }
    
    /**
     * 记录弹幕发送
     */
    public void recordSent(String content) {
        this.sentCount.incrementAndGet();
        this.lastSentTime = LocalDateTime.now();
        this.lastSentContent = content;
        
        // 记录到历史中，用于重复检测
        danmuHistory.offer(content);
        // 保持历史记录数量限制
        while (danmuHistory.size() > 10) {
            danmuHistory.poll();
        }
    }
    
    /**
     * 生成随机等待时间（秒）
     */
    public int generateRandomWaitTime() {
        return ThreadLocalRandom.current().nextInt(1, randomSeconds + 1);
    }
    
    /**
     * 检查内容是否重复
     */
    public boolean isDuplicateContent(String content) {
        return danmuHistory.contains(content);
    }
    
    /**
     * 获取运行时长（分钟）
     */
    public Long getRunDurationMinutes() {
        if (startTime == null) {
            return 0L;
        }
        return ChronoUnit.MINUTES.between(startTime, LocalDateTime.now());
    }
    
    /**
     * 是否正在运行
     */
    public boolean isRunning() {
        return status == AiDanmuStatus.RUNNING || status == AiDanmuStatus.STARTING;
    }
    
    /**
     * 设置定时任务引用
     */
    public void setScheduledTask(ScheduledFuture<?> scheduledTask) {
        this.scheduledTask = scheduledTask;
    }
}