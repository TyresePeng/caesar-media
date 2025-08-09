package org.caesar.media.test;

import cn.hutool.json.JSONObject;
import org.caesar.media.CaesarMediaApplication;
import org.caesar.media.service.DouyinService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 测试所有新增的抖音API接口
 * 
 * @author peng.guo
 * @create 2025-08-07
 */
public class DouyinAllApisTestRunner {

    public static void main(String[] args) {
        System.out.println("🚀 启动SpringBoot应用测试所有抖音API...");
        
        ConfigurableApplicationContext context = SpringApplication.run(CaesarMediaApplication.class, args);
        
        try {
            DouyinService douyinService = context.getBean(DouyinService.class);
            System.out.println("✅ DouyinService 注入成功");
            
            // 测试数据 - 请替换为真实值
            String testSecUserId = "MS4wLjABAAAAT9yh4Ay75Ggp1Uq58BQFQCUSWSapkg6PPgzGjs1R-2o";
            String testAwemeId = "7123456789"; // 替换为真实视频ID
            String testCommentId = "123456789"; // 替换为真实评论ID
            
            System.out.println("\n=== 开始测试所有API ===");
            
            // 1. 测试获取用户视频帖子
            testAwemePost(douyinService, testSecUserId);
            
            // 2. 测试获取视频详情
            testAwemeDetail(douyinService, testAwemeId);
            
            // 3. 测试获取评论列表
            testCommentList(douyinService, testAwemeId);
            
            // 4. 测试获取评论回复
            testCommentReply(douyinService, testAwemeId, testCommentId);
            
            // 5. 测试获取用户信息
            testUserProfile(douyinService, testSecUserId);
            
        } catch (Exception e) {
            System.err.println("❌ 测试异常: " + e.getMessage());
            e.printStackTrace();
        } finally {
            context.close();
            System.out.println("\n📴 应用已关闭");
        }
    }

    private static void testAwemePost(DouyinService douyinService, String secUserId) {
        System.out.println("\n📱 测试1: 获取用户视频帖子");
        try {
            JSONObject result = douyinService.getAwemePost(secUserId, 0L, 10, null);
            System.out.println("状态码: " + result.getInt("status_code"));
            if (result.getInt("status_code") == 0) {
                System.out.println("✅ 用户视频帖子获取成功");
                if (result.containsKey("aweme_list")) {
                    System.out.println("视频数量: " + result.getJSONArray("aweme_list").size());
                }
            } else {
                System.out.println("❌ 失败: " + result.getStr("status_msg"));
            }
        } catch (Exception e) {
            System.out.println("❌ 异常: " + e.getMessage());
        }
    }

    private static void testAwemeDetail(DouyinService douyinService, String awemeId) {
        System.out.println("\n🎬 测试2: 获取视频详情");
        try {
            JSONObject result = douyinService.getAwemeDetail(awemeId, null);
            System.out.println("状态码: " + result.getInt("status_code"));
            if (result.getInt("status_code") == 0) {
                System.out.println("✅ 视频详情获取成功");
                if (result.containsKey("aweme_detail")) {
                    JSONObject detail = result.getJSONObject("aweme_detail");
                    System.out.println("视频描述: " + detail.getStr("desc"));
                }
            } else {
                System.out.println("❌ 失败: " + result.getStr("status_msg"));
            }
        } catch (Exception e) {
            System.out.println("❌ 异常: " + e.getMessage());
        }
    }

    private static void testCommentList(DouyinService douyinService, String awemeId) {
        System.out.println("\n💬 测试3: 获取评论列表");
        try {
            JSONObject result = douyinService.getCommentList(awemeId, 0L, 20, null);
            System.out.println("状态码: " + result.getInt("status_code"));
            if (result.getInt("status_code") == 0) {
                System.out.println("✅ 评论列表获取成功");
                if (result.containsKey("comments")) {
                    System.out.println("评论数量: " + result.getJSONArray("comments").size());
                }
            } else {
                System.out.println("❌ 失败: " + result.getStr("status_msg"));
            }
        } catch (Exception e) {
            System.out.println("❌ 异常: " + e.getMessage());
        }
    }

    private static void testCommentReply(DouyinService douyinService, String awemeId, String commentId) {
        System.out.println("\n🗨️ 测试4: 获取评论回复");
        try {
            JSONObject result = douyinService.getCommentReply(awemeId, commentId, 0L, 20, null);
            System.out.println("状态码: " + result.getInt("status_code"));
            if (result.getInt("status_code") == 0) {
                System.out.println("✅ 评论回复获取成功");
                if (result.containsKey("comments")) {
                    System.out.println("回复数量: " + result.getJSONArray("comments").size());
                }
            } else {
                System.out.println("❌ 失败: " + result.getStr("status_msg"));
            }
        } catch (Exception e) {
            System.out.println("❌ 异常: " + e.getMessage());
        }
    }

    private static void testUserProfile(DouyinService douyinService, String secUserId) {
        System.out.println("\n👤 测试5: 获取用户信息");
        try {
            JSONObject result = douyinService.getUserProfile(secUserId, null);
            System.out.println("状态码: " + result.getInt("status_code"));
            if (result.getInt("status_code") == 0) {
                System.out.println("✅ 用户信息获取成功");
                if (result.containsKey("user")) {
                    JSONObject user = result.getJSONObject("user");
                    System.out.println("用户昵称: " + user.getStr("nickname"));
                    System.out.println("粉丝数: " + user.getLong("follower_count"));
                }
            } else {
                System.out.println("❌ 失败: " + result.getStr("status_msg"));
            }
        } catch (Exception e) {
            System.out.println("❌ 异常: " + e.getMessage());
        }
    }
}