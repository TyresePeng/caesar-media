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

package org.caesar.crawler.live.netty.client.config;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.caesar.crawler.live.netty.base.exception.BaseException;
import org.caesar.crawler.live.netty.base.listener.IBaseMsgListener;
import org.caesar.crawler.live.netty.client.IBaseLiveChatClient;
import org.caesar.crawler.live.netty.util.OrLiveChatHttpUtil;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 直播间弹幕客户端配置
 *
 * @author mjz
 * @date 2023/8/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class BaseLiveChatClientConfig {

    /**
     * 默认心跳包发送延迟时间（秒）
     */
    public static final long DEFAULT_HEARTBEAT_INITIAL_DELAY = 15;
    /**
     * 默认心跳包发送周期（秒）
     */
    public static final long DEFAULT_HEARTBEAT_PERIOD = 25;
    /**
     * 默认最小发送弹幕时间间隔（毫秒）
     */
    public static final long DEFAULT_MIN_SEND_DANMU_PERIOD = 3000L;
    /**
     * 默认WebSocket握手超时时间（毫秒）
     */
    public static final long DEFAULT_HANDSHAKE_TIMEOUT_MILLIS = 5 * 1000L;

    protected final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * WebSocket地址，配置后将直接连接该地址，不会自动添加参数
     */
    private String websocketUri;

    /**
     * 消息转发地址，WebSocket Server地址
     *
     * @deprecated Will be removed soon, please use `tech.ordinaryroad:live-chat-client-plugin-forward-{platform}:{version}`,
     * add the forward msg listener by {@link IBaseLiveChatClient#addMsgListener(IBaseMsgListener)}
     */
    @Deprecated
    private String forwardWebsocketUri;

    /**
     * 浏览器中的Cookie
     */
    private String cookie;

    /**
     * 直播间id
     */
    private Object roomId;

    /**
     * 是否启用自动重连
     */
    @Builder.Default
    private boolean autoReconnect = Boolean.TRUE;

    /**
     * 重试延迟时间（秒），默认5s后重试
     */
    @Builder.Default
    private int reconnectDelay = 5;

    /**
     * 首次发送心跳包的延迟时间（秒）
     */
    @Builder.Default
    private long heartbeatInitialDelay = DEFAULT_HEARTBEAT_INITIAL_DELAY;

    /**
     * 心跳包发送周期（秒）
     */
    @Builder.Default
    private long heartbeatPeriod = DEFAULT_HEARTBEAT_PERIOD;

    /**
     * 最小发送弹幕时间间隔（毫秒）
     */
    @Builder.Default
    private long minSendDanmuPeriod = DEFAULT_MIN_SEND_DANMU_PERIOD;

    /**
     * 握手超时时间（毫秒）
     */
    @Builder.Default
    private long handshakeTimeoutMillis = DEFAULT_HANDSHAKE_TIMEOUT_MILLIS;

    /**
     * Socks5代理——地址
     */
    private String socks5ProxyHost;

    /**
     * Socks5代理——端口
     */
    private int socks5ProxyPort;

    /**
     * Socks5代理——用户名
     */
    private String socks5ProxyUsername;

    /**
     * Socks5代理——密码
     */
    private String socks5ProxyPassword;

    public void setWebsocketUri(String websocketUri) {
        String oldValue = this.websocketUri;
        this.websocketUri = websocketUri;
        this.propertyChangeSupport.firePropertyChange("websocketUri", oldValue, websocketUri);
    }

    public void setForwardWebsocketUri(String forwardWebsocketUri) {
        String oldValue = this.forwardWebsocketUri;
        this.forwardWebsocketUri = forwardWebsocketUri;
        this.propertyChangeSupport.firePropertyChange("forwardWebsocketUri", oldValue, forwardWebsocketUri);
    }

    public void setCookie(String cookie) {
        String oldValue = this.cookie;
        this.cookie = cookie;
        this.propertyChangeSupport.firePropertyChange("cookie", oldValue, cookie);
    }

    public void setRoomId(Object roomId) {
        if (!(roomId instanceof Number || roomId instanceof String)) {
            throw new BaseException("房间ID仅支持数字或字符串，所传参数类型：" + roomId.getClass() + "值：" + roomId);
        }
        Object oldValue = this.roomId;
        this.roomId = roomId;
        this.propertyChangeSupport.firePropertyChange("roomId", oldValue, roomId);
    }

    public void setAutoReconnect(boolean autoReconnect) {
        boolean oldValue = this.autoReconnect;
        this.autoReconnect = autoReconnect;
        this.propertyChangeSupport.firePropertyChange("autoReconnect", oldValue, autoReconnect);
    }

    public void setReconnectDelay(int reconnectDelay) {
        int oldValue = this.reconnectDelay;
        this.reconnectDelay = reconnectDelay;
        this.propertyChangeSupport.firePropertyChange("reconnectDelay", oldValue, reconnectDelay);
    }

    public void setHeartbeatInitialDelay(long heartbeatInitialDelay) {
        long oldValue = this.heartbeatInitialDelay;
        this.heartbeatInitialDelay = heartbeatInitialDelay;
        this.propertyChangeSupport.firePropertyChange("heartbeatInitialDelay", oldValue, heartbeatInitialDelay);
    }

    public void setHeartbeatPeriod(long heartbeatPeriod) {
        long oldValue = this.heartbeatPeriod;
        this.heartbeatPeriod = heartbeatPeriod;
        this.propertyChangeSupport.firePropertyChange("heartbeatPeriod", oldValue, heartbeatPeriod);
    }

    public void setMinSendDanmuPeriod(long minSendDanmuPeriod) {
        long oldValue = this.minSendDanmuPeriod;
        this.minSendDanmuPeriod = minSendDanmuPeriod;
        this.propertyChangeSupport.firePropertyChange("minSendDanmuPeriod", oldValue, minSendDanmuPeriod);
    }

    public void setHandshakeTimeoutMillis(long handshakeTimeoutMillis) {
        long oldValue = this.handshakeTimeoutMillis;
        this.handshakeTimeoutMillis = handshakeTimeoutMillis;
        this.propertyChangeSupport.firePropertyChange("handshakeTimeoutMillis", oldValue, handshakeTimeoutMillis);
    }

    public void setSocks5ProxyHost(String socks5ProxyHost) {
        String oldValue = this.socks5ProxyHost;
        this.socks5ProxyHost = socks5ProxyHost;
        this.propertyChangeSupport.firePropertyChange("socks5ProxyHost", oldValue, socks5ProxyHost);

        OrLiveChatHttpUtil.updateProxyHost(socks5ProxyHost);
    }

    public void setSocks5ProxyPort(int socks5ProxyPort) {
        int oldValue = this.socks5ProxyPort;
        this.socks5ProxyPort = socks5ProxyPort;
        this.propertyChangeSupport.firePropertyChange("socks5ProxyPort", oldValue, socks5ProxyPort);

        OrLiveChatHttpUtil.updateProxyPort(socks5ProxyPort);
    }

    public void setSocks5ProxyUsername(String socks5ProxyUsername) {
        String oldValue = this.socks5ProxyUsername;
        this.socks5ProxyUsername = socks5ProxyUsername;
        this.propertyChangeSupport.firePropertyChange("socks5ProxyUsername", oldValue, socks5ProxyUsername);

        OrLiveChatHttpUtil.updateProxyUsername(socks5ProxyUsername);
    }

    public void setSocks5ProxyPassword(String socks5ProxyPassword) {
        String oldValue = this.socks5ProxyPassword;
        this.socks5ProxyPassword = socks5ProxyPassword;
        this.propertyChangeSupport.firePropertyChange("socks5ProxyPassword", oldValue, socks5ProxyPassword);

        OrLiveChatHttpUtil.updateProxyPassword(socks5ProxyPassword);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
