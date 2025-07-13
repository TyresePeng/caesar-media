package org.caesar.media.service.aidanmu.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.caesar.media.config.AiDanmuProperties;
import org.caesar.media.service.aidanmu.AiDanmuGenerateService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于WebClient的AI弹幕生成服务
 * 
 * @author peng.guo
 */
@Slf4j
@Service
@Primary
@RequiredArgsConstructor
@ConditionalOnProperty(name = "ai.danmu.api.api-key")
public class WebClientAiDanmuGenerateService implements AiDanmuGenerateService {
    
    private final AiDanmuProperties aiDanmuProperties;
    private final ObjectMapper objectMapper;
    
    private WebClient webClient;
    
    @Override
    public String generateDanmu(String roomDescription, String aiPersonality) {
        // 检查API配置
        if (!isAvailable()) {
            throw new RuntimeException("AI API配置不可用，请检查API Key配置");
        }
        
        // 使用AI API生成弹幕
        String aiGenerated = callAiApi(roomDescription, aiPersonality);
        
        // 如果AI生成失败，抛出异常
        if (aiGenerated == null || aiGenerated.trim().isEmpty()) {
            throw new RuntimeException("AI生成弹幕失败");
        }
        
        // 内容过滤和清理
        String cleanedContent = cleanupContent(aiGenerated);
        
        // 如果清理后为空，抛出异常
        if (cleanedContent == null || cleanedContent.trim().isEmpty()) {
            throw new RuntimeException("AI生成内容被过滤");
        }
        
        log.debug("AI生成弹幕成功: {}", cleanedContent);
        return cleanedContent;
    }
    
    /**
     * 调用AI API生成弹幕
     */
    private String callAiApi(String roomDescription, String aiPersonality) {
        try {
            // 懒加载WebClient
            if (webClient == null) {
                webClient = createWebClient();
            }
            
            // 构建请求体
            Map<String, Object> requestBody = buildRequestBody(roomDescription, aiPersonality);
            
            // 发送请求
            Mono<String> response = webClient
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofMillis(aiDanmuProperties.getApi().getTimeout()));
            
            // 同步获取结果
            String responseBody = response.block();
            
            // 解析响应
            return parseAiResponse(responseBody);
            
        } catch (Exception e) {
            log.error("AI API调用失败", e);
            return null;
        }
    }
    
    /**
     * 创建WebClient
     */
    private WebClient createWebClient() {
        AiDanmuProperties.AiApiConfig apiConfig = aiDanmuProperties.getApi();
        
        return WebClient.builder()
            .baseUrl(apiConfig.getUrl())
            .defaultHeader("Authorization", "Bearer " + apiConfig.getApiKey())
            .defaultHeader("Content-Type", "application/json")
            .build();
    }
    
    /**
     * 构建请求体
     */
    private Map<String, Object> buildRequestBody(String roomDescription, String aiPersonality) {
        AiDanmuProperties.AiApiConfig apiConfig = aiDanmuProperties.getApi();
        
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", apiConfig.getModel());
        requestBody.put("max_tokens", apiConfig.getMaxTokens());
        requestBody.put("temperature", apiConfig.getTemperature());
        
        // 构建消息
        Map<String, Object> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", buildSystemPrompt());
        
        Map<String, Object> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", buildUserPrompt(roomDescription, aiPersonality));
        
        Map<String, Object>[] messages = new Map[]{systemMessage, userMessage};
        requestBody.put("messages", messages);
        
        return requestBody;
    }
    
    /**
     * 构建系统提示词
     */
    private String buildSystemPrompt() {
        return "你是一个电商直播间的观众，需要生成自然的弹幕评论。" +
               "要求：" +
               "1. 弹幕要简短，不超过20个字 " +
               "2. 语言要自然，像真实观众说话 " +
               "3. 内容要与直播间主题相关 " +
               "4. 可以询问产品信息、表达购买意愿、夸赞主播等 " +
               "5. 避免重复和机械化的表达 " +
               "6. 不要包含敏感词汇";
    }
    
    /**
     * 构建用户提示词
     */
    private String buildUserPrompt(String roomDescription, String aiPersonality) {
        return "直播间情况：" + roomDescription + "\n" +
               "我的身份：" + aiPersonality + "\n" +
               "请生成一条适合的弹幕，只返回弹幕内容，不要其他解释：";
    }
    
    /**
     * 解析AI响应
     */
    private String parseAiResponse(String responseBody) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            
            // 检查错误
            if (rootNode.has("error")) {
                String errorMessage = rootNode.get("error").get("message").asText();
                log.error("AI API返回错误：{}", errorMessage);
                return null;
            }
            
            // 提取生成的内容
            JsonNode choicesNode = rootNode.get("choices");
            if (choicesNode != null && choicesNode.size() > 0) {
                JsonNode messageNode = choicesNode.get(0).get("message");
                if (messageNode != null) {
                    String content = messageNode.get("content").asText();
                    return content != null ? content.trim() : null;
                }
            }
            
            return null;
            
        } catch (Exception e) {
            log.error("解析AI响应失败", e);
            return null;
        }
    }
    
    /**
     * 清理AI生成的内容
     */
    private String cleanupContent(String content) {
        if (content == null) {
            return null;
        }
        
        // 清理多余的标点和空格
        content = content.trim()
            .replaceAll("^[\"'「」『』]", "")  // 删除开头的引号
            .replaceAll("[\"'「」『』]$", "")  // 删除结尾的引号
            .replaceAll("^弹幕[：:：]", "")   // 删除"弹幕："前缀
            .replaceAll("^回复[：:：]", "")   // 删除"回复："前缀
            .replaceAll("\\s+", " ")        // 合并多个空格
            .trim();
        
        // 长度限制
        AiDanmuProperties.ContentFilter filter = aiDanmuProperties.getContentFilter();
        if (content.length() > filter.getMaxLength()) {
            content = content.substring(0, filter.getMaxLength());
        }
        
        // 敏感词过滤
        for (String sensitiveWord : filter.getSensitiveWords()) {
            if (content.contains(sensitiveWord)) {
                return null; // 包含敏感词，返回空
            }
        }
        
        return content;
    }
    
    @Override
    public boolean isAvailable() {
        try {
            return aiDanmuProperties.isEnabled() && 
                   aiDanmuProperties.getApi() != null &&
                   aiDanmuProperties.getApi().getApiKey() != null &&
                   !aiDanmuProperties.getApi().getApiKey().trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 测试AI生成功能
     */
    public String testGenerate(String roomDescription, String aiPersonality) {
        log.info("测试AI弹幕生成，直播间：{}，人格：{}", roomDescription, aiPersonality);
        
        long startTime = System.currentTimeMillis();
        String result = generateDanmu(roomDescription, aiPersonality);
        long duration = System.currentTimeMillis() - startTime;
        
        log.info("AI弹幕生成完成，耗时：{}ms，结果：{}", duration, result);
        return result;
    }
}