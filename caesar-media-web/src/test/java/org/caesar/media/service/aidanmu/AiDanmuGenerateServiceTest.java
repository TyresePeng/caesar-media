package org.caesar.media.service.aidanmu;

import org.caesar.media.config.AiDanmuProperties;
import org.caesar.media.service.aidanmu.impl.WebClientAiDanmuGenerateService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AI弹幕生成服务测试
 * 
 * @author peng.guo
 */
@SpringBootTest
@TestPropertySource(properties = {
    "ai.danmu.enabled=true",
    "ai.danmu.api.api-key=7d01fe379e2d4592a32abf1596900ff0.qemgh4GRk0JD3c6f",
    "ai.danmu.api.url=https://open.bigmodel.cn/api/paas/v4/chat/completions",
    "ai.danmu.api.model=glm-4-flash",
    "ai.danmu.api.max-tokens=50",
    "ai.danmu.api.temperature=0.8",
    "ai.danmu.api.timeout=30000"
})
public class AiDanmuGenerateServiceTest {

    @Resource
    private AiDanmuGenerateService aiDanmuGenerateService;
    
    @Resource
    private AiDanmuProperties aiDanmuProperties;

    @Test
    public void testServiceAvailability() {
        System.out.println("=== 测试服务可用性 ===");
        
        boolean aiAvailable = aiDanmuGenerateService.isAvailable();
        System.out.println("AI服务可用: " + aiAvailable);
        
        // 测试服务类型
        System.out.println("服务实现类型: " + aiDanmuGenerateService.getClass().getSimpleName());
        assertTrue(aiDanmuGenerateService instanceof WebClientAiDanmuGenerateService);
    }
    
    @Test
    public void testAiDanmuProperties() {
        System.out.println("=== 测试AI弹幕配置 ===");
        
        System.out.println("AI弹幕启用: " + aiDanmuProperties.isEnabled());
        System.out.println("API Key: " + aiDanmuProperties.getApi().getApiKey());
        System.out.println("API URL: " + aiDanmuProperties.getApi().getUrl());
        System.out.println("模型: " + aiDanmuProperties.getApi().getModel());
        System.out.println("最大Token: " + aiDanmuProperties.getApi().getMaxTokens());
        System.out.println("温度: " + aiDanmuProperties.getApi().getTemperature());
        System.out.println("超时: " + aiDanmuProperties.getApi().getTimeout());
        
        System.out.println("内容过滤 - 最大长度: " + aiDanmuProperties.getContentFilter().getMaxLength());
        System.out.println("敏感词: " + aiDanmuProperties.getContentFilter().getSensitiveWords());
        
        System.out.println("默认配置 - 随机秒数: " + aiDanmuProperties.getDefaultConfig().getRandomSeconds());
        System.out.println("默认AI人格: " + aiDanmuProperties.getDefaultConfig().getAiPersonality());
        System.out.println("默认直播间描述: " + aiDanmuProperties.getDefaultConfig().getRoomDescription());
    }
    
    @Test
    public void testRealAiDanmuGenerate() {
        System.out.println("=== 测试真实AI弹幕生成 ===");
        
        try {
            // 测试美妆场景
            String danmu1 = aiDanmuGenerateService.generateDanmu("美妆护肤产品直播间", "热情的观众");
            System.out.println("美妆场景弹幕: " + danmu1);
            assertNotNull(danmu1);
            assertFalse(danmu1.trim().isEmpty());
            assertTrue(danmu1.length() <= 20);
            
            // 测试时尚场景
            String danmu2 = aiDanmuGenerateService.generateDanmu("时尚服装直播间", "时尚爱好者");
            System.out.println("时尚场景弹幕: " + danmu2);
            assertNotNull(danmu2);
            assertFalse(danmu2.trim().isEmpty());
            
            // 测试数码场景
            String danmu3 = aiDanmuGenerateService.generateDanmu("数码产品直播间", "科技爱好者");
            System.out.println("数码场景弹幕: " + danmu3);
            assertNotNull(danmu3);
            assertFalse(danmu3.trim().isEmpty());
            
            // 测试食品场景
            String danmu4 = aiDanmuGenerateService.generateDanmu("美食零食直播间", "美食爱好者");
            System.out.println("美食场景弹幕: " + danmu4);
            assertNotNull(danmu4);
            assertFalse(danmu4.trim().isEmpty());
            
            // 测试家居场景
            String danmu5 = aiDanmuGenerateService.generateDanmu("家居用品直播间", "居家达人");
            System.out.println("家居场景弹幕: " + danmu5);
            assertNotNull(danmu5);
            assertFalse(danmu5.trim().isEmpty());
            
        } catch (Exception e) {
            System.err.println("AI弹幕生成失败: " + e.getMessage());
            e.printStackTrace();
            fail("AI弹幕生成不应该失败");
        }
    }
    
