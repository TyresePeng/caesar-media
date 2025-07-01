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

package client;

import cn.hutool.core.util.StrUtil;
import config.WebSocketLiveChatClientConfig;
import constant.WebSocketCmdEnum;
import handler.WebSocketBinaryFrameHandler;
import handler.WebSocketChannelInitializer;
import handler.WebSocketConnectionHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolConfig;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import listener.IWebSocketConnectionListener;
import listener.IWebSocketMsgListener;
import lombok.extern.slf4j.Slf4j;
import msg.base.IWebSocketMsg;
import org.caesar.crawler.live.netty.base.exception.BaseException;
import org.caesar.crawler.live.netty.base.listener.IBaseConnectionListener;
import org.caesar.crawler.live.netty.base.msg.IMsg;
import org.caesar.crawler.live.netty.client.base.BaseNettyClient;
import org.caesar.crawler.live.netty.servers.IBaseConnectionHandler;
import room.WebSocketRoomInitResult;

import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 * @author mjz
 * @date 2024/3/8
 */
@Slf4j
public class WebSocketLiveChatClient extends BaseNettyClient<
        WebSocketLiveChatClientConfig,
        WebSocketRoomInitResult,
        WebSocketCmdEnum,
        IWebSocketMsg,
        IWebSocketMsgListener,
        WebSocketConnectionHandler,
        WebSocketBinaryFrameHandler
        > {

    private WebSocketLiveChatClient forwardClient;
    private final IBaseConnectionHandler connectionHandler;

    public WebSocketLiveChatClient(WebSocketLiveChatClientConfig config, IBaseConnectionHandler connectionHandler, IWebSocketMsgListener msgListener, IWebSocketConnectionListener connectionListener, EventLoopGroup workerGroup) {
        super(config, workerGroup, connectionListener);
        addMsgListener(msgListener);
        this.connectionHandler = connectionHandler;

        // 初始化
        this.init();
    }

    public WebSocketLiveChatClient(WebSocketLiveChatClientConfig config, IBaseConnectionHandler connectionHandler, IWebSocketMsgListener msgListener, IWebSocketConnectionListener connectionListener) {
        this(config, connectionHandler, msgListener, connectionListener, new NioEventLoopGroup());
    }

    public WebSocketLiveChatClient(WebSocketLiveChatClientConfig config, IBaseConnectionHandler connectionHandler, IWebSocketMsgListener msgListener) {
        this(config, connectionHandler, msgListener, null, new NioEventLoopGroup());
    }

    public WebSocketLiveChatClient(WebSocketLiveChatClientConfig config, IBaseConnectionHandler connectionHandler) {
        this(config, connectionHandler, null);
    }

    public WebSocketLiveChatClient(WebSocketLiveChatClientConfig config) {
        this(config, null, null);
    }

    @Override
    public WebSocketConnectionHandler initConnectionHandler(IBaseConnectionListener<WebSocketConnectionHandler> clientConnectionListener) {
        return new WebSocketConnectionHandler(
                () -> new WebSocketClientProtocolHandler(
                        WebSocketClientProtocolConfig.newBuilder()
                                .webSocketUri(getWebsocketUri())
                                .version(WebSocketVersion.V13)
                                .subprotocol(null)
                                .allowExtensions(true)
                                .customHeaders(new DefaultHttpHeaders())
                                .maxFramePayloadLength(getConfig().getMaxFramePayloadLength())
                                .handshakeTimeoutMillis(getConfig().getHandshakeTimeoutMillis())
                                .build()
                ),
                connectionHandler, WebSocketLiveChatClient.this, clientConnectionListener);
    }

    @Override
    protected void initChannel(SocketChannel channel) {
        channel.pipeline().addLast(new WebSocketChannelInitializer(this));
    }

    @Override
    public void init() {
        if (StrUtil.isNotBlank(getConfig().getForwardWebsocketUri())) {
            forwardClient = new WebSocketLiveChatClient(
                    WebSocketLiveChatClientConfig.builder()
                            .websocketUri(getConfig().getForwardWebsocketUri())
                            .build()
            );
            forwardClient.connect();
            addMsgListener(new IWebSocketMsgListener() {
                @Override
                public void onMsg(IMsg msg) {
                    ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
                    buffer.writeCharSequence(msg.toString(), StandardCharsets.UTF_8);
                    forwardClient.send(new BinaryWebSocketFrame(buffer));
                }
            });
        }
        super.init();
    }

    @Override
    public WebSocketRoomInitResult initRoom() {
        return WebSocketRoomInitResult.builder().build();
    }

    @Override
    public void send(Object msg, Runnable success, Consumer<Throwable> failed) {
        if (!(msg instanceof BinaryWebSocketFrame)) {
            throw new BaseException("WebSocketLiveChatClient.send 仅支持 BinaryWebSocketFrame类型的消息");
        }
        super.send(msg, success, failed);
    }

    @Override
    public void destroy() {
        if (forwardClient != null) {
            forwardClient.destroy();
        }
        super.destroy();
    }
}
