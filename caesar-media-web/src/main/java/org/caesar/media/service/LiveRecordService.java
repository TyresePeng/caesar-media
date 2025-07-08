package org.caesar.media.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.caesar.crawler.live.netty.base.exception.MediaException;
import org.caesar.media.dto.LiveRecordParam;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${live.record.path}")
    private String liveRecordPath;

    /**
     * 正在录制的任务缓存
     */
    private final Map<String, RecordTask> taskMap = new ConcurrentHashMap<>();

    /**
     * 已完成或中断的录制记录（供下载使用）
     */
    private final Map<String, CopyOnWriteArrayList<Path>> recordedFilesMap = new ConcurrentHashMap<>();

    private final ExecutorService executor = Executors.newCachedThreadPool();


    public String getKey(LiveRecordParam liveRecordParam) {
        return liveRecordParam.getRoomId() + "_" + liveRecordParam.getQuality();
    }

    /**
     * 启动录制任务
     */
    public void startRecording(LiveRecordParam liveRecordParam) {
        String roomId = liveRecordParam.getRoomId();
        String streamUrl = liveRecordParam.getStreamUrl();
        String key = this.getKey(liveRecordParam);

        if (taskMap.containsKey(key)) {
            log.warn("该直播间正在录制中：roomId:{},key:{}", roomId,key);
            return;
        }
        if (streamUrl == null || streamUrl.isEmpty()) {
            throw new MediaException("直播间无有效拉流地址");
        }

        String filename = key + "_" + System.currentTimeMillis() + ".mp4";
        Path outputPath = Paths.get(liveRecordPath, filename);

        try {
            Files.createDirectories(outputPath.getParent());
        } catch (IOException e) {
            throw new MediaException("无法创建输出目录", e);
        }

        executor.submit(() -> {
            Process process = null;
            try {
                List<String> command = new ArrayList<>();
                command.add("ffmpeg");
                command.add("-y");

                // 针对流地址类型，动态添加参数
                if (streamUrl.contains(".m3u8")) {
                    command.add("-reconnect");
                    command.add("1");
                    command.add("-reconnect_streamed");
                    command.add("1");
                    command.add("-reconnect_delay_max");
                    command.add("5");
                } else if (streamUrl.startsWith("rtmp") || streamUrl.contains(".flv")) {
                    command.add("-rw_timeout");
                    command.add("10000000"); // 10秒
                }

                // 输入流地址
                command.add("-i");
                command.add(streamUrl);

                // 编码参数
                command.addAll(Arrays.asList(
                        "-c:v", "libx264",
                        "-preset", "veryfast",
                        "-c:a", "aac",
                        "-movflags", "+faststart",
                        "-f", "mp4",
                        outputPath.toString()
                ));

                log.info("启动录制 FFmpeg：{}", String.join(" ", command));

                ProcessBuilder builder = new ProcessBuilder(command);
                builder.redirectErrorStream(true);
                process = builder.start();

                // 保存任务
                taskMap.put(key, new RecordTask(roomId, streamUrl, outputPath, process, System.currentTimeMillis()));

                // 输出日志（可选）
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        log.debug("[FFmpeg] {}", line);
                    }
                }

                int exitCode = process.waitFor();
                log.info("录制结束：roomId={}, path={}, exitCode={}", roomId, outputPath, exitCode);

                // 校验文件是否成功生成
                if (!Files.exists(outputPath) || Files.size(outputPath) == 0) {
                    log.warn("录制失败，输出文件未生成或为空: {}", outputPath);
                    Files.deleteIfExists(outputPath);
                }

            } catch (Exception e) {
                log.error("录制过程中异常：roomId=" + roomId, e);
            } finally {
                recordedFilesMap.computeIfAbsent(key, k -> new CopyOnWriteArrayList<>()).add(outputPath);
                taskMap.remove(key);
            }
        });
    }

    /**
     * 停止录制（优雅方式）
     */
    public void stopRecording(LiveRecordParam liveRecordParam) {
        String key = this.getKey(liveRecordParam);
        String roomId = liveRecordParam.getRoomId();
        RecordTask task = taskMap.get(key);
        if (task != null && task.getProcess().isAlive()) {
            try {
                stopFfmpeg(task.getProcess());
                log.info("已发送 destroy() 停止录制：roomId={}", roomId);
            } catch (Exception e) {
                log.warn("停止录制失败：roomId={}", roomId, e);
            }
            // 记录中断文件
            recordedFilesMap.computeIfAbsent(key, k -> new CopyOnWriteArrayList<>()).add(task.getOutputPath());
            taskMap.remove(key);
        }
    }

    /**
     * 停止 FFmpeg
     *
     * @param ffmpegProcess Process
     */
    public void stopFfmpeg(Process ffmpegProcess) {
        try (OutputStream os = ffmpegProcess.getOutputStream()) {
            // 通知 FFmpeg 正常退出
            os.write('q');
            os.flush();
            ffmpegProcess.waitFor(); // 等待退出完成
        } catch (IOException | InterruptedException e) {
            log.error("停止 FFmpeg 失败:{}", e.getMessage(), e);
            ffmpegProcess.destroy(); // 兜底
        }
    }

    /**
     * 判断是否正在录制
     */
    public boolean isRecording(String roomId) {
        return taskMap.containsKey(roomId) && taskMap.get(roomId).getProcess().isAlive();
    }

    /**
     * 获取最近一次录制文件（不管是否录制中断）
     */
    public Path getRecordedPath(LiveRecordParam liveRecordParam) {
        String key = this.getKey(liveRecordParam);
        RecordTask task = taskMap.get(key);
        if (task != null && Files.exists(task.getOutputPath())) {
            return task.getOutputPath();
        }

        List<Path> list = recordedFilesMap.get(key);
        if (list != null && !list.isEmpty()) {
            Path last = list.get(list.size() - 1);
            if (Files.exists(last)) return last;
        }
        throw new MediaException("未找到录制文件");
    }

    /**
     * 下载录制文件到响应体
     */
    public void downloadRecordingToResponse(LiveRecordParam liveRecordParam, HttpServletResponse resp) {
        Path path = getRecordedPath(liveRecordParam);
        if (!Files.exists(path)) {
            throw new MediaException("录制文件不存在");
        }

        // 设置响应头
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + path.getFileName() + "\"");
        resp.setContentLengthLong(path.toFile().length());

        try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(path));
             OutputStream os = resp.getOutputStream()) {

            byte[] buffer = new byte[8192];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            os.flush();
        } catch (IOException e) {
            log.error("下载录制文件失败：roomId:{},streamUrl:{}", liveRecordParam.getRoomId(), liveRecordParam.getStreamUrl(), e);
            throw new MediaException("下载失败");
        }
    }

    /**
     * 应用关闭时清理资源
     */
    @PreDestroy
    public void shutdown() {
        log.info("应用关闭，停止所有录制任务...");
        for (String roomId : taskMap.keySet()) {
            RecordTask recordTask = taskMap.get(roomId);
            LiveRecordParam liveRecordParam = new LiveRecordParam();
            liveRecordParam.setRoomId(recordTask.roomId);
            liveRecordParam.setStreamUrl(recordTask.streamUrl);
            stopRecording(liveRecordParam);
        }
        executor.shutdown();
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
