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

package org.caesar.crawler.live.netty.client.handler;

import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import lombok.Getter;
import org.caesar.crawler.live.netty.base.listener.IBaseConnectionListener;
import org.caesar.crawler.live.netty.client.base.BaseNettyClient;
import org.caesar.crawler.live.netty.client.config.BaseNettyClientConfig;
import org.caesar.crawler.live.netty.servers.BaseConnectionHandler;

import java.util.function.Supplier;

/**
 * BaseClientConnectionHandler
 *
 * @author mjz
 * @date 2023/8/27
 */
public abstract class BaseNettyClientConnectionHandler<
        Client extends BaseNettyClient<?, ?, ?, ?, ?, ?, ?>,
        ConnectionHandler extends BaseConnectionHandler<ConnectionHandler>>
        extends BaseConnectionHandler<ConnectionHandler> {

    @Getter
    protected final Client client;

    public BaseNettyClientConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketClientProtocolHandler, Client client, IBaseConnectionListener<ConnectionHandler> listener) {
        super(webSocketClientProtocolHandler, listener);
        this.client = client;
    }

    public BaseNettyClientConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketClientProtocolHandler, Client client) {
        this(webSocketClientProtocolHandler, client, null);
    }

    public BaseNettyClientConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketClientProtocolHandler, IBaseConnectionListener<ConnectionHandler> listener) {
        super(webSocketClientProtocolHandler, listener);
        this.client = null;
    }

    public BaseNettyClientConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketClientProtocolHandler, long roomId) {
        super(webSocketClientProtocolHandler, null);
        this.client = null;
    }

    @Override
    public long getHeartbeatPeriod() {
        if (client == null) {
            return BaseNettyClientConfig.DEFAULT_HEARTBEAT_PERIOD;
        } else {
            return client.getConfig().getHeartbeatPeriod();
        }
    }

    @Override
    public long getHeartbeatInitialDelay() {
        if (client == null) {
            return BaseNettyClientConfig.DEFAULT_HEARTBEAT_INITIAL_DELAY;
        } else {
            return client.getConfig().getHeartbeatInitialDelay();
        }
    }
}
