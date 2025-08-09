package org.caesar.crawler.live.douyin.client.test;

import cn.hutool.json.JSONObject;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.UnknownFieldSet;
import forward.ForwardMsgPlugin;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.caesar.crawler.live.douyin.client.client.DouyinClient;
import org.caesar.crawler.live.douyin.client.client.DouyinLiveChatClient;
import org.caesar.crawler.live.douyin.client.config.DouyinLiveChatClientConfig;
import org.caesar.crawler.live.douyin.client.handler.DouyinBinaryFrameHandler;
import org.caesar.crawler.live.douyin.client.listener.IDouyinMsgListener;
import org.caesar.crawler.live.douyin.codec.api.DouyinApis;
import org.caesar.crawler.live.douyin.codec.constant.DouyinCmdEnum;
import org.caesar.crawler.live.douyin.codec.msg.*;
import org.caesar.crawler.live.douyin.codec.protobuf.GiftMessage;
import org.caesar.crawler.live.douyin.codec.protobuf.Message;
import org.caesar.crawler.live.douyin.codec.room.DouyinRoomInitResult;
import org.caesar.crawler.live.netty.base.enums.PublishTimeType;
import org.caesar.crawler.live.netty.base.enums.SearchChannelType;
import org.caesar.crawler.live.netty.base.enums.SearchSortType;
import org.caesar.crawler.live.netty.base.msg.ICmdMsg;
import org.caesar.crawler.live.netty.base.msg.IMsg;
import org.caesar.crawler.live.netty.client.enums.ClientStatusEnums;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author mjz
 * @date 2024/1/2
 */
@Slf4j
class DouyinLiveChatClientTest {

    static Object lock = new Object();
    DouyinLiveChatClient client;


    @Test
    void queryKeyWord() throws InterruptedException {
        JSONObject ret = DouyinClient.queryKeyWord("小黑子", 0,
                20, PublishTimeType.UNLIMITED, SearchChannelType.GENERAL, SearchSortType.LATEST);
        System.err.println( ret);
    }

    @Test
    void roomInfo() throws InterruptedException {
        DouyinRoomInitResult douyinRoomInitResult = DouyinApis.roomInit(520738666992L);
        System.err.println(douyinRoomInitResult);
    }

