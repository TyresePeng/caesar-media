package org.caesar.crawler.live.douyin.client.test;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.caesar.crawler.live.douyin.client.client.DouyinClient;
import org.junit.jupiter.api.Test;

/**
 * 新增抖音API接口测试类 - 类似example77的简洁测试风格
 *
 * @author peng.guo
 * @create 2025-08-07
 */
@Slf4j
class DouyinNewApisTest {

    // 真实的Cookie，可以从浏览器复制
    String cookie = "UIFID_TEMP=43978cd9ea816a7d34a6416f01236307be591e58382e2d59dc373240de143c21a9d5039a38c4d9ec287d714a43af19a02446ef5403e29512aa52aaae3c90b4cd0d028d028afb94ca0ae1644d7ec7ef79; hevc_supported=true; UIFID=43978cd9ea816a7d34a6416f01236307be591e58382e2d59dc373240de143c21a9d5039a38c4d9ec287d714a43af19a0736f1e256c0ef55deb488602834d8a3424b612c2142088917e10e5ccb08653d7058226f82bde65857136659d8e157c92e847d4bdec8bb3dc0c985ec4e7720172743c59c5140d871e35e614ef8b8751fa26fc67f6a6ac5b8bb6cdaaf4dce5ffc9b9348e246cc5ec3d0feb8e6119cc22cb; bd_ticket_guard_client_web_domain=2; is_staff_user=false; SelfTabRedDotControl=%5B%5D; __security_server_data_status=1; SEARCH_RESULT_LIST_TYPE=%22single%22; my_rd=2; live_use_vvc=%22false%22; enter_pc_once=1; is_dash_user=1; __security_mc_1_s_sdk_sign_data_key_sso=b89bacb2-43dc-800c; n_mh=9-mIeuD4wZnlYrrOvfzG3MuT6aQmCUtmr8FxV8Kl8xY; __druidClientInfo=JTdCJTIyY2xpZW50V2lkdGglMjIlM0EyOTglMkMlMjJjbGllbnRIZWlnaHQlMjIlM0E1ODklMkMlMjJ3aWR0aCUyMiUzQTI5OCUyQyUyMmhlaWdodCUyMiUzQTU4OSUyQyUyMmRldmljZVBpeGVsUmF0aW8lMjIlM0EyJTJDJTIydXNlckFnZW50JTIyJTNBJTIyTW96aWxsYSUyRjUuMCUyMChNYWNpbnRvc2glM0IlMjBJbnRlbCUyME1hYyUyME9TJTIwWCUyMDEwXzE1XzcpJTIwQXBwbGVXZWJLaXQlMkY1MzcuMzYlMjAoS0hUTUwlMkMlMjBsaWtlJTIwR2Vja28pJTIwQ2hyb21lJTJGMTM4LjAuMC4wJTIwU2FmYXJpJTJGNTM3LjM2JTIyJTdE; __security_mc_1_s_sdk_crypt_sdk=583ba164-4609-a83f; passport_csrf_token=c4de5a6ff59a5214ed69c16d91e161db; passport_csrf_token_default=c4de5a6ff59a5214ed69c16d91e161db; __security_mc_1_s_sdk_cert_key=5be3b24d-4cf2-a808; publish_badge_show_info=%220%2C0%2C0%2C1754197188089%22; h265ErrorNum=-1; passport_assist_user=ClMnoK1G4klggy-SabXZ7C9s7x9OxKuFdNSK__Kbq9u41nWHuJtcLdpl6lIaq5uWHey_cvrtTIg53npM_a4jfndMWOUsvo9CYSBQpVDJy6XBLhUwoRpKCjwAAAAAAAAAAAAAT09WycszNxduzEIFSodd0o1glC0-jdnLyrIPsXcwaClP7ehYoCOkYcIuMkaOhUW7BSUQ-sP4DRiJr9ZUIAEiAQO1DNGn; sid_guard=9232255446b2c339d6c236e2769d3bef%7C1754275493%7C5183999%7CFri%2C+03-Oct-2025+02%3A44%3A52+GMT; uid_tt=80c1fb10a3bd4577c1727311b66fa3d23c201ec5ecace3f23f0226eeb4bc7c93; uid_tt_ss=80c1fb10a3bd4577c1727311b66fa3d23c201ec5ecace3f23f0226eeb4bc7c93; sid_tt=9232255446b2c339d6c236e2769d3bef; sessionid=9232255446b2c339d6c236e2769d3bef; sessionid_ss=9232255446b2c339d6c236e2769d3bef; session_tlb_tag=sttt%7C1%7CkjIlVEaywznWwjbidp077__________y2wEiyfgwHa0JKD8NoGnMppVU16fi9AenqrOzgrr5Ffg%3D; sid_ucp_v1=1.0.0-KGJiYTdkMjUzN2U3NWY3YjkzMDYxMmZjYTA0ZjBiMzI4YmY1MDg5NmYKIgi6iNiqoI_6n2gQpb3AxAYY7zEgDDCj1__BBjgFQPsHSAQaAmxxIiA5MjMyMjU1NDQ2YjJjMzM5ZDZjMjM2ZTI3NjlkM2JlZg; ssid_ucp_v1=1.0.0-KGJiYTdkMjUzN2U3NWY3YjkzMDYxMmZjYTA0ZjBiMzI4YmY1MDg5NmYKIgi6iNiqoI_6n2gQpb3AxAYY7zEgDDCj1__BBjgFQPsHSAQaAmxxIiA5MjMyMjU1NDQ2YjJjMzM5ZDZjMjM2ZTI3NjlkM2JlZg; login_time=1754275493005; __security_mc_1_s_sdk_sign_data_key_web_protect=5fdf6b0d-497c-83ae; _bd_ticket_crypt_cookie=124829ec03d3553a94a89e5ab6f59cec; volume_info=%7B%22isUserMute%22%3Afalse%2C%22isMute%22%3Atrue%2C%22volume%22%3A0.973%7D; __live_version__=%221.1.3.6971%22; live_can_add_dy_2_desktop=%221%22; download_guide=%223%2F20250803%2F1%22; WallpaperGuide=%7B%22showTime%22%3A1754488319461%2C%22closeTime%22%3A0%2C%22showCount%22%3A6%2C%22cursor1%22%3A85%2C%22cursor2%22%3A64%2C%22hoverTime%22%3A1752409903584%7D; FOLLOW_NUMBER_YELLOW_POINT_INFO=%22MS4wLjABAAAAdB8i76Iihl4tI5MT1GcQtdalkUrTddThryPcHELJoDb_hPqex6MyXYlXYIa5i6_g%2F1754582400000%2F0%2F1754581970130%2F0%22; stream_player_status_params=%22%7B%5C%22is_auto_play%5C%22%3A0%2C%5C%22is_full_screen%5C%22%3A0%2C%5C%22is_full_webscreen%5C%22%3A1%2C%5C%22is_mute%5C%22%3A1%2C%5C%22is_speed%5C%22%3A1%2C%5C%22is_visible%5C%22%3A0%7D%22; FOLLOW_LIVE_POINT_INFO=%22MS4wLjABAAAAdB8i76Iihl4tI5MT1GcQtdalkUrTddThryPcHELJoDb_hPqex6MyXYlXYIa5i6_g%2F1754582400000%2F1754582030145%2F0%2F1754582570130%22; playRecommendGuideTagCount=1; totalRecommendGuideTagCount=1; strategyABtestKey=%221754582606.927%22; bd_ticket_guard_client_data=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWl0ZXJhdGlvbi12ZXJzaW9uIjoxLCJiZC10aWNrZXQtZ3VhcmQtcmVlLXB1YmxpYy1rZXkiOiJCR2dQMDRqZW8zTHdCRjdqVnVXOThyYmdabktIbWU2WnB6WklUcmdCeEJPZnFZRlArL291cHBab0VpUVl1SmNBM0RHSjd1TFExQXk5QXh3cHVDdkwzbjQ9IiwiYmQtdGlja2V0LWd1YXJkLXdlYi12ZXJzaW9uIjoyfQ%3D%3D; odin_tt=0fa4027430e8a96376cf74d173d0a2cddd40f3e78c12475f16bed608eb8a63af87f239a3794eb8dab3d052077fdb5354304c164250f01f0d2ddf3e66336ecd065a258c5f8c33427382a4f585e72f8833; ttwid=1%7C2-5p65jHF-vx4moYl19hvBRcrE2SiRBTeamcfAy7W4c%7C1754586019%7C95287c3c5ee5344d23ee07e6b48ca13dfd3ec91ba1a2dcaed52778accfb73f30; biz_trace_id=0be37c71; sdk_source_info=7e276470716a68645a606960273f276364697660272927676c715a6d6069756077273f276364697660272927666d776a68605a607d71606b766c6a6b5a7666776c7571273f275e58272927666a6b766a69605a696c6061273f27636469766027292762696a6764695a7364776c6467696076273f275e582729277672715a646971273f2763646976602729277f6b5a666475273f2763646976602729276d6a6e5a6b6a716c273f2763646976602729276c6b6f5a7f6367273f27636469766027292771273f27363137353735333d3031303234272927676c715a75776a716a666a69273f2763646976602778; bit_env=X9KCsRomaZN9O_m5MLDJvImbZyP-V1pl2R2phS4KAHhTniZGfziPtOOP-Hn-i3kqXZBTl-mAKqdBuIg8z6_CL1akNWSayMFYHy_Iwu2fGuYioLY2XXPvRLYdCkxP_Zm-UDnaJe_hUKOebE5wNfIKImzQUvi6bCuBJH97VZtksrfaTFG8kjox3F9xH4weypnKjXVxRi5yNqSY_dNLOv20ntGfUv6g80FIfpbleiW8ZE5YbQUB__R5tmfIWiBQYetBGcCuKmBrH5M35lt2Q2Q0WvS7xu7S6xOdqtNa-FbBAGhrgFXzv4PtBxFNh0BcHCPgPLXffFEbydGywGrUwOAyRBnnni_KW41B5Awf0xsZJeL6U5nYaTbWosW2uduRGH4AT5VOL5XqaHQAUFz8_ijfN26hftLjJpBr-lH0BR5f7KCXSoAtslWAxjLmBoGV1VZx1XGaWDoVnbFby6XdYpHZaCM-FyP7Es2YggLMsM1yuTwMH0IEKSUeLlLie1hxG1G8iRC227egeoJF88dOpxkANTI2aSrHwijnuMlNkEIHBOQ%3D; gulu_source_res=eyJwX2luIjoiNWRiODg4ZDk2OWY5MmIyYmRkNjBkMTk5NjFmM2EzM2M0MDE5MTQ4NjExYzMyZGJjNzllZGE3NTdkNTBmMGQwMyJ9; passport_auth_mix_state=e0a970w5w8qld0gtcw90ht56ger9nrmg; IsDouyinActive=true; home_can_add_dy_2_desktop=%220%22; stream_recommend_feed_params=%22%7B%5C%22cookie_enabled%5C%22%3Atrue%2C%5C%22screen_width%5C%22%3A1512%2C%5C%22screen_height%5C%22%3A982%2C%5C%22browser_online%5C%22%3Atrue%2C%5C%22cpu_core_num%5C%22%3A8%2C%5C%22device_memory%5C%22%3A8%2C%5C%22downlink%5C%22%3A10%2C%5C%22effective_type%5C%22%3A%5C%224g%5C%22%2C%5C%22round_trip_time%5C%22%3A100%7D%22";

