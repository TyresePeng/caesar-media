package org.caesar.media.test;

import cn.hutool.json.JSONObject;
import org.caesar.media.CaesarMediaApplication;
import org.caesar.media.service.DouyinService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 直接启动SpringBoot测试DouyinService
 * 可以直接运行main方法进行测试
 * 
 * @author peng.guo
 */
public class DouyinServiceTestRunner {

    public static void main(String[] args) {
        System.out.println("🚀 启动SpringBoot应用测试DouyinService...");
        
        // 启动SpringBoot应用
        ConfigurableApplicationContext context = SpringApplication.run(CaesarMediaApplication.class, args);
        
        try {
            // 获取DouyinService Bean
            DouyinService douyinService = context.getBean(DouyinService.class);
            System.out.println("✅ DouyinService 注入成功");
            
            // ==== 在这里填入你的测试数据 ====
            testGetAwemePost(douyinService);
            
        } catch (Exception e) {
            System.err.println("❌ 测试异常: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 关闭应用上下文
            context.close();
            System.out.println("📴 应用已关闭");
        }
    }

    private static void testGetAwemePost(DouyinService douyinService) {
        System.out.println("\n=== 开始测试 getAwemePost 方法 ===");
        
        // TODO: 请替换为真实的测试数据
        String secUserId = "MS4wLjABAAAA_5WgHcy_8m8_mwTKvQWudg4gkOhm1-2oj2p7Qw6Y1Y8";  // 替换为真实值
        Long maxCursor = 0L;
        Integer count = 5;
        Long userId = null;  // 或者指定用户池中的用户ID
        
        System.out.println("📝 测试参数:");
        System.out.println("  secUserId: " + secUserId);
        System.out.println("  maxCursor: " + maxCursor);
        System.out.println("  count: " + count);
        System.out.println("  userId: " + userId);
        System.out.println();
        
        try {
            System.out.println("⏳ 正在调用 DouyinService.getAwemePost...");
            
            // 调用方法
            JSONObject result = douyinService.getAwemePost(secUserId, maxCursor, count, userId);
            
            // 输出结果
            System.out.println("📊 响应结果:");
            System.out.println("状态码: " + result.getInt("status_code"));
            
            if (result.getInt("status_code") == 0) {
                System.out.println("✅ 请求成功!");
                
                if (result.containsKey("aweme_list")) {
                    int videoCount = result.getJSONArray("aweme_list").size();
                    System.out.println("📱 获取视频数量: " + videoCount);
                    
                    // 显示前几个视频信息
                    for (int i = 0; i < Math.min(videoCount, 3); i++) {
                        JSONObject video = result.getJSONArray("aweme_list").getJSONObject(i);
                        System.out.println("  视频" + (i+1) + ": " + video.getStr("aweme_id") + " - " + getDesc(video));
                    }
                    
                    // 分页信息
                    if (result.getInt("has_more") == 1) {
                        System.out.println("🔄 还有更多数据，下次游标: " + result.getStr("max_cursor"));
                    } else {
                        System.out.println("✅ 已获取所有数据");
                    }
                }
                
                // 可选：打印完整响应
                System.out.println("\n📋 完整响应数据:");
                System.out.println(result.toStringPretty());
                
            } else {
                System.out.println("❌ 请求失败");
                System.out.println("错误信息: " + result.getStr("status_msg"));
                
                // 错误处理建议
                printErrorAdvice(result.getInt("status_code"));
            }
            
        } catch (Exception e) {
            System.err.println("❌ 方法调用异常: " + e.getMessage());
            e.printStackTrace();
            
            System.out.println("\n🔧 可能的问题:");
            System.out.println("1. PlaywrightFactoryPool 未正确初始化");
            System.out.println("2. 网络连接问题");
            System.out.println("3. 参数配置错误");
            System.out.println("4. 抖音反爬限制");
        }
        
        System.out.println("\n=== 测试完成 ===");
    }

    private static String getDesc(JSONObject video) {
        String desc = video.getStr("desc");
        if (desc == null || desc.trim().isEmpty()) {
            return "[无描述]";
        }
        return desc.length() > 50 ? desc.substring(0, 50) + "..." : desc;
    }

    private static void printErrorAdvice(int statusCode) {
        System.out.println("\n💡 错误处理建议:");
        switch (statusCode) {
            case 10000:
                System.out.println("- 检查 sec_user_id 是否正确");
                System.out.println("- 从抖音用户页面URL获取正确的ID");
                break;
            case 10001:
                System.out.println("- 用户不存在或已注销");
                System.out.println("- 请确认用户ID有效");
                break;
            case 10002:
                System.out.println("- 用户设置了隐私，无法获取视频");
                break;
            case 10004:
                System.out.println("- 需要登录状态");
                System.out.println("- 请确保用户池中有有效的登录用户");
                break;
            default:
                System.out.println("- 检查服务配置和网络连接");
                System.out.println("- 查看应用日志获取详细信息");
        }
    }
}