    @Test
    void example2() throws InterruptedException {
        String cookie = "UIFID_TEMP=43978cd9ea816a7d34a6416f01236307be591e58382e2d59dc373240de143c21a9d5039a38c4d9ec287d714a43af19a02446ef5403e29512aa52aaae3c90b4cd0d028d028afb94ca0ae1644d7ec7ef79; hevc_supported=true; UIFID=43978cd9ea816a7d34a6416f01236307be591e58382e2d59dc373240de143c21a9d5039a38c4d9ec287d714a43af19a0736f1e256c0ef55deb488602834d8a3424b612c2142088917e10e5ccb08653d7058226f82bde65857136659d8e157c92e847d4bdec8bb3dc0c985ec4e7720172743c59c5140d871e35e614ef8b8751fa26fc67f6a6ac5b8bb6cdaaf4dce5ffc9b9348e246cc5ec3d0feb8e6119cc22cb; passport_csrf_token=5d4e77c350238c54768bce045de7caea; passport_csrf_token_default=5d4e77c350238c54768bce045de7caea; __security_mc_1_s_sdk_crypt_sdk=85685131-49bf-8ba8; __security_mc_1_s_sdk_cert_key=9155a2e5-47c9-bcee; bd_ticket_guard_client_web_domain=2; is_staff_user=false; SelfTabRedDotControl=%5B%5D; __security_server_data_status=1; SEARCH_RESULT_LIST_TYPE=%22single%22; my_rd=2; live_use_vvc=%22false%22; h265ErrorNumNew=-1; enter_pc_once=1; h265ErrorNum=-1; publish_badge_show_info=%220%2C0%2C0%2C1751183133976%22; download_guide=%223%2F20250629%2F0%22; WallpaperGuide=%7B%22showTime%22%3A1751281399087%2C%22closeTime%22%3A0%2C%22showCount%22%3A1%2C%22cursor1%22%3A10%2C%22cursor2%22%3A2%7D; is_dash_user=1; volume_info=%7B%22isUserMute%22%3Afalse%2C%22isMute%22%3Atrue%2C%22volume%22%3A0.6%7D; stream_player_status_params=%22%7B%5C%22is_auto_play%5C%22%3A0%2C%5C%22is_full_screen%5C%22%3A0%2C%5C%22is_full_webscreen%5C%22%3A0%2C%5C%22is_mute%5C%22%3A1%2C%5C%22is_speed%5C%22%3A1%2C%5C%22is_visible%5C%22%3A0%7D%22; FOLLOW_NUMBER_YELLOW_POINT_INFO=%22MS4wLjABAAAAhQ8eiBM3jufM08Ki6LacfX7i0gyf5tAYVhdw8JPo-v-77o7NCLfb-xwrR3gdfhVU%2F1751558400000%2F0%2F0%2F1751537047668%22; __security_mc_1_s_sdk_sign_data_key_sso=b89bacb2-43dc-800c; FOLLOW_LIVE_POINT_INFO=%22MS4wLjABAAAAhQ8eiBM3jufM08Ki6LacfX7i0gyf5tAYVhdw8JPo-v-77o7NCLfb-xwrR3gdfhVU%2F1751558400000%2F0%2F1751536660348%2F0%22; webcast_leading_last_show_time=1751536807710; webcast_leading_total_show_times=1; webcast_local_quality=sd; n_mh=9-mIeuD4wZnlYrrOvfzG3MuT6aQmCUtmr8FxV8Kl8xY; passport_assist_user=ClOWXk_20NhxrdluPi7TrDeUhrvJ7bvvb65phGxf3_KpPmOdaJShShYUOFOClS3hu9wtliPeMtZ_pdVgmWzWo_KJaLemQrhIOGa5u82A3IjUN-2SxhpKCjwAAAAAAAAAAAAATzGl7yhvgXpi32FhliaYsVFVyrn7UCI2-mdS007QW9FyVjvnFIEndnbh40nXKpvBXA4Q8-f1DRiJr9ZUIAEiAQNEh51h; sid_guard=7d6d762f8ad0b182c3c3fa22d94e864a%7C1751595114%7C5184000%7CTue%2C+02-Sep-2025+02%3A11%3A54+GMT; uid_tt=74c3ea858c41647dbafd36f38dde28dfb5a1bc72ceb0bec019f6f7b6be6e1861; uid_tt_ss=74c3ea858c41647dbafd36f38dde28dfb5a1bc72ceb0bec019f6f7b6be6e1861; sid_tt=7d6d762f8ad0b182c3c3fa22d94e864a; sessionid=7d6d762f8ad0b182c3c3fa22d94e864a; sessionid_ss=7d6d762f8ad0b182c3c3fa22d94e864a; session_tlb_tag=sttt%7C15%7CfW12L4rQsYLDw_oi2U6GSv________-7FC2_Y1x_GvaSc-aMbRwf6hExLXI5W7AoFLdZLjJKAow%3D; sid_ucp_v1=1.0.0-KDdkY2M3NDM2ZjQzMjA0NWUwMTdiYmZmMzhiYmVhNjQzMGJhMGEwYWMKIgi6iNiqoI_6n2gQ6vCcwwYY7zEgDDCj1__BBjgHQPQHSAQaAmxxIiA3ZDZkNzYyZjhhZDBiMTgyYzNjM2ZhMjJkOTRlODY0YQ; ssid_ucp_v1=1.0.0-KDdkY2M3NDM2ZjQzMjA0NWUwMTdiYmZmMzhiYmVhNjQzMGJhMGEwYWMKIgi6iNiqoI_6n2gQ6vCcwwYY7zEgDDCj1__BBjgHQPQHSAQaAmxxIiA3ZDZkNzYyZjhhZDBiMTgyYzNjM2ZhMjJkOTRlODY0YQ; login_time=1751595114342; _bd_ticket_crypt_cookie=832713fd9594dcbd9b478bf8b6bdd0d2; __security_mc_1_s_sdk_sign_data_key_web_protect=831757ec-4eaf-aa28; __druidClientInfo=JTdCJTIyY2xpZW50V2lkdGglMjIlM0EyOTglMkMlMjJjbGllbnRIZWlnaHQlMjIlM0E1ODklMkMlMjJ3aWR0aCUyMiUzQTI5OCUyQyUyMmhlaWdodCUyMiUzQTU4OSUyQyUyMmRldmljZVBpeGVsUmF0aW8lMjIlM0EyJTJDJTIydXNlckFnZW50JTIyJTNBJTIyTW96aWxsYSUyRjUuMCUyMChNYWNpbnRvc2glM0IlMjBJbnRlbCUyME1hYyUyME9TJTIwWCUyMDEwXzE1XzcpJTIwQXBwbGVXZWJLaXQlMkY1MzcuMzYlMjAoS0hUTUwlMkMlMjBsaWtlJTIwR2Vja28pJTIwQ2hyb21lJTJGMTM4LjAuMC4wJTIwU2FmYXJpJTJGNTM3LjM2JTIyJTdE; __live_version__=%221.1.3.5120%22; live_can_add_dy_2_desktop=%221%22; stream_recommend_feed_params=%22%7B%5C%22cookie_enabled%5C%22%3Atrue%2C%5C%22screen_width%5C%22%3A1512%2C%5C%22screen_height%5C%22%3A982%2C%5C%22browser_online%5C%22%3Atrue%2C%5C%22cpu_core_num%5C%22%3A8%2C%5C%22device_memory%5C%22%3A8%2C%5C%22downlink%5C%22%3A10%2C%5C%22effective_type%5C%22%3A%5C%224g%5C%22%2C%5C%22round_trip_time%5C%22%3A100%7D%22; strategyABtestKey=%221751601485.543%22; biz_trace_id=ca5bcada; ttwid=1%7C2-5p65jHF-vx4moYl19hvBRcrE2SiRBTeamcfAy7W4c%7C1751601485%7Cd66251b370e1df7eb2c6c7582eb9f5e069f77d90f9c9e7d81ee94ef359b2f3c8; odin_tt=e36f3016f597908ac5aadbbb0edc627dfe0a85445683e9d84b5a5b6876d3d7710d0ed9e0bdfc65e4217bb76363c2de2846fa3b1bd68f80dfe7a5a6e1ecbc2b63; IsDouyinActive=true; home_can_add_dy_2_desktop=%221%22; bd_ticket_guard_client_data=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWl0ZXJhdGlvbi12ZXJzaW9uIjoxLCJiZC10aWNrZXQtZ3VhcmQtcmVlLXB1YmxpYy1rZXkiOiJCR2dQMDRqZW8zTHdCRjdqVnVXOThyYmdabktIbWU2WnB6WklUcmdCeEJPZnFZRlArL291cHBab0VpUVl1SmNBM0RHSjd1TFExQXk5QXh3cHVDdkwzbjQ9IiwiYmQtdGlja2V0LWd1YXJkLXdlYi12ZXJzaW9uIjoyfQ%3D%3D";
        log.error("cookie: {}", cookie);
        DouyinLiveChatClientConfig config = DouyinLiveChatClientConfig.builder()
//                .cookie(cookie)
                .roomId(520738666992L)
                .build();

        client = new DouyinLiveChatClient(config, new IDouyinMsgListener() {});
        client.connect();
        // 防止测试时直接退出
        client.send("......................");
        while (true) {
            synchronized (lock) {
                lock.wait();
            }
        }
    }

