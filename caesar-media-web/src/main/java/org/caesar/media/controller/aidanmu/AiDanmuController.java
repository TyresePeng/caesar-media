package org.caesar.media.controller.aidanmu;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.caesar.media.common.ApiResponse;
import org.caesar.media.dto.*;
import org.caesar.media.service.aidanmu.AiDanmuManagerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AI弹幕控制器
 *
 * @author peng.guo
 */
@RestController
@RequestMapping("/ai-danmu")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AiDanmuController {

    private final AiDanmuManagerService aiDanmuManagerService;

    /**
     * 启动AI弹幕
     */
    @PostMapping("/start")
    public ApiResponse<Void> startAiDanmu(@Valid @RequestBody AiDanmuStartParam param) {
        aiDanmuManagerService.startAiDanmu(
            param.getUserId(),
            param.getRoomId(),
            param.getRoomDescription(),
            param.getRandomSeconds(),
            param.getAiPersonality()
        );
        return ApiResponse.success();
    }

    /**
     * 停止AI弹幕
     */
    @PostMapping("/stop")
    public ApiResponse<Void> stopAiDanmu(@Valid @RequestBody AiDanmuStopParam param) {
        aiDanmuManagerService.stopAiDanmu(param.getUserId());
        return ApiResponse.success();
    }

    /**
     * 更新AI弹幕配置（立即生效）
     */
    @PutMapping("/config")
    public ApiResponse<Void> updateConfig(@Valid @RequestBody AiDanmuConfigParam param) {
        aiDanmuManagerService.updateConfig(
            param.getUserId(),
            param.getRoomDescription(),
            param.getRandomSeconds(),
            param.getAiPersonality()
        );
        return ApiResponse.success();
    }

    /**
     * 获取单个用户的AI弹幕状态
     */
    @GetMapping("/status/{userId}")
    public ApiResponse<AiDanmuStatusVO> getUserStatus(@PathVariable @NotNull Long userId) {
        AiDanmuStatusVO status = aiDanmuManagerService.getUserStatus(userId);
        return ApiResponse.success(status);
    }

    /**
     * 获取所有用户的AI弹幕状态
     */
    @GetMapping("/status/all")
    public ApiResponse<List<AiDanmuStatusVO>> getAllStatus() {
        List<AiDanmuStatusVO> statusList = aiDanmuManagerService.getAllStatus();
        return ApiResponse.success(statusList);
    }

    /**
     * 批量启动AI弹幕
     */
    @PostMapping("/batch/start")
    public ApiResponse<Map<String, Object>> batchStart(@Valid @RequestBody AiDanmuBatchParam param) {
        Map<Long, Boolean> results = aiDanmuManagerService.batchStart(
            param.getUserIds(),
            param.getRoomId(),
            param.getRoomDescription(),
            param.getRandomSeconds(),
            param.getAiPersonality()
        );

        // 统计结果
        long successCount = results.values().stream().mapToLong(success -> success ? 1 : 0).sum();
        long failCount = results.size() - successCount;

        Map<String, Object> response = new HashMap<>();
        response.put("successCount", successCount);
        response.put("failCount", failCount);
        response.put("details", results);

        return ApiResponse.success(response);
    }

    /**
     * 批量停止AI弹幕
     */
    @PostMapping("/batch/stop")
    public ApiResponse<Map<String, Object>> batchStop(@Valid @RequestBody AiDanmuBatchParam param) {
        Map<Long, Boolean> results = aiDanmuManagerService.batchStop(param.getUserIds());

        // 统计结果
        long successCount = results.values().stream().mapToLong(success -> success ? 1 : 0).sum();
        long failCount = results.size() - successCount;

        Map<String, Object> response = new HashMap<>();
        response.put("successCount", successCount);
        response.put("failCount", failCount);
        response.put("details", results);

        return ApiResponse.success(response);
    }

    /**
     * 获取AI弹幕统计数据
     */
    @GetMapping("/statistics")
    public ApiResponse<Map<String, Object>> getStatistics() {
        List<AiDanmuStatusVO> allStatus = aiDanmuManagerService.getAllStatus();

        long totalUsers = allStatus.size();
        long activeUsers = allStatus.stream()
            .mapToLong(status -> status.getStatus().getCode().equals("RUNNING") ? 1 : 0)
            .sum();

        long totalSentToday = allStatus.stream()
            .mapToLong(status -> status.getSentCount() != null ? status.getSentCount() : 0)
            .sum();

        double avgSentPerUser = totalUsers > 0 ? (double) totalSentToday / totalUsers : 0;

        // 获取最活跃的用户（按发送数量排序）
        List<AiDanmuStatusVO> topActiveUsers = allStatus.stream()
            .filter(status -> status.getSentCount() != null && status.getSentCount() > 0)
            .sorted((a, b) -> Long.compare(b.getSentCount(), a.getSentCount()))
            .limit(5)
            .collect(java.util.stream.Collectors.toList());

        Map<String, Object> statistics = new HashMap<>();
        statistics.put("totalUsers", totalUsers);
        statistics.put("activeUsers", activeUsers);
        statistics.put("totalSentToday", totalSentToday);
        statistics.put("avgSentPerUser", Math.round(avgSentPerUser * 100.0) / 100.0);
        statistics.put("topActiveUsers", topActiveUsers);

        return ApiResponse.success(statistics);
    }

    /**
     * 健康检查
     */
    @GetMapping("/health")
    public ApiResponse<Map<String, Object>> healthCheck() {
        List<AiDanmuStatusVO> allStatus = aiDanmuManagerService.getAllStatus();

        long activeConnections = allStatus.stream()
            .mapToLong(status -> status.getStatus().getCode().equals("RUNNING") ? 1 : 0)
            .sum();

        Map<String, Object> health = new HashMap<>();
        health.put("status", "healthy");
        health.put("activeConnections", activeConnections);
        // 可以扩展为真实的AI API状态检查
        health.put("aiApiStatus", "normal");
        health.put("lastCheckTime", java.time.LocalDateTime.now());

        return ApiResponse.success(health);
    }
}
