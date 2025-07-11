package org.caesar.media.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.caesar.media.config.LiveRecordProperties;
import org.caesar.media.dto.LiveRecordParam;
import org.caesar.media.enums.ErrorCode;
import org.caesar.media.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 抖音直播录制服务（使用 Java 进程方式执行 FFmpeg）
 * 支持：
 * - 拉流录制
 * - 优雅中断
 * - 断点下载
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LiveRecordService {

    private final LiveRecordProperties liveRecordProperties;

    /**
     * 正在录制的任务缓存
     */
    private final Map<String, RecordTask> taskMap = new ConcurrentHashMap<>();

    /**
     * 已完成或中断的录制记录（供下载使用）
     */
    private final Map<String, CopyOnWriteArrayList<Path>> recordedFilesMap = new ConcurrentHashMap<>();

    /**
     * 线程池 - 延迟初始化
     */
    private volatile ExecutorService executor;

    /**
     * 获取或创建线程池
     */
    private ExecutorService getExecutor() {
        if (executor == null) {
            synchronized (this) {
                if (executor == null) {
                    executor = Executors.newFixedThreadPool(
                        liveRecordProperties.getMaxConcurrentRecords(),
                        r -> {
                            Thread thread = new Thread(r, "live-record-" + System.currentTimeMillis());
                            thread.setDaemon(true);
                            return thread;
                        }
                    );
                }
            }
        }
        return executor;
    }

    public String getKey(LiveRecordParam liveRecordParam) {
        return liveRecordParam.getRoomId() + "_" + liveRecordParam.getQuality();
    }

    /**
     * 启动录制任务
     */
    public void startRecording(LiveRecordParam liveRecordParam) {
        String key = getKey(liveRecordParam);

        validateRecordingRequest(liveRecordParam, key);

        String filename = generateFilename(key);
        Path outputPath = createOutputPath(filename);

        getExecutor().submit(() -> executeRecording(liveRecordParam, key, outputPath));
    }

    /**
     * 验证录制请求
     */
    private void validateRecordingRequest(LiveRecordParam liveRecordParam, String key) {
        if (taskMap.containsKey(key)) {
            log.warn("该直播间正在录制中：roomId:{}, key:{}", liveRecordParam.getRoomId(), key);
            return;
        }

        if (taskMap.size() >= liveRecordProperties.getMaxConcurrentRecords()) {
            throw new BusinessException(ErrorCode.RECORDING_MAX_CONCURRENT_REACHED);
        }

        String streamUrl = liveRecordParam.getStreamUrl();
        if (streamUrl == null || streamUrl.trim().isEmpty()) {
            throw new BusinessException(ErrorCode.LIVE_ROOM_OFFLINE, "直播间无有效拉流地址");
        }
    }

    /**
     * 生成录制文件名
     */
    private String generateFilename(String key) {
        return key + "_" + System.currentTimeMillis() + ".mp4";
    }

    /**
     * 创建输出路径
     */
    private Path createOutputPath(String filename) {
        Path outputPath = Paths.get(liveRecordProperties.getPath(), filename);
        try {
            Files.createDirectories(outputPath.getParent());
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.RECORDING_START_FAILED, "无法创建输出目录");
        }
        return outputPath;
    }

    /**
     * 执行录制
     */
    private void executeRecording(LiveRecordParam liveRecordParam, String key, Path outputPath) {
        Process process = null;
        try {
            List<String> command = buildFFmpegCommand(liveRecordParam, outputPath);
            process = startFFmpegProcess(command);

            // 保存任务
            taskMap.put(key, new RecordTask(
                liveRecordParam.getRoomId(),
                liveRecordParam.getStreamUrl(),
                outputPath,
                process,
                System.currentTimeMillis()
            ));

            handleFFmpegOutput(process, liveRecordParam.getRoomId());

            int exitCode = process.waitFor();
            log.info("录制结束：roomId={}, path={}, exitCode={}",
                liveRecordParam.getRoomId(), outputPath, exitCode);

            validateRecordingResult(outputPath);

        } catch (Exception e) {
            log.error("录制过程中异常：roomId={}", liveRecordParam.getRoomId(), e);
            throw new BusinessException(ErrorCode.RECORDING_START_FAILED, e.getMessage());
        } finally {
            recordedFilesMap.computeIfAbsent(key, k -> new CopyOnWriteArrayList<>()).add(outputPath);
            taskMap.remove(key);
        }
    }

    /**
     * 构建FFmpeg命令
     */
    private List<String> buildFFmpegCommand(LiveRecordParam liveRecordParam, Path outputPath) {
        String streamUrl = liveRecordParam.getStreamUrl();
        List<String> command = new ArrayList<>();

        command.add(liveRecordProperties.getFfmpegPath());
        command.add("-y");

        // 针对流地址类型，动态添加参数
        if (streamUrl.contains(".m3u8")) {
            addHlsParameters(command);
        } else if (streamUrl.startsWith("rtmp") || streamUrl.contains(".flv")) {
            addRtmpParameters(command);
        }

        // 输入流地址
        command.add("-i");
        command.add(streamUrl);

        // 编码参数
        addEncodingParameters(command, outputPath);

        return command;
    }

    /**
     * 添加HLS参数
     */
    private void addHlsParameters(List<String> command) {
        command.addAll(Arrays.asList(
            "-reconnect", "1",
            "-reconnect_streamed", "1",
            "-reconnect_delay_max", String.valueOf(liveRecordProperties.getReconnectDelayMax())
        ));
    }

    /**
     * 添加RTMP参数
     */
    private void addRtmpParameters(List<String> command) {
        command.addAll(Arrays.asList(
            "-rw_timeout", String.valueOf(liveRecordProperties.getRwTimeout())
        ));
    }

    /**
     * 添加编码参数
     */
    private void addEncodingParameters(List<String> command, Path outputPath) {
        command.addAll(Arrays.asList(
            "-c:v", "libx264",
            "-preset", "veryfast",
            "-c:a", "aac",
            "-movflags", "+faststart",
            "-f", "mp4",
            "-t", String.valueOf(liveRecordProperties.getMaxRecordDuration()),
            outputPath.toString()
        ));
    }

    /**
     * 启动FFmpeg进程
     */
    private Process startFFmpegProcess(List<String> command) throws IOException {
        log.info("启动录制 FFmpeg：{}", String.join(" ", command));

        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);

        try {
            return builder.start();
        } catch (IOException e) {
            if (e.getMessage().contains("Cannot run program")) {
                throw new BusinessException(ErrorCode.FFMPEG_NOT_FOUND);
            }
            throw e;
        }
    }

    /**
     * 处理FFmpeg输出
     */
    private void handleFFmpegOutput(Process process, String roomId) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                log.debug("[FFmpeg-{}] {}", roomId, line);
            }
        } catch (IOException e) {
            log.warn("读取FFmpeg输出失败：{}", e.getMessage());
        }
    }

    /**
     * 验证录制结果
     */
    private void validateRecordingResult(Path outputPath) {
        try {
            if (!Files.exists(outputPath) || Files.size(outputPath) == 0) {
                log.warn("录制失败，输出文件未生成或为空: {}", outputPath);
                Files.deleteIfExists(outputPath);
                throw new BusinessException(ErrorCode.RECORDING_START_FAILED, "录制文件生成失败");
            }
        } catch (IOException e) {
            log.error("验证录制文件失败：{}", e.getMessage());
        }
    }

    /**
     * 停止录制（优雅方式）
     */
    public void stopRecording(LiveRecordParam liveRecordParam) {
        String key = getKey(liveRecordParam);
        String roomId = liveRecordParam.getRoomId();
        RecordTask task = taskMap.get(key);

        if (task != null && task.getProcess().isAlive()) {
            try {
                stopFFmpegProcess(task.getProcess());
                log.info("已发送停止信号：roomId={}", roomId);
            } catch (Exception e) {
                log.warn("停止录制失败：roomId={}", roomId, e);
                throw new BusinessException(ErrorCode.RECORDING_STOP_FAILED, e.getMessage());
            }
            // 记录中断文件
            recordedFilesMap.computeIfAbsent(key, k -> new CopyOnWriteArrayList<>()).add(task.getOutputPath());
            taskMap.remove(key);
        }
    }

    /**
     * 停止 FFmpeg 进程
     */
    private void stopFFmpegProcess(Process ffmpegProcess) {
        try (OutputStream os = ffmpegProcess.getOutputStream()) {
            // 通知 FFmpeg 正常退出
            os.write('q');
            os.flush();

            // 等待退出，最多等待5秒
            if (!ffmpegProcess.waitFor(5, TimeUnit.SECONDS)) {
                log.warn("FFmpeg未在5秒内正常退出，强制终止");
                ffmpegProcess.destroyForcibly();
            }
        } catch (IOException | InterruptedException e) {
            log.error("停止 FFmpeg 失败：{}", e.getMessage(), e);
            ffmpegProcess.destroyForcibly();
        }
    }

    /**
     * 判断是否正在录制
     */
    public boolean isRecording(String roomId) {
        return taskMap.values().stream()
            .anyMatch(task -> task.getRoomId().equals(roomId) && task.getProcess().isAlive());
    }

    /**
     * 获取最近一次录制文件（不管是否录制中断）
     */
    public Path getRecordedPath(LiveRecordParam liveRecordParam) {
        String key = getKey(liveRecordParam);
        RecordTask task = taskMap.get(key);

        if (task != null && Files.exists(task.getOutputPath())) {
            return task.getOutputPath();
        }

        List<Path> list = recordedFilesMap.get(key);
        if (list != null && !list.isEmpty()) {
            Path last = list.get(list.size() - 1);
            if (Files.exists(last)) {
                return last;
            }
        }
        throw new BusinessException(ErrorCode.RECORDING_FILE_NOT_FOUND);
    }

    /**
     * 下载录制文件到响应体
     */
    public void downloadRecordingToResponse(LiveRecordParam liveRecordParam, HttpServletResponse resp) {
        Path path = getRecordedPath(liveRecordParam);
        if (!Files.exists(path)) {
            throw new BusinessException(ErrorCode.RECORDING_FILE_NOT_FOUND);
        }

        // 设置响应头
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + path.getFileName() + "\"");
        resp.setContentLengthLong(path.toFile().length());

        try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(path));
             OutputStream os = resp.getOutputStream()) {

            byte[] buffer = new byte[liveRecordProperties.getBufferSize()];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            os.flush();
        } catch (IOException e) {
            log.error("下载录制文件失败：roomId:{}, streamUrl:{}",
                liveRecordParam.getRoomId(), liveRecordParam.getStreamUrl(), e);
            throw new BusinessException(ErrorCode.RECORDING_FILE_NOT_FOUND, "下载失败");
        }
    }

    /**
     * 应用关闭时清理资源
     */
    @PreDestroy
    public void shutdown() {
        log.info("应用关闭，停止所有录制任务...");

        // 停止所有录制任务
        taskMap.values().parallelStream().forEach(task -> {
            try {
                stopFFmpegProcess(task.getProcess());
            } catch (Exception e) {
                log.warn("停止录制任务失败：{}", e.getMessage());
            }
        });

        // 关闭线程池
        ExecutorService currentExecutor = executor;
        if (currentExecutor != null) {
            currentExecutor.shutdown();
            try {
                if (!currentExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
                    currentExecutor.shutdownNow();
                }
            } catch (InterruptedException e) {
                currentExecutor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 录制任务对象
     */
    @Data
    @RequiredArgsConstructor
    private static class RecordTask {
        private final String roomId;
        private final String streamUrl;
        private final Path outputPath;
        private final Process process;
        private final long startTime;
    }
}