    @Test
    void testAwemePost() {
        // 测试获取用户视频帖子 - 类似example77
        JSONObject awemePost = DouyinClient.getAwemePost(cookie, "MS4wLjABAAAAcE_cnc5xOhnQvnOSUWTN3yayo1Ba4-MCyzytkfQsKyA", 0, 10);
        System.err.println("=== 用户视频帖子 ===");
        System.err.println(awemePost);
        System.err.println("testAwemePost 完成");
    }

    @Test
    void testAwemeDetail() {
        // 测试获取视频详情 - 替换为真实的视频ID
        JSONObject awemeDetail = DouyinClient.getAwemeDetail(cookie, "7202432992642387233");
        System.err.println("=== 视频详情 ===");
        System.err.println(awemeDetail);
        System.err.println("testAwemeDetail 完成");
    }


    @Test
    void testUserProfile() {
        // 测试获取用户信息
        JSONObject userProfile = DouyinClient.getUserProfile(cookie, "MS4wLjABAAAAT9yh4Ay75Ggp1Uq58BQFQCUSWSapkg6PPgzGjs1R-2o");
        System.err.println("=== 用户信息 ===");
        System.err.println(userProfile);
        System.err.println("testUserProfile 完成");
    }

    @Test
    void testCommentList() {
        // 测试获取评论列表 - 替换为真实的视频ID
        JSONObject commentList = DouyinClient.getCommentList(cookie, "7535111605118963003", 0, 20);
        System.err.println("=== 评论列表 ===");
        System.err.println(commentList);
        System.err.println("testCommentList 完成");
    }