    @Test
    void example() throws InterruptedException {
        String cookie = "UIFID_TEMP=43978cd9ea816a7d34a6416f01236307be591e58382e2d59dc373240de143c21a9d5039a38c4d9ec287d714a43af19a02446ef5403e29512aa52aaae3c90b4cd0d028d028afb94ca0ae1644d7ec7ef79; hevc_supported=true; UIFID=43978cd9ea816a7d34a6416f01236307be591e58382e2d59dc373240de143c21a9d5039a38c4d9ec287d714a43af19a0736f1e256c0ef55deb488602834d8a3424b612c2142088917e10e5ccb08653d7058226f82bde65857136659d8e157c92e847d4bdec8bb3dc0c985ec4e7720172743c59c5140d871e35e614ef8b8751fa26fc67f6a6ac5b8bb6cdaaf4dce5ffc9b9348e246cc5ec3d0feb8e6119cc22cb; passport_csrf_token=5d4e77c350238c54768bce045de7caea; passport_csrf_token_default=5d4e77c350238c54768bce045de7caea; __security_mc_1_s_sdk_crypt_sdk=85685131-49bf-8ba8; __security_mc_1_s_sdk_cert_key=9155a2e5-47c9-bcee; bd_ticket_guard_client_web_domain=2; is_staff_user=false; SelfTabRedDotControl=%5B%5D; __security_server_data_status=1; SEARCH_RESULT_LIST_TYPE=%22single%22; my_rd=2; live_use_vvc=%22false%22; h265ErrorNumNew=-1; enter_pc_once=1; h265ErrorNum=-1; publish_badge_show_info=%220%2C0%2C0%2C1751183133976%22; download_guide=%223%2F20250629%2F0%22; WallpaperGuide=%7B%22showTime%22%3A1751281399087%2C%22closeTime%22%3A0%2C%22showCount%22%3A1%2C%22cursor1%22%3A10%2C%22cursor2%22%3A2%7D; is_dash_user=1; volume_info=%7B%22isUserMute%22%3Afalse%2C%22isMute%22%3Atrue%2C%22volume%22%3A0.6%7D; stream_player_status_params=%22%7B%5C%22is_auto_play%5C%22%3A0%2C%5C%22is_full_screen%5C%22%3A0%2C%5C%22is_full_webscreen%5C%22%3A0%2C%5C%22is_mute%5C%22%3A1%2C%5C%22is_speed%5C%22%3A1%2C%5C%22is_visible%5C%22%3A0%7D%22; strategyABtestKey=%221751514435.034%22; FOLLOW_NUMBER_YELLOW_POINT_INFO=%22MS4wLjABAAAAhQ8eiBM3jufM08Ki6LacfX7i0gyf5tAYVhdw8JPo-v-77o7NCLfb-xwrR3gdfhVU%2F1751558400000%2F0%2F0%2F1751537047668%22; __security_mc_1_s_sdk_sign_data_key_sso=b89bacb2-43dc-800c; FOLLOW_LIVE_POINT_INFO=%22MS4wLjABAAAAhQ8eiBM3jufM08Ki6LacfX7i0gyf5tAYVhdw8JPo-v-77o7NCLfb-xwrR3gdfhVU%2F1751558400000%2F0%2F1751536660348%2F0%22; webcast_leading_last_show_time=1751536807710; webcast_leading_total_show_times=1; webcast_local_quality=sd; n_mh=9-mIeuD4wZnlYrrOvfzG3MuT6aQmCUtmr8FxV8Kl8xY; stream_recommend_feed_params=%22%7B%5C%22cookie_enabled%5C%22%3Atrue%2C%5C%22screen_width%5C%22%3A1512%2C%5C%22screen_height%5C%22%3A982%2C%5C%22browser_online%5C%22%3Atrue%2C%5C%22cpu_core_num%5C%22%3A8%2C%5C%22device_memory%5C%22%3A8%2C%5C%22downlink%5C%22%3A9.2%2C%5C%22effective_type%5C%22%3A%5C%224g%5C%22%2C%5C%22round_trip_time%5C%22%3A150%7D%22; ttwid=1%7C2-5p65jHF-vx4moYl19hvBRcrE2SiRBTeamcfAy7W4c%7C1751595077%7Ce9107d68a161014b190f5e2f1086df451658a4000137a2c63cb93e0b9a4c3481; biz_trace_id=a0397ddb; passport_assist_user=ClOWXk_20NhxrdluPi7TrDeUhrvJ7bvvb65phGxf3_KpPmOdaJShShYUOFOClS3hu9wtliPeMtZ_pdVgmWzWo_KJaLemQrhIOGa5u82A3IjUN-2SxhpKCjwAAAAAAAAAAAAATzGl7yhvgXpi32FhliaYsVFVyrn7UCI2-mdS007QW9FyVjvnFIEndnbh40nXKpvBXA4Q8-f1DRiJr9ZUIAEiAQNEh51h; sid_guard=7d6d762f8ad0b182c3c3fa22d94e864a%7C1751595114%7C5184000%7CTue%2C+02-Sep-2025+02%3A11%3A54+GMT; uid_tt=74c3ea858c41647dbafd36f38dde28dfb5a1bc72ceb0bec019f6f7b6be6e1861; uid_tt_ss=74c3ea858c41647dbafd36f38dde28dfb5a1bc72ceb0bec019f6f7b6be6e1861; sid_tt=7d6d762f8ad0b182c3c3fa22d94e864a; sessionid=7d6d762f8ad0b182c3c3fa22d94e864a; sessionid_ss=7d6d762f8ad0b182c3c3fa22d94e864a; session_tlb_tag=sttt%7C15%7CfW12L4rQsYLDw_oi2U6GSv________-7FC2_Y1x_GvaSc-aMbRwf6hExLXI5W7AoFLdZLjJKAow%3D; sid_ucp_v1=1.0.0-KDdkY2M3NDM2ZjQzMjA0NWUwMTdiYmZmMzhiYmVhNjQzMGJhMGEwYWMKIgi6iNiqoI_6n2gQ6vCcwwYY7zEgDDCj1__BBjgHQPQHSAQaAmxxIiA3ZDZkNzYyZjhhZDBiMTgyYzNjM2ZhMjJkOTRlODY0YQ; ssid_ucp_v1=1.0.0-KDdkY2M3NDM2ZjQzMjA0NWUwMTdiYmZmMzhiYmVhNjQzMGJhMGEwYWMKIgi6iNiqoI_6n2gQ6vCcwwYY7zEgDDCj1__BBjgHQPQHSAQaAmxxIiA3ZDZkNzYyZjhhZDBiMTgyYzNjM2ZhMjJkOTRlODY0YQ; login_time=1751595114342; _bd_ticket_crypt_cookie=832713fd9594dcbd9b478bf8b6bdd0d2; __security_mc_1_s_sdk_sign_data_key_web_protect=831757ec-4eaf-aa28; home_can_add_dy_2_desktop=%221%22; __druidClientInfo=JTdCJTIyY2xpZW50V2lkdGglMjIlM0EyOTglMkMlMjJjbGllbnRIZWlnaHQlMjIlM0E1ODklMkMlMjJ3aWR0aCUyMiUzQTI5OCUyQyUyMmhlaWdodCUyMiUzQTU4OSUyQyUyMmRldmljZVBpeGVsUmF0aW8lMjIlM0EyJTJDJTIydXNlckFnZW50JTIyJTNBJTIyTW96aWxsYSUyRjUuMCUyMChNYWNpbnRvc2glM0IlMjBJbnRlbCUyME1hYyUyME9TJTIwWCUyMDEwXzE1XzcpJTIwQXBwbGVXZWJLaXQlMkY1MzcuMzYlMjAoS0hUTUwlMkMlMjBsaWtlJTIwR2Vja28pJTIwQ2hyb21lJTJGMTM4LjAuMC4wJTIwU2FmYXJpJTJGNTM3LjM2JTIyJTdE; __live_version__=%221.1.3.5120%22; live_can_add_dy_2_desktop=%221%22; IsDouyinActive=true; bd_ticket_guard_client_data=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWl0ZXJhdGlvbi12ZXJzaW9uIjoxLCJiZC10aWNrZXQtZ3VhcmQtcmVlLXB1YmxpYy1rZXkiOiJCR2dQMDRqZW8zTHdCRjdqVnVXOThyYmdabktIbWU2WnB6WklUcmdCeEJPZnFZRlArL291cHBab0VpUVl1SmNBM0RHSjd1TFExQXk5QXh3cHVDdkwzbjQ9IiwiYmQtdGlja2V0LWd1YXJkLXdlYi12ZXJzaW9uIjoyfQ%3D%3D; odin_tt=3160dbe1e3284cdb4b23946f826b0e0bfb364ec043d2aa8a72364b3dbdb364908c8d314c081f3bfee7e29efc8ee52b2791795149d71822f7559679c1375afcdd75d1e13b322979d8ca55f319681d2be0";
        log.error("cookie: {}", cookie);
        DouyinLiveChatClientConfig config = DouyinLiveChatClientConfig.builder()
                .cookie(cookie)
                .roomId(520738666992L)
                .build();

        client = new DouyinLiveChatClient(config, new IDouyinMsgListener() {
            @Override
            public void onMsg(IMsg msg) {
                log.debug("收到{}消息 {}", msg.getClass(), msg);
            }

            @Override
            public void onCmdMsg(DouyinCmdEnum cmd, ICmdMsg<DouyinCmdEnum> cmdMsg) {
                // log.debug("收到CMD消息{} {}", cmd, cmdMsg);
            }

            @Override
            public void onOtherCmdMsg(DouyinCmdEnum cmd, ICmdMsg<DouyinCmdEnum> cmdMsg) {
                log.debug("收到其他CMD消息 {}", cmd);
            }

            @Override
            public void onUnknownCmd(String cmdString, IMsg msg) {
                log.debug("收到未知CMD消息 {}", cmdString);
                Message douyin_cmd_msg = ((DouyinCmdMsg) msg).getMsg();
                ByteString payload = douyin_cmd_msg.getPayload();
                try {
                    UnknownFieldSet unknownFieldSet = UnknownFieldSet.parseFrom(payload);
                    Map<Integer, UnknownFieldSet.Field> map = unknownFieldSet.asMap();
                } catch (InvalidProtocolBufferException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onDanmuMsg(DouyinBinaryFrameHandler binaryFrameHandler, DouyinDanmuMsg msg) {
                log.info("{} 收到弹幕 {} {}({})：{}", binaryFrameHandler.getRoomId(), msg.getBadgeLevel() != 0 ? msg.getBadgeLevel() + msg.getBadgeName() : "", msg.getUsername(), msg.getUid(), msg.getContent());
            }

            @Override
            public void onGiftMsg(DouyinBinaryFrameHandler binaryFrameHandler, DouyinGiftMsg msg) {
                GiftMessage giftMessage = msg.getMsg();

                boolean combo = giftMessage.getGift().getCombo();

                long msgId = giftMessage.getCommon().getMsgId();
                long comboCount = giftMessage.getComboCount();
                long groupId = giftMessage.getGroupId();
                long groupCount = giftMessage.getGroupCount();
                long totalCount = giftMessage.getTotalCount();
                String traceId = giftMessage.getTraceId();
                long repeatCount = giftMessage.getRepeatCount();
                if (msg.getGiftCount() > 0) {
                    log.info("{} 收到礼物 {} {}({}) {} {}({})x{}({}) msgId:{}, groupId:{}, groupCount:{}, comboCount:{}, totalCount:{}, repeatCount:{}, traceId:{}, combo:{}", binaryFrameHandler.getRoomId(), msg.getBadgeLevel() != 0 ? msg.getBadgeLevel() + msg.getBadgeName() : "", msg.getUsername(), msg.getUid(), "赠送", msg.getGiftName(), msg.getGiftId(), msg.getGiftCount(), msg.getGiftPrice(), msgId, groupId, groupCount, comboCount, totalCount, repeatCount, traceId, combo);
                }
            }

            @Override
            public void onLikeMsg(DouyinBinaryFrameHandler binaryFrameHandler, DouyinLikeMsg msg) {
                log.info("{} 收到点赞 [{}] {}({})x{}", binaryFrameHandler.getRoomId(), msg.getBadgeLevel() != 0 ? msg.getBadgeLevel() + msg.getBadgeName() : "", msg.getUsername(), msg.getUid(), msg.getClickCount());
            }

            @Override
            public void onEnterRoomMsg(DouyinBinaryFrameHandler binaryFrameHandler, DouyinEnterRoomMsg msg) {
                log.info("{} {}({}) 进入直播间", msg.getBadgeLevel() != 0 ? msg.getBadgeLevel() + msg.getBadgeName() : "", msg.getUsername(), msg.getUid());
            }

            @Override
            public void onLiveStatusMsg(DouyinBinaryFrameHandler douyinBinaryFrameHandler, DouyinControlMsg douyinControlMsg) {
                log.error("状态变化: {}", douyinControlMsg.getMsg().getStatus());
            }

            @Override
            public void onRoomStatsMsg(DouyinBinaryFrameHandler binaryFrameHandler, DouyinRoomStatsMsg msg) {
                IDouyinMsgListener.super.onRoomStatsMsg(binaryFrameHandler, msg);
                log.info("{} 统计信息 累计点赞数: {}, 当前观看人数: {}, 累计观看人数: {}", binaryFrameHandler.getRoomId(), msg.getLikedCount(), msg.getWatchingCount(), msg.getWatchedCount());
            }

            @Override
            public void onSocialMsg(DouyinBinaryFrameHandler binaryFrameHandler, DouyinSocialMsg msg) {
                log.info("{} 社交消息 {} {} {}({})", binaryFrameHandler.getRoomId(), msg.getSocialAction(), msg.getBadgeLevel() != 0 ? msg.getBadgeLevel() + msg.getBadgeName() : "", msg.getUsername(), msg.getUid());
            }
        });

        client.addStatusChangeListener((evt, oldStatus, newStatus) -> {
            if (newStatus == ClientStatusEnums.CONNECTED) {
                log.warn("{} 已连接", client.getConfig().getRoomId());
                log.warn("直播间标题 {}", client.getRoomInitResult().getRoomTitle());
                log.warn("房间直播状态: {}", client.getRoomInitResult().getRoomLiveStatus());
            }
        });

        client.connect();

        //        ThreadUtil.execAsync(() -> {
//            ThreadUtil.sleep(5000);
//            client.disconnect();
//        });

        // 防止测试时直接退出
        client.send("....................................................................");
        while (true) {
            synchronized (lock) {
                lock.wait();
            }
        }
    }

    @Test
    void giftTest() throws Exception {
        DouyinLiveChatClient douyinLiveChatClientTest = new DouyinLiveChatClient(DouyinLiveChatClientConfig.builder()
                .roomId("567523334915")
//                .roomId("507978586806")
                .build());

        douyinLiveChatClientTest.addMsgListener(new IDouyinMsgListener() {
            @Override
            public void onGiftMsg(DouyinGiftMsg msg) {
                if (msg.getGiftCount() > 0) {
                    log.info("收到礼物 {} 送出 {}x{}", msg.getUsername(), msg.getGiftName(), msg.getGiftCount());
                }
            }

            @Override
            public void onOtherCmdMsg(DouyinCmdEnum cmd, ICmdMsg<DouyinCmdEnum> cmdMsg) {
                log.warn("收到其他CMD消息 {} {}", cmd, cmdMsg);
//                if (cmd == DouyinCmdEnum.WebcastFansclubMessage) {
//                    DouyinCmdMsgOuterClass.DouyinCmdMsg msg = ((DouyinCmdMsg) cmdMsg).getMsg();
//                    ByteString payload = msg.getPayload();
//                    log.warn(payload.toStringUtf8());
//                }
            }
        });

        douyinLiveChatClientTest.connect();

        while (true) {
            System.in.read();
        }
    }

    @SneakyThrows
    @Test
    void forwardMsgTest() {
        String forwardWebsocketUri = "ws://localhost:8080/websocket";
        DouyinLiveChatClient liveChatClient = new DouyinLiveChatClient(DouyinLiveChatClientConfig.builder()
                .forwardWebsocketUri(forwardWebsocketUri)
                .roomId(646454278948L)
                .build()
        );
        liveChatClient.addPlugin(new ForwardMsgPlugin(forwardWebsocketUri));
        liveChatClient.connect();
        while (true) {
            System.in.read();
        }
    }


    @Test
    void example77() throws InterruptedException {
        String cookie = "UIFID_TEMP=43978cd9ea816a7d34a6416f01236307be591e58382e2d59dc373240de143c21a9d5039a38c4d9ec287d714a43af19a02446ef5403e29512aa52aaae3c90b4cd0d028d028afb94ca0ae1644d7ec7ef79; hevc_supported=true; UIFID=43978cd9ea816a7d34a6416f01236307be591e58382e2d59dc373240de143c21a9d5039a38c4d9ec287d714a43af19a0736f1e256c0ef55deb488602834d8a3424b612c2142088917e10e5ccb08653d7058226f82bde65857136659d8e157c92e847d4bdec8bb3dc0c985ec4e7720172743c59c5140d871e35e614ef8b8751fa26fc67f6a6ac5b8bb6cdaaf4dce5ffc9b9348e246cc5ec3d0feb8e6119cc22cb; bd_ticket_guard_client_web_domain=2; is_staff_user=false; SelfTabRedDotControl=%5B%5D; __security_server_data_status=1; SEARCH_RESULT_LIST_TYPE=%22single%22; my_rd=2; live_use_vvc=%22false%22; enter_pc_once=1; is_dash_user=1; __security_mc_1_s_sdk_sign_data_key_sso=b89bacb2-43dc-800c; n_mh=9-mIeuD4wZnlYrrOvfzG3MuT6aQmCUtmr8FxV8Kl8xY; __druidClientInfo=JTdCJTIyY2xpZW50V2lkdGglMjIlM0EyOTglMkMlMjJjbGllbnRIZWlnaHQlMjIlM0E1ODklMkMlMjJ3aWR0aCUyMiUzQTI5OCUyQyUyMmhlaWdodCUyMiUzQTU4OSUyQyUyMmRldmljZVBpeGVsUmF0aW8lMjIlM0EyJTJDJTIydXNlckFnZW50JTIyJTNBJTIyTW96aWxsYSUyRjUuMCUyMChNYWNpbnRvc2glM0IlMjBJbnRlbCUyME1hYyUyME9TJTIwWCUyMDEwXzE1XzcpJTIwQXBwbGVXZWJLaXQlMkY1MzcuMzYlMjAoS0hUTUwlMkMlMjBsaWtlJTIwR2Vja28pJTIwQ2hyb21lJTJGMTM4LjAuMC4wJTIwU2FmYXJpJTJGNTM3LjM2JTIyJTdE; __security_mc_1_s_sdk_crypt_sdk=583ba164-4609-a83f; passport_csrf_token=c4de5a6ff59a5214ed69c16d91e161db; passport_csrf_token_default=c4de5a6ff59a5214ed69c16d91e161db; __security_mc_1_s_sdk_cert_key=5be3b24d-4cf2-a808; publish_badge_show_info=%220%2C0%2C0%2C1754197188089%22; h265ErrorNum=-1; passport_assist_user=ClMnoK1G4klggy-SabXZ7C9s7x9OxKuFdNSK__Kbq9u41nWHuJtcLdpl6lIaq5uWHey_cvrtTIg53npM_a4jfndMWOUsvo9CYSBQpVDJy6XBLhUwoRpKCjwAAAAAAAAAAAAAT09WycszNxduzEIFSodd0o1glC0-jdnLyrIPsXcwaClP7ehYoCOkYcIuMkaOhUW7BSUQ-sP4DRiJr9ZUIAEiAQO1DNGn; sid_guard=9232255446b2c339d6c236e2769d3bef%7C1754275493%7C5183999%7CFri%2C+03-Oct-2025+02%3A44%3A52+GMT; uid_tt=80c1fb10a3bd4577c1727311b66fa3d23c201ec5ecace3f23f0226eeb4bc7c93; uid_tt_ss=80c1fb10a3bd4577c1727311b66fa3d23c201ec5ecace3f23f0226eeb4bc7c93; sid_tt=9232255446b2c339d6c236e2769d3bef; sessionid=9232255446b2c339d6c236e2769d3bef; sessionid_ss=9232255446b2c339d6c236e2769d3bef; session_tlb_tag=sttt%7C1%7CkjIlVEaywznWwjbidp077__________y2wEiyfgwHa0JKD8NoGnMppVU16fi9AenqrOzgrr5Ffg%3D; sid_ucp_v1=1.0.0-KGJiYTdkMjUzN2U3NWY3YjkzMDYxMmZjYTA0ZjBiMzI4YmY1MDg5NmYKIgi6iNiqoI_6n2gQpb3AxAYY7zEgDDCj1__BBjgFQPsHSAQaAmxxIiA5MjMyMjU1NDQ2YjJjMzM5ZDZjMjM2ZTI3NjlkM2JlZg; ssid_ucp_v1=1.0.0-KGJiYTdkMjUzN2U3NWY3YjkzMDYxMmZjYTA0ZjBiMzI4YmY1MDg5NmYKIgi6iNiqoI_6n2gQpb3AxAYY7zEgDDCj1__BBjgFQPsHSAQaAmxxIiA5MjMyMjU1NDQ2YjJjMzM5ZDZjMjM2ZTI3NjlkM2JlZg; login_time=1754275493005; __security_mc_1_s_sdk_sign_data_key_web_protect=5fdf6b0d-497c-83ae; _bd_ticket_crypt_cookie=124829ec03d3553a94a89e5ab6f59cec; volume_info=%7B%22isUserMute%22%3Afalse%2C%22isMute%22%3Atrue%2C%22volume%22%3A0.973%7D; strategyABtestKey=%221754533989.49%22; __live_version__=%221.1.3.6971%22; live_can_add_dy_2_desktop=%221%22; download_guide=%223%2F20250803%2F1%22; stream_player_status_params=%22%7B%5C%22is_auto_play%5C%22%3A0%2C%5C%22is_full_screen%5C%22%3A0%2C%5C%22is_full_webscreen%5C%22%3A1%2C%5C%22is_mute%5C%22%3A1%2C%5C%22is_speed%5C%22%3A1%2C%5C%22is_visible%5C%22%3A0%7D%22; FOLLOW_LIVE_POINT_INFO=%22MS4wLjABAAAAdB8i76Iihl4tI5MT1GcQtdalkUrTddThryPcHELJoDb_hPqex6MyXYlXYIa5i6_g%2F1754582400000%2F0%2F0%2F1754579868426%22; FOLLOW_NUMBER_YELLOW_POINT_INFO=%22MS4wLjABAAAAdB8i76Iihl4tI5MT1GcQtdalkUrTddThryPcHELJoDb_hPqex6MyXYlXYIa5i6_g%2F1754582400000%2F0%2F0%2F1754580468426%22; sdk_source_info=7e276470716a68645a606960273f276364697660272927676c715a6d6069756077273f276364697660272927666d776a68605a607d71606b766c6a6b5a7666776c7571273f275e58272927666a6b766a69605a696c6061273f27636469766027292762696a6764695a7364776c6467696076273f275e582729277672715a646971273f2763646976602729277f6b5a666475273f2763646976602729276d6a6e5a6b6a716c273f2763646976602729276c6b6f5a7f6367273f27636469766027292771273f27353537303d373c323031303234272927676c715a75776a716a666a69273f2763646976602778; bit_env=pUYygGtv7oRugnHxhoLiokeq9XUx7ELZWhT89gZPILmQZCoZA4AkZBi8zJtE9rHatx7wK2hVE3aqFD27-fErWWSBwL8xVvUKFZqn_-xtsI-4hlUyK0dS_eoqI4KAvZukEhQIg5L_ErG6y3lS5yOR9pGMBbhhVXBTGv1XECTkCyA8jd3QmPnKymGGEKb7rXxuH29g5UMOONTrlxF7Zj2_jhiCd0OnH5REYjKvWVJXX2dRtAMMa3dWJ-GRaxFM-v5KA1tI12_2GNTYpzLVQw_uuL5j9fPYM3T7LE41L1MsIIqk-lCeGucz9e7gNT3Vtocjqt6AAy6sX_RTeZ0ShcX7PmPBQmR2mzSHMcgIuGOTloiY6YDff5_pbDW5I3Yl3PzUmTb6eEqGQSzeS6cdvZxeJM-HoogFSFshbqFXeubZadeIH10DY8Geko3loq-kaR9QYc-exEjusXgI9pBdiWjNkTHnZGuvhhNPzyjmZ7Ldn89L_NCFN86RhgVegrpCejCQ5foYUTrM0okIpUwkNT4jHVKLc3_hekQy-vt2SIx5t60%3D; gulu_source_res=eyJwX2luIjoiNWRiODg4ZDk2OWY5MmIyYmRkNjBkMTk5NjFmM2EzM2M0MDE5MTQ4NjExYzMyZGJjNzllZGE3NTdkNTBmMGQwMyJ9; passport_auth_mix_state=va1xeczhdnq5mu40h0mxodgmp4w5028xx50lu2rvo2kxh7hm; biz_trace_id=5843e4d6; WallpaperGuide=%7B%22showTime%22%3A1754488319461%2C%22closeTime%22%3A0%2C%22showCount%22%3A6%2C%22cursor1%22%3A85%2C%22cursor2%22%3A64%2C%22hoverTime%22%3A1752409903584%7D; odin_tt=dc093180ec2bc0d965ead22caba25a8add1af90e3257bf6bf41b49981b9b007d3a0c9ee53996968c48e23d35608453e35e1c651996e3ab4c8bd0efb7e82ea554; ttwid=1%7C2-5p65jHF-vx4moYl19hvBRcrE2SiRBTeamcfAy7W4c%7C1754579497%7C19723cf25ccf2f262b8cf8621456319aeac357c6dc1b5ca96aee81b1cbef0603; IsDouyinActive=true; home_can_add_dy_2_desktop=%220%22; stream_recommend_feed_params=%22%7B%5C%22cookie_enabled%5C%22%3Atrue%2C%5C%22screen_width%5C%22%3A1512%2C%5C%22screen_height%5C%22%3A982%2C%5C%22browser_online%5C%22%3Atrue%2C%5C%22cpu_core_num%5C%22%3A8%2C%5C%22device_memory%5C%22%3A8%2C%5C%22downlink%5C%22%3A4.55%2C%5C%22effective_type%5C%22%3A%5C%224g%5C%22%2C%5C%22round_trip_time%5C%22%3A150%7D%22; bd_ticket_guard_client_data=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWl0ZXJhdGlvbi12ZXJzaW9uIjoxLCJiZC10aWNrZXQtZ3VhcmQtcmVlLXB1YmxpYy1rZXkiOiJCR2dQMDRqZW8zTHdCRjdqVnVXOThyYmdabktIbWU2WnB6WklUcmdCeEJPZnFZRlArL291cHBab0VpUVl1SmNBM0RHSjd1TFExQXk5QXh3cHVDdkwzbjQ9IiwiYmQtdGlja2V0LWd1YXJkLXdlYi12ZXJzaW9uIjoyfQ%3D%3D";
        JSONObject awemePost = DouyinClient.getAwemePost(cookie, "MS4wLjABAAAAkNqdyOh_-W3d9hmg679I_M3JXFFB3WLVA_wf2N1HCwE", 0, 10);
        System.err.println(awemePost);
        System.err.println("xx");
    }

}
