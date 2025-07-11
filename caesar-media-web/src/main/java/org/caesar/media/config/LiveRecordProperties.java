package org.caesar.media.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 直播录制配置属性
 * 
 * @author peng.guo
 */
@Data
@Component
@ConfigurationProperties(prefix = "live.record")
public class LiveRecordProperties {
    
    /**
     * 录制文件保存路径
     */
    private String path = "/tmp/records/";
    
    /**
     * FFmpeg 可执行文件路径
     */
    private String ffmpegPath = "ffmpeg";
    
    /**
     * 最大并发录制数量
     */
    private int maxConcurrentRecords = 10;
    
    /**
     * 最大录制时长（秒）
     */
    private long maxRecordDuration = 3600;
    
    /**
     * 重连延迟时间（秒）
     */
    private int reconnectDelayMax = 5;
    
    /**
     * 读写超时时间（微秒）
     */
    private long rwTimeout = 10000000;
    
    /**
     * 文件缓冲区大小
     */
    private int bufferSize = 8192;
    
    /**
     * 是否自动清理过期录制文件
     */
    private boolean autoCleanup = true;
    
    /**
     * 录制文件保留天数
     */
    private int retentionDays = 7;
}