    @Test
    void testCommentReply() {
        // 测试获取评论回复 - 需要先从评论列表中获取真实的评论ID
        JSONObject commentReply = DouyinClient.getCommentReply(cookie, "7535111605118963003", "7432054068965663011", 0, 20);
        System.err.println("=== 评论回复 ===");
        System.err.println(commentReply);
        System.err.println("testCommentReply 完成");
    }


    @Test
    void testAllApis() {
        // 测试所有API - 一次性运行
        String secUserId = "MS4wLjABAAAAT9yh4Ay75Ggp1Uq58BQFQCUSWSapkg6PPgzGjs1R-2o";
        String awemeId = "7432053729534717194"; // 替换为真实视频ID
        String commentId = "7432054068965663011"; // 替换为真实评论ID

        System.err.println("\n🚀 开始测试所有抖音API...\n");

        try {
            // 1. 用户视频帖子
            JSONObject posts = DouyinClient.getAwemePost(cookie, secUserId, 0, 5);
            System.err.println("1. 用户视频帖子: " + (posts.getInt("status_code") == 0 ? "✅成功" : "❌失败"));

            // 2. 视频详情
            JSONObject detail = DouyinClient.getAwemeDetail(cookie, awemeId);
            System.err.println("2. 视频详情: " + (detail.getInt("status_code") == 0 ? "✅成功" : "❌失败"));

            // 3. 评论列表
            JSONObject comments = DouyinClient.getCommentList(cookie, awemeId, 0, 10);
            System.err.println("3. 评论列表: " + (comments.getInt("status_code") == 0 ? "✅成功" : "❌失败"));

            // 4. 评论回复
            JSONObject replies = DouyinClient.getCommentReply(cookie, awemeId, commentId, 0, 10);
            System.err.println("4. 评论回复: " + (replies.getInt("status_code") == 0 ? "✅成功" : "❌失败"));

            // 5. 用户信息
            JSONObject profile = DouyinClient.getUserProfile(cookie, secUserId);
            System.err.println("5. 用户信息: " + (profile.getInt("status_code") == 0 ? "✅成功" : "❌失败"));

            System.err.println("\n🎉 所有API测试完成!");

        } catch (Exception e) {
            System.err.println("❌ 测试异常: " + e.getMessage());
        }
    }