    @Test
    public void testBatchAiGenerate() {
        System.out.println("=== 测试批量AI弹幕生成 ===");
        
        try {
            for (int i = 0; i < 5; i++) {
                String danmu = aiDanmuGenerateService.generateDanmu("电商直播间", "观众" + i);
                System.out.println("第" + (i + 1) + "条AI弹幕: " + danmu);
                assertNotNull(danmu);
                assertFalse(danmu.trim().isEmpty());
                assertTrue(danmu.length() <= 20);
                
                // 避免请求过快
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.err.println("批量AI弹幕生成失败: " + e.getMessage());
            e.printStackTrace();
            fail("批量AI弹幕生成不应该失败");
        }
    }
    
    @Test
    public void testPerformanceWithRealAi() {
        System.out.println("=== 测试AI弹幕生成性能 ===");
        
        try {
            long startTime = System.currentTimeMillis();
            
            // 生成10条AI弹幕测试性能
            for (int i = 0; i < 10; i++) {
                String danmu = aiDanmuGenerateService.generateDanmu("电商直播间", "观众");
                assertNotNull(danmu);
                assertFalse(danmu.trim().isEmpty());
                
                // 避免请求过快，智谱AI有QPS限制
                Thread.sleep(200);
            }
            
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            System.out.println("生成10条AI弹幕总耗时: " + duration + "ms");
            System.out.println("平均每条耗时: " + (duration / 10.0) + "ms");
            
            // AI调用性能要求相对宽松
            assertTrue(duration < 60000, "AI弹幕生成总时间不应超过60秒");
            
        } catch (Exception e) {
            System.err.println("AI性能测试失败: " + e.getMessage());
            e.printStackTrace();
            fail("AI性能测试不应该失败");
        }
    }
    
    @Test
    public void testAiServiceWithoutApiKey() {
        System.out.println("=== 测试无API Key情况 ===");
        
        // 临时清空API Key
        String originalKey = aiDanmuProperties.getApi().getApiKey();
        aiDanmuProperties.getApi().setApiKey(null);
        
        try {
            boolean available = aiDanmuGenerateService.isAvailable();
            System.out.println("无API Key时服务可用性: " + available);
            assertFalse(available);
            
            // 尝试生成弹幕应该抛出异常
            assertThrows(RuntimeException.class, () -> {
                aiDanmuGenerateService.generateDanmu("测试直播间", "测试观众");
            });
            
            System.out.println("无API Key时正确抛出异常");
            
        } finally {
            // 恢复原始API Key
            aiDanmuProperties.getApi().setApiKey(originalKey);
        }
    }
    
    @Test
    public void testContentFilterConfiguration() {
        System.out.println("=== 测试内容过滤配置 ===");
        
        AiDanmuProperties.ContentFilter filter = aiDanmuProperties.getContentFilter();
        
        assertTrue(filter.isEnabled());
        assertTrue(filter.getMaxLength() > 0);
        assertTrue(filter.getMinLength() > 0);
        assertTrue(filter.getSensitiveWords().length > 0);
        
        System.out.println("内容过滤配置验证通过");
    }
    
    @Test
    public void testDefaultConfiguration() {
        System.out.println("=== 测试默认配置 ===");
        
        AiDanmuProperties.DefaultConfig defaultConfig = aiDanmuProperties.getDefaultConfig();
        
        assertNotNull(defaultConfig);
        assertTrue(defaultConfig.getRandomSeconds() > 0);
        assertNotNull(defaultConfig.getAiPersonality());
        assertNotNull(defaultConfig.getRoomDescription());
        assertFalse(defaultConfig.getAiPersonality().trim().isEmpty());
        assertFalse(defaultConfig.getRoomDescription().trim().isEmpty());
        
        System.out.println("默认配置验证通过");
    }
    
    @Test
    public void testApiConfiguration() {
        System.out.println("=== 测试API配置 ===");
        
        AiDanmuProperties.AiApiConfig apiConfig = aiDanmuProperties.getApi();
        
        assertNotNull(apiConfig);
        assertNotNull(apiConfig.getUrl());
        assertNotNull(apiConfig.getModel());
        assertTrue(apiConfig.getMaxTokens() > 0);
        assertTrue(apiConfig.getTemperature() >= 0);
        assertTrue(apiConfig.getTimeout() > 0);
        
        System.out.println("API配置验证通过");
    }
}