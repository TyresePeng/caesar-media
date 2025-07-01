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

package org.caesar.crawler.live.netty.client.base;

import cn.hutool.core.util.StrUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.proxy.Socks5ProxyHandler;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.caesar.crawler.live.netty.base.exception.BaseException;
import org.caesar.crawler.live.netty.base.listener.IBaseConnectionListener;
import org.caesar.crawler.live.netty.base.listener.IBaseMsgListener;
import org.caesar.crawler.live.netty.base.msg.IMsg;
import org.caesar.crawler.live.netty.base.room.IRoomInitResult;
import org.caesar.crawler.live.netty.client.BaseLiveChatClient;
import org.caesar.crawler.live.netty.client.config.BaseNettyClientConfig;
import org.caesar.crawler.live.netty.client.enums.ClientStatusEnums;
import org.caesar.crawler.live.netty.servers.BaseBinaryFrameHandler;
import org.caesar.crawler.live.netty.servers.BaseConnectionHandler;
import org.caesar.crawler.live.netty.util.OrLiveChatUrlUtil;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author mjz
 * @date 2023/8/26
 */
@Slf4j
public abstract class BaseNettyClient
        <Config extends BaseNettyClientConfig,
                RoomInitResult extends IRoomInitResult,
                CmdEnum extends Enum<CmdEnum>,
                Msg extends IMsg,
                MsgListener extends IBaseMsgListener<BinaryFrameHandler, CmdEnum>,
                ConnectionHandler extends BaseConnectionHandler<ConnectionHandler>,
                BinaryFrameHandler extends BaseBinaryFrameHandler<BinaryFrameHandler, CmdEnum, Msg, MsgListener>
                >
        extends BaseLiveChatClient<Config, RoomInitResult, MsgListener>
        implements IBaseConnectionListener<ConnectionHandler> {

    @Getter
    private final EventLoopGroup workerGroup;
    @Getter
    private final Bootstrap bootstrap = new Bootstrap();
    protected IBaseConnectionListener<ConnectionHandler> clientConnectionListener = this;
    protected ConnectionHandler connectionHandler;
    private IBaseConnectionListener<ConnectionHandler> connectionListener;
    private Channel channel;
    @Getter
    private URI websocketUri;
    /**
     * 控制弹幕发送频率
     */
    private volatile long lastSendDanmuTimeInMillis;

    protected BaseNettyClient(Config config, EventLoopGroup workerGroup, IBaseConnectionListener<ConnectionHandler> connectionListener) {
        super(config);
        this.workerGroup = workerGroup;
        this.connectionListener = connectionListener;
        this.msgListenerClass = null;
    }

    protected BaseNettyClient(Config config, EventLoopGroup workerGroup, IBaseConnectionListener<ConnectionHandler> connectionListener, Class<MsgListener> msgListenerClass) {
        super(config);
        this.workerGroup = workerGroup;
        this.connectionListener = connectionListener;
        this.msgListenerClass = msgListenerClass;
    }

    /**
     * 初始化Channel，添加自己的Handler
     */
    protected void initChannel(SocketChannel channel) {
        // ignore
    }

    public abstract ConnectionHandler initConnectionHandler(IBaseConnectionListener<ConnectionHandler> clientConnectionListener);

    @Override
    public void onConnected(ConnectionHandler connectionHandler) {
        this.setStatus(ClientStatusEnums.CONNECTED);
        if (this.connectionListener != null) {
            this.connectionListener.onConnected(connectionHandler);
        }
    }

    @Override
    public void onConnectFailed(ConnectionHandler connectionHandler) {
        this.setStatus(ClientStatusEnums.CONNECT_FAILED);
        if (this.connectionListener != null) {
            this.connectionListener.onConnectFailed(connectionHandler);
        }
    }

    @Override
    public void onDisconnected(ConnectionHandler connectionHandler) {
        this.setStatus(ClientStatusEnums.DISCONNECTED);
        tryReconnect();
        if (this.connectionListener != null) {
            this.connectionListener.onDisconnected(connectionHandler);
        }
    }

    @Override
    public void init() {
        if (checkStatus(ClientStatusEnums.INITIALIZED)) {
            return;
        }

        this.connectionHandler = this.initConnectionHandler(this);
        this.bootstrap.group(this.workerGroup)
                // 创建Channel
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true);

        this.setStatus(ClientStatusEnums.INITIALIZED);
    }

    @Override
    public void connect(Runnable success, Consumer<Throwable> failed) {
        super.connect(success, failed);
        if (this.cancelReconnect) {
            this.cancelReconnect = false;
        }
        if (!checkStatus(ClientStatusEnums.INITIALIZED)) {
            return;
        }
        if (getStatus() == ClientStatusEnums.CONNECTED) {
            return;
        }
        if (getStatus() != ClientStatusEnums.RECONNECTING) {
            this.setStatus(ClientStatusEnums.CONNECTING);
        }

        String webSocketUriString = getWebSocketUriString();
        if (StrUtil.isEmpty(webSocketUriString)) {
            _connectFailed(failed, new BaseException("WebSocket地址为空"));
            return;
        }
        int port = OrLiveChatUrlUtil.getWebSocketUriPort(webSocketUriString);
        try {
            this.websocketUri = new URI(webSocketUriString);
        } catch (Exception e) {
            _connectFailed(failed, e);
            return;
        }

        this.bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // 责任链
                        ChannelPipeline pipeline = ch.pipeline();

                        // Socks5代理
                        String socks5ProxyHost = getConfig().getSocks5ProxyHost();
                        if (StrUtil.isNotBlank(socks5ProxyHost)) {
                            int socks5ProxyPort = getConfig().getSocks5ProxyPort();
                            pipeline.addFirst(new Socks5ProxyHandler(new InetSocketAddress(socks5ProxyHost, socks5ProxyPort), getConfig().getSocks5ProxyUsername(), getConfig().getSocks5ProxyPassword()));
                            if (log.isDebugEnabled()) {
                                log.debug("已启用Socks5代理");
                            }
                        }

                        if (pipeline.get(SslHandler.class) != null) {
                            pipeline.remove(SslHandler.class);
                        }
                        if ("wss".equalsIgnoreCase(OrLiveChatUrlUtil.getScheme(getWebSocketUriString()))) {
                            pipeline.addLast(SslContextBuilder.forClient()
                                    .build()
                                    .newHandler(ch.alloc(), BaseNettyClient.this.websocketUri.getHost(), port));
                        }

                        // 添加一个http的编解码器
                        pipeline.addLast(new HttpClientCodec());
                        // 添加一个用于支持大数据流的支持
                        pipeline.addLast(new ChunkedWriteHandler());
                        // 添加一个聚合器，这个聚合器主要是将HttpMessage聚合成FullHttpRequest/Response
                        pipeline.addLast(new HttpObjectAggregator(BaseNettyClient.this.getConfig().getAggregatorMaxContentLength()));

                        // 添加一个WebSocket协议处理器
                        pipeline.addLast(BaseNettyClient.this.connectionHandler.getWebSocketProtocolHandler().get());

                        // 连接处理器
                        pipeline.addLast(BaseNettyClient.this.connectionHandler);

                        BaseNettyClient.this.initChannel(ch);
                    }
                })
                .connect(this.websocketUri.getHost(), port).addListener((ChannelFutureListener) connectFuture -> {
                    if (connectFuture.isSuccess()) {
                        if (log.isDebugEnabled()) {
                            Object roomId = getConfig().getRoomId();
                            log.debug("连接建立成功！ {}", roomId == null ? getConfig().getWebsocketUri() : roomId);
                        }
                        this.channel = connectFuture.channel();
                        // 监听是否握手成功
                        this.connectionHandler.getHandshakePromise().addListener((ChannelFutureListener) handshakeFuture -> {
                            if (handshakeFuture.isSuccess()) {
                                try {
                                    connectionHandler.sendAuthRequest(channel);
                                    if (success != null) {
                                        channel.eventLoop().execute(success);
                                    }
                                } catch (Exception e) {
                                    log.error("认证包发送失败，断开连接", e);
                                    this.disconnect();
                                }
                            }
                        });
                    } else {
                        _connectFailed(failed, connectFuture.cause());
                    }
                });
    }

    @Override
    public void disconnect() {
        if (this.channel == null) {
            return;
        }
        this.channel.close();
    }

    @Override
    protected void tryReconnect() {
        if (this.cancelReconnect) {
            this.cancelReconnect = false;
            return;
        }
        if (!getConfig().isAutoReconnect()) {
            return;
        }
        if (log.isWarnEnabled()) {
            Object roomId = getConfig().getRoomId();
            log.warn("{}s后将重新连接 {}", getConfig().getReconnectDelay(), roomId == null ? getConfig().getWebsocketUri() : roomId);
        }
        workerGroup.schedule(() -> {
            this.setStatus(ClientStatusEnums.RECONNECTING);
            this.connect();
        }, getConfig().getReconnectDelay(), TimeUnit.SECONDS);
    }

    @Override
    public void send(Object msg, Runnable success, Consumer<Throwable> failed) {
        if (getStatus() != ClientStatusEnums.CONNECTED) {
            return;
        }

        ChannelFuture future = this.channel.writeAndFlush(msg);
        if (success != null || failed != null) {
            future.addListener((ChannelFutureListener) channelFuture -> {
                if (channelFuture.isSuccess()) {
                    if (success != null) {
                        channelFuture.channel().eventLoop().execute(success);
                    }
                } else {
                    if (failed != null) {
                        channelFuture.channel().eventLoop().execute(() -> failed.accept(channelFuture.cause()));
                    }
                }
            });
        }
    }

    @Override
    public void destroy() {
        // 销毁时不需要重连
        this.cancelReconnect = true;
        workerGroup.shutdownGracefully().addListener(future -> {
            if (future.isSuccess()) {
                this.setStatus(ClientStatusEnums.DESTROYED);
                // 设置完 DESTROYED 后再删除监听器
                super.destroy();
            } else {
                throw new BaseException("client销毁失败", future.cause());
            }
        });
    }

    @Override
    protected String getWebSocketUriString() {
        return getConfig().getWebsocketUri();
    }

    @Override
    protected void setStatus(ClientStatusEnums status) {
        if (log.isDebugEnabled()) {
            if (getStatus() != status) {
                log.debug("{} 状态变化 {} => {}\n", getClass().getSimpleName(), getStatus(), status);
            }
        }
        super.setStatus(status);
    }

    @Override
    public void sendDanmu(Object danmu, Runnable success, Consumer<Throwable> failed) {
        throw new BaseException("暂未支持该功能");
    }

    @Override
    public void clickLike(int count, Runnable success, Consumer<Throwable> failed) {
        throw new BaseException("暂未支持该功能");
    }

    /**
     * 发送弹幕前判断是否可以发送
     *
     * @param checkConnected 是否检查Client连接状态
     */
    protected boolean checkCanSendDanmu(boolean checkConnected) {
        if (checkConnected && getStatus() != ClientStatusEnums.CONNECTED) {
            throw new BaseException("连接未建立，无法发送弹幕");
        }
        if (System.currentTimeMillis() - this.lastSendDanmuTimeInMillis <= getConfig().getMinSendDanmuPeriod()) {
            if (log.isWarnEnabled()) {
                log.warn("发送弹幕频率过快，忽略该次发送");
            }
            return false;
        }
        return true;
    }

    protected boolean checkCanSendDanmu() {
        return checkCanSendDanmu(true);
    }

    /**
     * 发送弹幕后调用该方法
     */
    protected void finishSendDanmu() {
        this.lastSendDanmuTimeInMillis = System.currentTimeMillis();
        if (log.isDebugEnabled()) {
            log.debug("弹幕发送完成");
        }
    }

    /**
     * 建立连接失败后调用
     */
    private void _connectFailed(Consumer<Throwable> failed, Throwable e) {
        log.error("连接建立失败", e);
        this.onConnectFailed(this.connectionHandler);
        if (failed != null) {
            failed.accept(e);
        }
    }
}
