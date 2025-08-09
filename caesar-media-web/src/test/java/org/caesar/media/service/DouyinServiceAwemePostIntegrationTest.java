package org.caesar.media.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DouyinService.getAwemePost 方法集成测试
 * 启动SpringBoot容器进行真实测试
 * 
 * @author peng.guo
 * @create 2025-08-07
 */
@SpringBootTest
@ActiveProfiles("test") // 使用测试配置
class DouyinServiceAwemePostIntegrationTest {

    @Autowired
    private DouyinService douyinService;

    @Test
    void testGetAwemePost_WithRealData() {
        System.out.println("=== 开始测试 DouyinService.getAwemePost 方法 ===");
        
        // TODO: 请在这里填入真实的测试数据
        String secUserId = "MS4wLjABAAAANRTjNmEV_lI7Psl_tx05JoGFaWWAgpsBoLXU8w6SRbCkPr8b2v7p1WHfAyFVxZL7";  // 替换为真实用户ID
        Long maxCursor = 0L;
        Integer count = 5; // 测试用较小的数量
        Long userId = null; // 不指定用户，使用默认session
        
        System.out.println("测试参数:");
        System.out.println("- secUserId: " + secUserId);
        System.out.println("- maxCursor: " + maxCursor);
        System.out.println("- count: " + count);
        System.out.println("- userId: " + userId);
        System.out.println();
        
        try {
            // 调用服务方法
            JSONObject result = douyinService.getAwemePost(secUserId, maxCursor, count, userId);
            
            // 验证响应不为空
            assertNotNull(result, "响应结果不应该为空");
            
            System.out.println("API响应:");
            System.out.println(result.toStringPretty());
            System.out.println();
            
            // 验证响应结构
            assertTrue(result.containsKey("status_code"), "响应应该包含 status_code");
            
            int statusCode = result.getInt("status_code");
            System.out.println("状态码: " + statusCode);
            
            if (statusCode == 0) {
                System.out.println("✅ 请求成功！");
                
                // 验证成功响应的结构
                assertTrue(result.containsKey("aweme_list"), "成功响应应该包含 aweme_list");
                
                JSONArray awemeList = result.getJSONArray("aweme_list");
                assertNotNull(awemeList, "aweme_list 不应该为空");
                
                System.out.println("获取到视频数量: " + awemeList.size());
                
                // 输出视频信息
                for (int i = 0; i < awemeList.size() && i < 3; i++) {
                    JSONObject video = awemeList.getJSONObject(i);
                    System.out.println("视频 " + (i + 1) + ":");
                    System.out.println("  - ID: " + video.getStr("aweme_id"));
                    System.out.println("  - 描述: " + getVideoDesc(video));
                    System.out.println("  - 创建时间: " + video.getLong("create_time"));
                    
                    // 验证视频基本字段
                    assertNotNull(video.getStr("aweme_id"), "视频ID不应该为空");
                }
                
                // 检查分页信息
                if (result.containsKey("has_more")) {
                    int hasMore = result.getInt("has_more");
                    System.out.println("是否还有更多: " + (hasMore == 1 ? "是" : "否"));
                    
                    if (hasMore == 1) {
                        assertTrue(result.containsKey("max_cursor"), "有更多数据时应该包含 max_cursor");
                        System.out.println("下次请求游标: " + result.getStr("max_cursor"));
                    }
                }
                
            } else {
                System.out.println("❌ 请求失败: " + result.getStr("status_msg"));
                
                // 即使失败，也要验证错误响应的结构
                assertTrue(result.containsKey("status_msg"), "失败响应应该包含 status_msg");
                
                String statusMsg = result.getStr("status_msg");
                System.out.println("错误信息: " + statusMsg);
                
                // 根据错误信息给出建议
                printErrorSuggestions(statusCode, statusMsg);
            }
            
        } catch (Exception e) {
            System.err.println("❌ 测试异常: " + e.getMessage());
            e.printStackTrace();
            fail("测试不应该抛出异常: " + e.getMessage());
        }
        
        System.out.println("\n=== 测试完成 ===");
    }

