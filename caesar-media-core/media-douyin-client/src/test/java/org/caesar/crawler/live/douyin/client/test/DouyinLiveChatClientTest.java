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
        DouyinRoomInitResult douyinRoomInitResult = DouyinApis.roomInit(162515605480L);
        System.err.println(douyinRoomInitResult);
    }


    @Test
    void example() throws InterruptedException {
        String cookie = "UIFID_TEMP=43978cd9ea816a7d34a6416f01236307be591e58382e2d59dc373240de143c21a9d5039a38c4d9ec287d714a43af19a02446ef5403e29512aa52aaae3c90b4cd0d028d028afb94ca0ae1644d7ec7ef79; hevc_supported=true; UIFID=43978cd9ea816a7d34a6416f01236307be591e58382e2d59dc373240de143c21a9d5039a38c4d9ec287d714a43af19a0736f1e256c0ef55deb488602834d8a3424b612c2142088917e10e5ccb08653d7058226f82bde65857136659d8e157c92e847d4bdec8bb3dc0c985ec4e7720172743c59c5140d871e35e614ef8b8751fa26fc67f6a6ac5b8bb6cdaaf4dce5ffc9b9348e246cc5ec3d0feb8e6119cc22cb; passport_csrf_token=5d4e77c350238c54768bce045de7caea; passport_csrf_token_default=5d4e77c350238c54768bce045de7caea; __security_mc_1_s_sdk_crypt_sdk=85685131-49bf-8ba8; __security_mc_1_s_sdk_cert_key=9155a2e5-47c9-bcee; bd_ticket_guard_client_web_domain=2; n_mh=wZIwrdDC4ExSYvdDFd-R3BXIztgXzaS0_bnzvSI3ALw; is_staff_user=false; SelfTabRedDotControl=%5B%5D; __security_server_data_status=1; SEARCH_RESULT_LIST_TYPE=%22single%22; my_rd=2; xgplayer_device_id=86132390923; xgplayer_user_id=830133142961; live_use_vvc=%22false%22; h265ErrorNumNew=-1; fpk1=U2FsdGVkX1+ctvYLX8xwTqjQRPx0fCe6NmZ1SjJ+uIfRsGX3GMIvv3i6njXX3vPMIDEykImaVda5gLI8R9VHng==; fpk2=5b2ba492da1bf8b88f5f71b161575820; enter_pc_once=1; h265ErrorNum=-1; passport_assist_user=CkHfR8fTOj0Hq_Fv1qa2WqkWMZCuyItIgF0ndgqe2BnrdwB4HZKxcQTUZePxXWdpdvRm6smktUqSPLSTP2vSk-U1dRpKCjwAAAAAAAAAAAAATyyaOUdWRvmZHVePShv-SYxT_KA5cyOGZXwpeTdRn7zsaYveuRIFpHTDTXCKO_q2eFoQ6rD1DRiJr9ZUIAEiAQNbOAj8; sid_guard=cf0431cad73db8999092bf7d5a30caef%7C1751183132%7C5184000%7CThu%2C+28-Aug-2025+07%3A45%3A32+GMT; uid_tt=60bef36c7251b8d90c847b9adfb4a89c; uid_tt_ss=60bef36c7251b8d90c847b9adfb4a89c; sid_tt=cf0431cad73db8999092bf7d5a30caef; sessionid=cf0431cad73db8999092bf7d5a30caef; sessionid_ss=cf0431cad73db8999092bf7d5a30caef; session_tlb_tag=sttt%7C5%7CzwQxytc9uJmQkr99WjDK7__________kGiqQ066ouvnKInQ-r8E4qc3rwG_ShxmbECS0BLmw1q8%3D; sid_ucp_v1=1.0.0-KDY1M2UxNjYxZTZiMGJiNzZjZmZhNDBiNzhjN2JkNzA2ZDE5MjQ3MDEKIQjutsCnj_SxBBCc3oPDBhjvMSAMMKaivfAFOAdA9AdIBBoCaGwiIGNmMDQzMWNhZDczZGI4OTk5MDkyYmY3ZDVhMzBjYWVm; ssid_ucp_v1=1.0.0-KDY1M2UxNjYxZTZiMGJiNzZjZmZhNDBiNzhjN2JkNzA2ZDE5MjQ3MDEKIQjutsCnj_SxBBCc3oPDBhjvMSAMMKaivfAFOAdA9AdIBBoCaGwiIGNmMDQzMWNhZDczZGI4OTk5MDkyYmY3ZDVhMzBjYWVm; publish_badge_show_info=%220%2C0%2C0%2C1751183133976%22; _bd_ticket_crypt_cookie=2bb2dd84815f7ab99860b467a21bc666; __security_mc_1_s_sdk_sign_data_key_web_protect=e3a86b00-454c-ad8d; download_guide=%223%2F20250629%2F0%22; has_avx2=null; device_web_cpu_core=8; device_web_memory_size=8; csrf_session_id=8f247a9fe80cc35e6e445ce8ca64a4ad; WallpaperGuide=%7B%22showTime%22%3A1751281399087%2C%22closeTime%22%3A0%2C%22showCount%22%3A1%2C%22cursor1%22%3A10%2C%22cursor2%22%3A2%7D; is_dash_user=1; volume_info=%7B%22isUserMute%22%3Afalse%2C%22isMute%22%3Atrue%2C%22volume%22%3A0.6%7D; ttwid=1%7C2-5p65jHF-vx4moYl19hvBRcrE2SiRBTeamcfAy7W4c%7C1751282091%7C548f8c755374b6617bda3357216aa87f5465462c506ca80ac7f2797d9a8d5383; __live_version__=%221.1.3.5120%22; passport_fe_beating_status=true; strategyABtestKey=%221751365207.501%22; biz_trace_id=0db0f46b; login_time=1751365272659; stream_player_status_params=%22%7B%5C%22is_auto_play%5C%22%3A0%2C%5C%22is_full_screen%5C%22%3A0%2C%5C%22is_full_webscreen%5C%22%3A0%2C%5C%22is_mute%5C%22%3A1%2C%5C%22is_speed%5C%22%3A1%2C%5C%22is_visible%5C%22%3A0%7D%22; stream_recommend_feed_params=%22%7B%5C%22cookie_enabled%5C%22%3Atrue%2C%5C%22screen_width%5C%22%3A1512%2C%5C%22screen_height%5C%22%3A982%2C%5C%22browser_online%5C%22%3Atrue%2C%5C%22cpu_core_num%5C%22%3A8%2C%5C%22device_memory%5C%22%3A8%2C%5C%22downlink%5C%22%3A1.5%2C%5C%22effective_type%5C%22%3A%5C%223g%5C%22%2C%5C%22round_trip_time%5C%22%3A350%7D%22; home_can_add_dy_2_desktop=%221%22; webcast_local_quality=origin; FOLLOW_NUMBER_YELLOW_POINT_INFO=%22MS4wLjABAAAAhQ8eiBM3jufM08Ki6LacfX7i0gyf5tAYVhdw8JPo-v-77o7NCLfb-xwrR3gdfhVU%2F1751472000000%2F0%2F1751442316374%2F0%22; FOLLOW_LIVE_POINT_INFO=%22MS4wLjABAAAAhQ8eiBM3jufM08Ki6LacfX7i0gyf5tAYVhdw8JPo-v-77o7NCLfb-xwrR3gdfhVU%2F1751558400000%2F0%2F1751512127460%2F0%22; __ac_nonce=06865f440003f546d7ad2; __ac_signature=_02B4Z6wo00f01dci2.QAAIDA.iZFiIwLhiHXAt9AAB2v88; xg_device_score=7.592368005596743; live_can_add_dy_2_desktop=%221%22; bd_ticket_guard_client_data=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWl0ZXJhdGlvbi12ZXJzaW9uIjoxLCJiZC10aWNrZXQtZ3VhcmQtcmVlLXB1YmxpYy1rZXkiOiJCR2dQMDRqZW8zTHdCRjdqVnVXOThyYmdabktIbWU2WnB6WklUcmdCeEJPZnFZRlArL291cHBab0VpUVl1SmNBM0RHSjd1TFExQXk5QXh3cHVDdkwzbjQ9IiwiYmQtdGlja2V0LWd1YXJkLXdlYi12ZXJzaW9uIjoyfQ%3D%3D; odin_tt=aaf380807563ee7cea26e9ff763090e4413e2acf3b6ee0a33374e68fa9971ca442cd44cf529d98478e98475ddc001c3ac288d00b386c893b9470626772898fe6; IsDouyinActive=true";
        log.error("cookie: {}", cookie);
        DouyinLiveChatClientConfig config = DouyinLiveChatClientConfig.builder()
                .cookie(cookie)
                .roomId(837842834415L)
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

}
