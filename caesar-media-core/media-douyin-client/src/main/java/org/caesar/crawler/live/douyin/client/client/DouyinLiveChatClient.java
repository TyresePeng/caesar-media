/*
 * MIT License
 *
 * Copyright (c) 2023 OrdinaryRoad
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.caesar.crawler.live.douyin.client.client;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.Header;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import forward.ForwardMsgPlugin;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolConfig;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.caesar.crawler.live.douyin.client.config.DouyinLiveChatClientConfig;
import org.caesar.crawler.live.douyin.client.handler.DouyinBinaryFrameHandler;
import org.caesar.crawler.live.douyin.client.handler.DouyinConnectionHandler;
import org.caesar.crawler.live.douyin.client.handler.DouyinLiveChatClientChannelInitializer;
import org.caesar.crawler.live.douyin.client.listener.IDouyinConnectionListener;
import org.caesar.crawler.live.douyin.client.listener.IDouyinMsgListener;
import org.caesar.crawler.live.douyin.codec.api.DouyinApis;
import org.caesar.crawler.live.douyin.codec.constant.DouyinCmdEnum;
import org.caesar.crawler.live.douyin.codec.msg.base.IDouyinMsg;
import org.caesar.crawler.live.douyin.codec.room.DouyinRoomInitResult;
import org.caesar.crawler.live.netty.base.exception.BaseException;
import org.caesar.crawler.live.netty.base.listener.IBaseConnectionListener;
import org.caesar.crawler.live.netty.client.base.BaseNettyClient;
import org.caesar.crawler.live.netty.util.OrJavaScriptUtil;
import org.caesar.crawler.live.netty.util.OrLiveChatCollUtil;
import org.caesar.crawler.live.netty.util.OrLiveChatHttpUtil;
import org.caesar.media.browser.factory.PlaywrightFactory;

import javax.script.ScriptEngine;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author mjz
 * @date 2024/1/2
 */
@Slf4j
public class DouyinLiveChatClient extends BaseNettyClient<DouyinLiveChatClientConfig, DouyinRoomInitResult, DouyinCmdEnum, IDouyinMsg, IDouyinMsgListener, DouyinConnectionHandler, DouyinBinaryFrameHandler> {

    public static String JS_SDK;
    public static String STEALTH_SDK;

    static {
        InputStream resourceAsStream = DouyinLiveChatClient.class.getResourceAsStream("/js/douyin-webmssdk.js");
        JS_SDK = IoUtil.readUtf8(resourceAsStream);
        InputStream resourceAsStreamStealth = DouyinLiveChatClient.class.getResourceAsStream("/js/stealth.min.js");
        STEALTH_SDK = IoUtil.readUtf8(resourceAsStreamStealth);
    }

    public DouyinLiveChatClient(DouyinLiveChatClientConfig config, List<IDouyinMsgListener> msgListeners, IDouyinConnectionListener connectionListener, EventLoopGroup workerGroup) {
        super(config, workerGroup, connectionListener, IDouyinMsgListener.class);
        addMsgListeners(msgListeners);

        // 初始化
        this.init();
    }

    public DouyinLiveChatClient(DouyinLiveChatClientConfig config, IDouyinMsgListener msgListener, IDouyinConnectionListener connectionListener, EventLoopGroup workerGroup) {
        super(config, workerGroup, connectionListener, IDouyinMsgListener.class);
        addMsgListener(msgListener);

        // 初始化
        this.init();
    }

    public DouyinLiveChatClient(DouyinLiveChatClientConfig config, IDouyinMsgListener msgListener, IDouyinConnectionListener connectionListener) {
        this(config, msgListener, connectionListener, new NioEventLoopGroup());
    }

    public DouyinLiveChatClient(DouyinLiveChatClientConfig config, IDouyinMsgListener msgListener) {
        this(config, msgListener, null, new NioEventLoopGroup());
    }

    public DouyinLiveChatClient(DouyinLiveChatClientConfig config) {
        this(config, null);
    }

    @Override
    public void init() {
        // TODO remove this
        addPlugin(new ForwardMsgPlugin(getConfig().getForwardWebsocketUri()));
        super.init();
    }

    @Override
    public DouyinConnectionHandler initConnectionHandler(IBaseConnectionListener<DouyinConnectionHandler> clientConnectionListener) {
        return new DouyinConnectionHandler(() -> {
            DefaultHttpHeaders headers = new DefaultHttpHeaders();
            headers.add(Header.COOKIE.name(), DouyinApis.KEY_COOKIE_TTWID + "=" + roomInitResult.getTtwid());
            headers.add(Header.USER_AGENT.name(), getConfig().getUserAgent());
            return new WebSocketClientProtocolHandler(
                    WebSocketClientProtocolConfig.newBuilder()
                            .webSocketUri(getWebsocketUri())
                            .version(WebSocketVersion.V13)
                            .subprotocol(null)
                            .allowExtensions(true)
                            .customHeaders(headers)
                            .maxFramePayloadLength(getConfig().getMaxFramePayloadLength())
                            .handshakeTimeoutMillis(getConfig().getHandshakeTimeoutMillis())
                            .build()
            );
        }, DouyinLiveChatClient.this, clientConnectionListener);
    }

    @Override
    protected void initChannel(SocketChannel channel) {
        channel.pipeline().addLast(new DouyinLiveChatClientChannelInitializer(this));
    }

    @Override
    public DouyinRoomInitResult initRoom() {
        return DouyinApis.roomInit(getConfig().getRoomId(), getConfig().getCookie(), roomInitResult);
    }

