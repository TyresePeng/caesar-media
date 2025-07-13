package org.caesar.media.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AI弹幕配置属性
 * 
 * @author peng.guo
 */
@Data
@Component
@ConfigurationProperties(prefix = "ai.danmu")
public class AiDanmuProperties {
    
    /**
     * 是否启用AI弹幕功能
     */
    private boolean enabled = true;
    
    /**
     * 最大并发用户数
     */
    private int maxConcurrentUsers = 10;
    
    /**
     * 默认发送间隔基础值（秒）
     */
    private int baseInterval = 30;
    
    /**
     * 最小随机等待时间（秒）
     */
    private int minRandomSeconds = 1;
    
    /**
     * 最大随机等待时间（秒）
     */
    private int maxRandomSeconds = 10;
    
    /**
     * AI API配置
     */
    private AiApiConfig api = new AiApiConfig();
    
    /**
     * 内容过滤配置
     */
    private ContentFilter contentFilter = new ContentFilter();
    
    /**
     * 默认配置
     */
    private DefaultConfig defaultConfig = new DefaultConfig();
    
    @Data
    public static class AiApiConfig {
        /**
         * AI API地址
         */
        private String url = "https://api.openai.com/v1/chat/completions";
        
        /**
         * API密钥
         */
        private String apiKey = "";
        
        /**
         * 模型名称
         */
        private String model = "gpt-3.5-turbo";
        
        /**
         * 最大令牌数
         */
        private int maxTokens = 50;
        
        /**
         * 温度参数
         */
        private double temperature = 0.8;
        
        /**
         * 请求超时时间（毫秒）
         */
        private int timeout = 10000;
        
        /**
         * 重试次数
         */
        private int retryCount = 2;
    }
    
    @Data
    public static class ContentFilter {
        /**
         * 是否启用内容过滤
         */
        private boolean enabled = true;
        
        /**
         * 敏感词列表
         */
        private String[] sensitiveWords = {"admin", "管理员", "机器人"};
        
        /**
         * 最大弹幕长度
         */
        private int maxLength = 30;
        
        /**
         * 最小弹幕长度
         */
        private int minLength = 2;
        
        /**
         * 是否过滤重复内容
         */
        private boolean filterDuplicate = true;
        
        /**
         * 重复检查历史数量
         */
        private int duplicateCheckCount = 10;
    }
    
    @Data
    public static class DefaultConfig {
        /**
         * 默认随机秒数
         */
        private int randomSeconds = 30;
        
        /**
         * 默认AI人格
         */
        private String aiPersonality = "热情的观众";
        
        /**
         * 默认直播间描述
         */
        private String roomDescription = "电商直播间";
    }
}