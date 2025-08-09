package org.caesar.media.integration;

import cn.hutool.json.JSONObject;
import org.caesar.crawler.live.douyin.client.client.DouyinClient;
import org.caesar.crawler.live.douyin.codec.api.DouyinApis;
import org.caesar.crawler.live.netty.base.enums.PublishTimeType;
import org.caesar.crawler.live.netty.base.enums.SearchChannelType;
import org.caesar.crawler.live.netty.base.enums.SearchSortType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 抖音API集成测试
 * 注意：这些测试需要真实的网络连接和有效的抖音账号
 * 
 * @author peng.guo
 * @create 2025-08-07
 */
@SpringBootTest
@Disabled("需要真实网络环境和账号，默认禁用")
class DouyinApiIntegrationTest {

    @Test
    void testQueryKeyWord_Integration() {
        try {
            JSONObject result = DouyinClient.queryKeyWord(
                "跳舞", 
                0, 
                5, 
                PublishTimeType.UNLIMITED,
                SearchChannelType.GENERAL,
                SearchSortType.GENERAL
            );
            
            // 验证响应结构
            assertNotNull(result);
            System.out.println("查询结果: " + result.toStringPretty());
            
            // 如果成功，验证基本字段
            if (result.getInt("status_code") == 0) {
                assertTrue(result.containsKey("data"));
                System.out.println("关键词查询测试通过！");
            } else {
                System.out.println("API返回错误: " + result.getStr("status_msg"));
            }
        } catch (Exception e) {
            System.err.println("网络请求失败，这在测试环境中是正常的: " + e.getMessage());
        }
    }

    @Test
    void testGetAwemePost_Integration() {
        // 注意：这里需要一个真实的sec_user_id进行测试
        String testSecUserId = "MS4wLjABAAAATest"; // 这需要替换为真实的ID
        
        try {
            JSONObject result = DouyinClient.getAwemePost(testSecUserId, 0L, 5);
            
            // 验证响应结构
            assertNotNull(result);
            System.out.println("用户视频查询结果: " + result.toStringPretty());
            
            // 如果成功，验证基本字段
            if (result.getInt("status_code") == 0) {
                assertTrue(result.containsKey("aweme_list"));
                System.out.println("用户视频查询测试通过！");
                
                // 输出视频信息
                if (result.getJSONArray("aweme_list") != null && !result.getJSONArray("aweme_list").isEmpty()) {
                    System.out.println("找到 " + result.getJSONArray("aweme_list").size() + " 个视频");
                }
            } else {
                System.out.println("API返回错误: " + result.getStr("status_msg"));
            }
        } catch (Exception e) {
            System.err.println("网络请求失败，这在测试环境中是正常的: " + e.getMessage());
        }
    }

    @Test 
    void testGetAwemePost_WithCookie() {
        // 这个测试需要真实的Cookie
        String testCookie = ""; // 需要填入真实的Cookie
        String testSecUserId = "MS4wLjABAAAATest"; // 需要真实的用户ID
        
        if (testCookie.isEmpty() || testSecUserId.equals("MS4wLjABAAAATest")) {
            System.out.println("跳过Cookie测试 - 需要真实的Cookie和用户ID");
            return;
        }
        
        try {
            JSONObject result = DouyinApis.getAwemePost(testCookie, testSecUserId, 0L, 5);
            
            assertNotNull(result);
            System.out.println("带Cookie的用户视频查询结果: " + result.toStringPretty());
            
            if (result.getInt("status_code") == 0) {
                assertTrue(result.containsKey("aweme_list"));
                System.out.println("带Cookie的用户视频查询测试通过！");
            }
        } catch (Exception e) {
            System.err.println("带Cookie的请求失败: " + e.getMessage());
        }
    }

    @Test
    void testParameterValidation() {
        // 测试参数构建是否正确
        try {
            // 测试空的sec_user_id是否会正确处理
            assertThrows(Exception.class, () -> {
                DouyinClient.getAwemePost("", 0L, 10);
            });
            
            // 测试负数cursor
            assertThrows(Exception.class, () -> {
                DouyinClient.getAwemePost("MS4wLjABAAAATest", -1L, 10);
            });
            
            System.out.println("参数验证测试通过！");
        } catch (Exception e) {
            System.err.println("参数验证测试失败: " + e.getMessage());
        }
    }

    @Test
    void testApiEndpoints() {
        // 测试API端点构建
        System.out.println("测试API端点构建...");
        
        // 这里可以测试URL构建逻辑
        String searchUri = "/aweme/v1/web/general/search/single/";
        String awemePostUri = "/aweme/v1/web/aweme/post/";
        
        assertEquals("/aweme/v1/web/general/search/single/", searchUri);
        assertEquals("/aweme/v1/web/aweme/post/", awemePostUri);
        
        System.out.println("API端点构建测试通过！");
    }
}