    @Test
    void testGetAwemePost_WithSpecificUser() {
        System.out.println("=== 测试指定用户ID的场景 ===");
        
        // TODO: 填入真实数据
        String secUserId = "MS4wLjABAAAAtest";  // 替换为真实用户ID
        Long maxCursor = 0L;
        Integer count = 3;
        Long userId = 1L; // 指定用户池中的用户ID，替换为真实值
        
        System.out.println("测试参数:");
        System.out.println("- secUserId: " + secUserId);
        System.out.println("- userId: " + userId + " (指定用户)");
        System.out.println();
        
        try {
            JSONObject result = douyinService.getAwemePost(secUserId, maxCursor, count, userId);
            
            assertNotNull(result, "指定用户时响应不应该为空");
            System.out.println("指定用户测试结果:");
            System.out.println("状态码: " + result.getInt("status_code"));
            
            if (result.getInt("status_code") == 0) {
                System.out.println("✅ 指定用户测试成功");
            } else {
                System.out.println("⚠️ 指定用户测试失败: " + result.getStr("status_msg"));
            }
            
        } catch (Exception e) {
            System.err.println("指定用户测试异常: " + e.getMessage());
            // 指定用户可能会失败（如果用户池中没有该用户），这是正常的
        }
        
        System.out.println("=== 指定用户测试完成 ===");
    }

    @Test
    void testGetAwemePost_Pagination() {
        System.out.println("=== 测试分页功能 ===");
        
        // TODO: 填入真实数据
        String secUserId = "MS4wLjABAAAAtest";  // 替换为真实用户ID
        Long maxCursor = 0L;
        Integer count = 2; // 小数量便于测试分页
        Long userId = null;
        
        try {
            // 第一页
            System.out.println("获取第一页...");
            JSONObject firstPage = douyinService.getAwemePost(secUserId, maxCursor, count, userId);
            
            assertNotNull(firstPage, "第一页响应不应该为空");
            
            if (firstPage.getInt("status_code") == 0) {
                System.out.println("✅ 第一页获取成功");
                
                JSONArray firstList = firstPage.getJSONArray("aweme_list");
                System.out.println("第一页视频数量: " + firstList.size());
                
                // 如果有更多数据，测试第二页
                if (firstPage.getInt("has_more") == 1) {
                    String nextCursor = firstPage.getStr("max_cursor");
                    System.out.println("获取第二页，游标: " + nextCursor);
                    
                    // 等待一秒避免请求过快
                    Thread.sleep(1000);
                    
                    JSONObject secondPage = douyinService.getAwemePost(secUserId, Long.parseLong(nextCursor), count, userId);
                    
                    if (secondPage.getInt("status_code") == 0) {
                        System.out.println("✅ 第二页获取成功");
                        
                        JSONArray secondList = secondPage.getJSONArray("aweme_list");
                        System.out.println("第二页视频数量: " + secondList.size());
                        
                        // 验证两页数据不重复
                        if (firstList.size() > 0 && secondList.size() > 0) {
                            String firstVideoId = firstList.getJSONObject(0).getStr("aweme_id");
                            String secondVideoId = secondList.getJSONObject(0).getStr("aweme_id");
                            assertNotEquals(firstVideoId, secondVideoId, "两页数据不应该重复");
                            System.out.println("✅ 分页数据验证通过");
                        }
                    } else {
                        System.out.println("⚠️ 第二页获取失败: " + secondPage.getStr("status_msg"));
                    }
                } else {
                    System.out.println("ℹ️ 该用户只有一页数据");
                }
            } else {
                System.out.println("❌ 第一页获取失败: " + firstPage.getStr("status_msg"));
            }
            
        } catch (Exception e) {
            System.err.println("分页测试异常: " + e.getMessage());
        }
        
        System.out.println("=== 分页测试完成 ===");
    }

    /**
     * 获取视频描述，处理空值
     */
    private String getVideoDesc(JSONObject video) {
        String desc = video.getStr("desc");
        return desc != null && !desc.trim().isEmpty() ? desc : "[无描述]";
    }

    /**
     * 根据错误码和错误信息提供建议
     */
    private void printErrorSuggestions(int statusCode, String statusMsg) {
        System.out.println("\n💡 错误处理建议:");
        
        switch (statusCode) {
            case 10000:
                System.out.println("- 参数错误，请检查 sec_user_id 是否正确");
                break;
            case 10001:
                System.out.println("- 用户不存在，请确认 sec_user_id 是有效的用户ID");
                break;
            case 10002:
                System.out.println("- 用户私密账号，无法获取视频");
                break;
            case 10003:
                System.out.println("- 请求过于频繁，建议稍后重试");
                break;
            case 10004:
                System.out.println("- 需要登录，建议配置有效的 Cookie");
                break;
            default:
                System.out.println("- 请检查网络连接和服务配置");
                System.out.println("- 确认 PlaywrightFactoryPool 是否正常工作");
                System.out.println("- 查看详细日志获取更多信息");
        }
    }
}