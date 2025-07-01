package org.caesar.crawler.live.douyin.client.test;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.UnknownFieldSet;
import forward.ForwardMsgPlugin;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
    void roomInfo() throws InterruptedException {
        DouyinRoomInitResult douyinRoomInitResult = DouyinApis.roomInit(162515605480L);
        System.err.println(douyinRoomInitResult);
    }


    @Test
    void example() throws InterruptedException {
        String cookie = "UIFID_TEMP=43978cd9ea816a7d34a6416f01236307be591e58382e2d59dc373240de143c21a9d5039a38c4d9ec287d714a43af19a02446ef5403e29512aa52aaae3c90b4cd0d028d028afb94ca0ae1644d7ec7ef79; hevc_supported=true; UIFID=43978cd9ea816a7d34a6416f01236307be591e58382e2d59dc373240de143c21a9d5039a38c4d9ec287d714a43af19a0736f1e256c0ef55deb488602834d8a3424b612c2142088917e10e5ccb08653d7058226f82bde65857136659d8e157c92e847d4bdec8bb3dc0c985ec4e7720172743c59c5140d871e35e614ef8b8751fa26fc67f6a6ac5b8bb6cdaaf4dce5ffc9b9348e246cc5ec3d0feb8e6119cc22cb; passport_csrf_token=5d4e77c350238c54768bce045de7caea; passport_csrf_token_default=5d4e77c350238c54768bce045de7caea; __security_mc_1_s_sdk_crypt_sdk=85685131-49bf-8ba8; __security_mc_1_s_sdk_cert_key=9155a2e5-47c9-bcee; bd_ticket_guard_client_web_domain=2; n_mh=wZIwrdDC4ExSYvdDFd-R3BXIztgXzaS0_bnzvSI3ALw; is_staff_user=false; SelfTabRedDotControl=%5B%5D; __security_server_data_status=1; SEARCH_RESULT_LIST_TYPE=%22single%22; my_rd=2; xgplayer_device_id=86132390923; xgplayer_user_id=830133142961; live_use_vvc=%22false%22; h265ErrorNumNew=-1; fpk1=U2FsdGVkX1+ctvYLX8xwTqjQRPx0fCe6NmZ1SjJ+uIfRsGX3GMIvv3i6njXX3vPMIDEykImaVda5gLI8R9VHng==; fpk2=5b2ba492da1bf8b88f5f71b161575820; enter_pc_once=1; WallpaperGuide=%7B%22showTime%22%3A1750493714451%2C%22closeTime%22%3A0%2C%22showCount%22%3A1%2C%22cursor1%22%3A22%2C%22cursor2%22%3A6%7D; has_avx2=null; device_web_cpu_core=8; device_web_memory_size=8; csrf_session_id=8f247a9fe80cc35e6e445ce8ca64a4ad; webcast_leading_last_show_time=1751014740581; webcast_leading_total_show_times=1; webcast_local_quality=sd; __ac_signature=_02B4Z6wo00f012qLtHAAAIDCQ48qDoH7M4tqq7DAALMH8b; h265ErrorNum=-1; __live_version__=%221.1.3.4887%22; strategyABtestKey=%221751183080.269%22; xg_device_score=7.621467019087541; sdk_source_info=7e276470716a68645a606960273f276364697660272927676c715a6d6069756077273f276364697660272927666d776a68605a607d71606b766c6a6b5a7666776c7571273f275e58272927666a6b766a69605a696c6061273f27636469766027292762696a6764695a7364776c6467696076273f275e5827292771273f2735353c373734363d34343032342778; bit_env=SZfhVUANZ3kBIBcSrQi-jk_HsFW-mEbFVV5ICRuTmh8jkM8-IMMSYlPV7oucGwcJ0GF8qrwhPlwGIBavcTq46nQs5fFhwGyOM2Sp0QshaErJvBCq1z2S6l0dsBM2tx37k0SRiz5LipCrfBAsf6Cd81RpPXOoqtGOEUan4vnpw9HYvmYK8dUV1-aOOeqIYBbsTIOgPtaHc4iM_UZEvFu2TXzjmt-asLkVk2wcR8DK8gYyZ0oRWm3RfMOrNMTCgi02a-DemDrccxAHUNpJb4JMiJa-bBRmWahDrjVCH3PNXPh4tRZkj1BQxTVk5sfjbEfUTnVYHO3AixKmsT-KTZ5cyWFbrn0XqBgWpL0xm0N78A0Koq6bb5p53mtE5f3vducCK2fkDGtsUQlfcT1eavTnfbrNWP5pbIrAsj1W6s3uH9TPTh_95u5_PmwAR17DrrPLCgyjG2gZt0FfbPxrGZ1OJpmkJSgI2b90o7_yGRXJk14VcA8UgCD1CofxwEUoxP3tvMz54MF_SuiqDqCGxKnpDpm9UBrOZfvdFnG1iWzmLUE%3D; gulu_source_res=eyJwX2luIjoiNDBkNjY5OWMwYjhmMjQwZTU4ZjEzZTA0ZTM3ZTI0ZjE0NTQ4MTFlZjgzY2Y4ZjYyYjAzZTk4MTBmMGE0MzdkOCJ9; passport_auth_mix_state=zvi4ky6n0hunku9aaxfelkwyqo46r7dt; biz_trace_id=14394fb9; ttwid=1%7C2-5p65jHF-vx4moYl19hvBRcrE2SiRBTeamcfAy7W4c%7C1751183126%7Cc754570e9d6408c548151998e4cb6df9c691426964b6bb1bb9a5f176952e6a58; passport_assist_user=CkHfR8fTOj0Hq_Fv1qa2WqkWMZCuyItIgF0ndgqe2BnrdwB4HZKxcQTUZePxXWdpdvRm6smktUqSPLSTP2vSk-U1dRpKCjwAAAAAAAAAAAAATyyaOUdWRvmZHVePShv-SYxT_KA5cyOGZXwpeTdRn7zsaYveuRIFpHTDTXCKO_q2eFoQ6rD1DRiJr9ZUIAEiAQNbOAj8; sid_guard=cf0431cad73db8999092bf7d5a30caef%7C1751183132%7C5184000%7CThu%2C+28-Aug-2025+07%3A45%3A32+GMT; uid_tt=60bef36c7251b8d90c847b9adfb4a89c; uid_tt_ss=60bef36c7251b8d90c847b9adfb4a89c; sid_tt=cf0431cad73db8999092bf7d5a30caef; sessionid=cf0431cad73db8999092bf7d5a30caef; sessionid_ss=cf0431cad73db8999092bf7d5a30caef; session_tlb_tag=sttt%7C5%7CzwQxytc9uJmQkr99WjDK7__________kGiqQ066ouvnKInQ-r8E4qc3rwG_ShxmbECS0BLmw1q8%3D; sid_ucp_v1=1.0.0-KDY1M2UxNjYxZTZiMGJiNzZjZmZhNDBiNzhjN2JkNzA2ZDE5MjQ3MDEKIQjutsCnj_SxBBCc3oPDBhjvMSAMMKaivfAFOAdA9AdIBBoCaGwiIGNmMDQzMWNhZDczZGI4OTk5MDkyYmY3ZDVhMzBjYWVm; ssid_ucp_v1=1.0.0-KDY1M2UxNjYxZTZiMGJiNzZjZmZhNDBiNzhjN2JkNzA2ZDE5MjQ3MDEKIQjutsCnj_SxBBCc3oPDBhjvMSAMMKaivfAFOAdA9AdIBBoCaGwiIGNmMDQzMWNhZDczZGI4OTk5MDkyYmY3ZDVhMzBjYWVm; login_time=1751183132946; publish_badge_show_info=%220%2C0%2C0%2C1751183133976%22; _bd_ticket_crypt_cookie=2bb2dd84815f7ab99860b467a21bc666; __security_mc_1_s_sdk_sign_data_key_web_protect=e3a86b00-454c-ad8d; odin_tt=effeaf5fcba9e55b0396e95a7764ce77ee8c3f7f8d5ed79fcb855a5361bff822ef949efadf8c96c1ddba6c07c10d589968ce4b9ae834b7a202facbffac109c77; FOLLOW_LIVE_POINT_INFO=%22MS4wLjABAAAAhQ8eiBM3jufM08Ki6LacfX7i0gyf5tAYVhdw8JPo-v-77o7NCLfb-xwrR3gdfhVU%2F1751212800000%2F0%2F1751183135883%2F0%22; stream_recommend_feed_params=%22%7B%5C%22cookie_enabled%5C%22%3Atrue%2C%5C%22screen_width%5C%22%3A1512%2C%5C%22screen_height%5C%22%3A982%2C%5C%22browser_online%5C%22%3Atrue%2C%5C%22cpu_core_num%5C%22%3A8%2C%5C%22device_memory%5C%22%3A8%2C%5C%22downlink%5C%22%3A10%2C%5C%22effective_type%5C%22%3A%5C%224g%5C%22%2C%5C%22round_trip_time%5C%22%3A100%7D%22; home_can_add_dy_2_desktop=%221%22; IsDouyinActive=true; bd_ticket_guard_client_data=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWl0ZXJhdGlvbi12ZXJzaW9uIjoxLCJiZC10aWNrZXQtZ3VhcmQtcmVlLXB1YmxpYy1rZXkiOiJCR2dQMDRqZW8zTHdCRjdqVnVXOThyYmdabktIbWU2WnB6WklUcmdCeEJPZnFZRlArL291cHBab0VpUVl1SmNBM0RHSjd1TFExQXk5QXh3cHVDdkwzbjQ9IiwiYmQtdGlja2V0LWd1YXJkLXdlYi12ZXJzaW9uIjoyfQ%3D%3D; live_can_add_dy_2_desktop=%221%22; passport_fe_beating_status=true";
        log.error("cookie: {}", cookie);
        DouyinLiveChatClientConfig config = DouyinLiveChatClientConfig.builder()
//                .cookie(cookie)
                .roomId("567523334915")
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

}