    @Test
    void example88() {
        // 类似example77的极简测试 - 获取用户视频帖子
        JSONObject awemePost = DouyinClient.getAwemePost(cookie, "MS4wLjABAAAAT9yh4Ay75Ggp1Uq58BQFQCUSWSapkg6PPgzGjs1R-2o", 0, 10);
        System.err.println(awemePost);
        System.err.println("example88 完成");
    }

    @Test
    void example99() {
        // 获取视频详情的极简测试
        JSONObject awemeDetail = DouyinClient.getAwemeDetail(cookie, "7535111605118963003");
        System.err.println(awemeDetail);
        System.err.println("example99 完成");
    }

    @Test
    void example100() {
        // 获取用户信息的极简测试
        JSONObject userProfile = DouyinClient.getUserProfile(cookie, "MS4wLjABAAAAT9yh4Ay75Ggp1Uq58BQFQCUSWSapkg6PPgzGjs1R-2o");
        System.err.println(userProfile);
        System.err.println("example100 完成");
    }

    @Test
    void example101() {
        // 获取评论列表的极简测试
        JSONObject commentList = DouyinClient.getCommentList(cookie, "7432053729534717194", 0, 20);
        System.err.println(commentList);
        System.err.println("example101 完成");
    }

    @Test
    void example102() {
        // 获取评论回复的极简测试
        JSONObject commentReply = DouyinClient.getCommentReply(cookie, "7432053729534717194", "7432054068965663011", 0, 20);
        System.err.println(commentReply);
        System.err.println("example102 完成");
    }

    // 无Cookie测试 - 测试不带Cookie的情况
    @Test
    void testNoCookie() {
        System.err.println("\n🧪 测试无Cookie情况...\n");

        try {
            // 不带Cookie的用户视频获取
            JSONObject noCookiePost = DouyinClient.getAwemePost("MS4wLjABAAAAT9yh4Ay75Ggp1Uq58BQFQCUSWSapkg6PPgzGjs1R-2o", 0, 5);
            System.err.println("无Cookie用户视频: " + (noCookiePost.getInt("status_code") == 0 ? "✅成功" : "❌失败"));

            // 不带Cookie的视频详情
            JSONObject noCookieDetail = DouyinClient.getAwemeDetail("7432053729534717194");
            System.err.println("无Cookie视频详情: " + (noCookieDetail.getInt("status_code") == 0 ? "✅成功" : "❌失败"));

            // 不带Cookie的用户信息
            JSONObject noCookieProfile = DouyinClient.getUserProfile("MS4wLjABAAAAT9yh4Ay75Ggp1Uq58BQFQCUSWSapkg6PPgzGjs1R-2o");
            System.err.println("无Cookie用户信息: " + (noCookieProfile.getInt("status_code") == 0 ? "✅成功" : "❌失败"));

        } catch (Exception e) {
            System.err.println("无Cookie测试异常: " + e.getMessage());
        }
    }
}
