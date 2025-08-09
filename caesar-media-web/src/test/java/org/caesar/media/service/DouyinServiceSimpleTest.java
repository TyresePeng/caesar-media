package org.caesar.media.service;

import cn.hutool.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * DouyinService 简单测试类
 * 启动SpringBoot，填入真实数据测试
 *
 * @author peng.guo
 */
@SpringBootTest
class DouyinServiceSimpleTest {

    @Autowired
    private DouyinService douyinService;

    @Test
    void testGetAwemePost() {
        // ==== 请在这里填入你的测试数据 ====
        String secUserId = "MS4wLjABAAAAT9yh4Ay75Ggp1Uq58BQFQCUSWSapkg6PPgzGjs1R-2o";  // 从抖音用户页面URL获取
        Long maxCursor = 0L;  // 首次请求使用0
        Integer count = 10;   // 获取数量
        Long userId = null;   // 用户池ID，null表示使用默认

        // ==== 开始测试 ====
        System.out.println("🚀 开始测试抖音用户视频获取...");
        System.out.println("参数: secUserId=" + secUserId + ", maxCursor=" + maxCursor + ", count=" + count);

        try {
            JSONObject result = douyinService.getAwemePost(secUserId, maxCursor, count, userId);

            System.out.println("\n📋 测试结果:");
            System.out.println("状态码: " + result.getInt("status_code"));
            System.out.println("消息: " + result.getStr("status_msg"));

            if (result.getInt("status_code") == 0) {
                System.out.println("✅ 成功！");
                System.out.println("视频数量: " + result.getJSONArray("aweme_list").size());
                System.out.println("是否有更多: " + (result.getInt("has_more") == 1 ? "是" : "否"));

                // 打印完整响应（可选）
                // System.out.println("\n完整响应:");
                // System.out.println(result.toStringPretty());

            } else {
                System.out.println("❌ 失败: " + result.getStr("status_msg"));
            }

        } catch (Exception e) {
            System.out.println("❌ 异常: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    void testGetAwemePostWithUserSession() {
        // ==== 测试使用指定用户session ====
        String secUserId = "替换为真实的sec_user_id";
        Long userId = 1L;  // 替换为用户池中真实的用户ID

        System.out.println("🚀 测试使用指定用户session...");
        System.out.println("参数: secUserId=" + secUserId + ", userId=" + userId);

        try {
            JSONObject result = douyinService.getAwemePost(secUserId, 0L, 5, userId);

            System.out.println("\n📋 指定用户测试结果:");
            System.out.println("状态码: " + result.getInt("status_code"));

            if (result.getInt("status_code") == 0) {
                System.out.println("✅ 使用指定用户session成功！");
            } else {
                System.out.println("❌ 失败: " + result.getStr("status_msg"));
            }

        } catch (Exception e) {
            System.out.println("❌ 异常: " + e.getMessage());
        }
    }
}
