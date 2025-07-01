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

package handler;

import client.WebSocketLiveChatClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import lombok.extern.slf4j.Slf4j;
import org.caesar.crawler.live.netty.base.listener.IBaseConnectionListener;
import org.caesar.crawler.live.netty.client.handler.BaseNettyClientConnectionHandler;
import org.caesar.crawler.live.netty.servers.IBaseConnectionHandler;

import java.util.function.Supplier;


/**
 * 连接处理器
 *
 * @author mjz
 * @date 2024/3/8
 */
@Slf4j
@ChannelHandler.Sharable
public class WebSocketConnectionHandler extends BaseNettyClientConnectionHandler<WebSocketLiveChatClient, WebSocketConnectionHandler> {

    private final IBaseConnectionHandler connectionHandler;

    public WebSocketConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, IBaseConnectionHandler connectionHandler, WebSocketLiveChatClient client, IBaseConnectionListener<WebSocketConnectionHandler> listener) {
        super(webSocketProtocolHandler, client, listener);
        this.connectionHandler = connectionHandler;
    }

    public WebSocketConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, IBaseConnectionHandler connectionHandler, WebSocketLiveChatClient client) {
        this(webSocketProtocolHandler, connectionHandler, client, null);
    }

    public WebSocketConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, IBaseConnectionHandler connectionHandler) {
        this(webSocketProtocolHandler, connectionHandler, null);
    }

    public WebSocketConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler) {
        this(webSocketProtocolHandler, null);
    }

    @Override
    public void sendHeartbeat(Channel channel) {
        if (connectionHandler == null) {
            return;
        }
        connectionHandler.sendHeartbeat(channel);
    }

    @Override
    public void sendAuthRequest(Channel channel) {
        if (connectionHandler == null) {
            return;
        }
        connectionHandler.sendAuthRequest(channel);
    }
}