    @Override
    public void send(Object msg) {
        DouyinRoomInitResult result = this.getRoomInitResult();
        long realRoomId = result.getRealRoomId();
        PlaywrightFactory playwrightFactory = this.getConfig().getPlaywrightFactory();
        playwrightFactory.doWithContext(ctx->{
            ctx.addInitScript(STEALTH_SDK);
            Page page = ctx.newPage();
            page.navigate("https://live.douyin.com/"+realRoomId);
            page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        });
    }

    @Override
    protected String getWebSocketUriString() {
        String webSocketUriString = super.getWebSocketUriString();
        if (StrUtil.isBlank(webSocketUriString)) {
            webSocketUriString = OrLiveChatCollUtil.getRandom(DouyinLiveChatClientConfig.WEB_SOCKET_URIS);
        }

        long realRoomId = roomInitResult.getRealRoomId();
        String userUniqueId = roomInitResult.getUserUniqueId();

        Map<String, String> queryParams = new LinkedHashMap<>();
        queryParams.put("app_name", "douyin_web");
        queryParams.put("version_code", getConfig().getVersionCode());
        queryParams.put("webcast_sdk_version", getConfig().getWebcastSdkVersion());
        queryParams.put("update_version_code", getConfig().getUpdateVersionCode());
        queryParams.put("compress", "gzip");
        queryParams.put("device_platform", "web");
        queryParams.put("cookie_enabled", "true");
        queryParams.put("screen_width", "1280");
        queryParams.put("screen_height", "800");
        queryParams.put("browser_language", "zh-CN");
        queryParams.put("browser_platform", "MacIntel");
        queryParams.put("browser_name", "Mozilla");
        queryParams.put("browser_version", getConfig().getBrowserVersion());
        queryParams.put("browser_online", "true");
        queryParams.put("tz_name", "Asia/Shanghai");
        // cursor=r-1_d-1_u-1_fh-743192 5703690294282_t-1730380287061&
        // cursor=t-1730378827676_r-1_d-1_u-1_fh-743192 0359546983464&
//        queryParams.put("cursor", "t-" + System.currentTimeMillis() + "_r-1_d-1_u-1_h-1");
        queryParams.put("cursor", "t-" + System.currentTimeMillis() + "_r-1_d-1_u-1_fh-743192" + RandomUtil.randomNumbers(13));
        queryParams.put("internal_ext", "internal_src:dim|" +
                        "wss_push_room_id:" + realRoomId + "|" +
                        "wss_push_did:" + userUniqueId + "|" +
                        "first_req_ms:" + System.currentTimeMillis() + "|" +
                        "fetch_time:" + System.currentTimeMillis() + "|" +
                        "seq:1|" +
                        "wss_info:0-" + System.currentTimeMillis() + "-0-0|" +
                        // wrds_v:743192 6738013141490&
                        // wrds_v:743192 0463065920405&
                        "wrds_v:743192" + RandomUtil.randomNumbers(13)
//                "wrds_kvs:WebcastRoomStatsMessage-" + System.nanoTime() + "_WebcastRoomRankMessage-" + System.nanoTime() + "_LotteryInfoSyncData-" + System.nanoTime() + "_WebcastActivityEmojiGroupsMessage-" + System.nanoTime()
        );
        queryParams.put("host", "https://live.douyin.com");
        queryParams.put("aid", "6383");
        queryParams.put("live_id", "1");
        queryParams.put("did_rule", "3");
        queryParams.put("endpoint", "live_pc");
        queryParams.put("support_wrds", "1");
        queryParams.put("user_unique_id", userUniqueId);
        queryParams.put("im_path", "/webcast/im/fetch/");
        queryParams.put("identity", "audience");
        queryParams.put("need_persist_msg_count", "15");
        queryParams.put("insert_task_id", "");
        queryParams.put("live_reason", "");
        queryParams.put("room_id", Long.toString(realRoomId));
        queryParams.put("heartbeatDuration ", "0");
        queryParams.put("signature", getSignature(getConfig().getUserAgent(), roomInitResult.getRealRoomId(), roomInitResult.getUserUniqueId()));
        return webSocketUriString + "?" + OrLiveChatHttpUtil.toParams(queryParams);
    }

    @SneakyThrows
    public String getSignature(String userAgent, long roomId, String userUniqueId) {
        String JS_ENV = " document = {};\nwindow = {};\nnavigator = {\nuserAgent: '" + userAgent + "'\n};\n";
        ScriptEngine scriptEngine = OrJavaScriptUtil.createScriptEngine();
        scriptEngine.eval(JS_ENV + JS_SDK);
        String signPram = ("live_id=1,aid=6383," +
                "version_code=$version_code$," +
                "webcast_sdk_version=$webcast_sdk_version$," +
                "room_id=$roomId$," +
                "sub_room_id=,sub_channel_id=,did_rule=3," +
                "user_unique_id=$userId$," +
                "device_platform=web,device_type=,ac=,identity=audience")
                .replace("$webcast_sdk_version$", getConfig().getWebcastSdkVersion())
                .replace("$version_code$", getConfig().getVersionCode())
                .replace("$roomId$", String.valueOf(roomId))
                .replace("$userId$", userUniqueId);
        String md5Hex = DigestUtil.md5Hex(signPram.getBytes(StandardCharsets.UTF_8));
        try {
            Object eval = scriptEngine.eval("get_sign('" + md5Hex + "')");
            return eval.toString();
        } catch (Exception e) {
            throw new BaseException("Execution failed: getSignature", e);
        }
    }
}
