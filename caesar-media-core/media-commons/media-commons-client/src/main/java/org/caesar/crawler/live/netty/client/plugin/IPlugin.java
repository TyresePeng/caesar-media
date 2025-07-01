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

package org.caesar.crawler.live.netty.client.plugin;

import org.caesar.crawler.live.netty.base.listener.IBaseMsgListener;
import org.caesar.crawler.live.netty.client.BaseLiveChatClient;

/**
 * 插件接口
 *
 * @author mjz
 * @date 2025/2/11
 * @since 1.5.0
 */
public interface IPlugin {

    <LiveChatClient extends BaseLiveChatClient<?, ?, MsgListener>, MsgListener extends IBaseMsgListener<?, ?>> void register(
            LiveChatClient liveChatClient, Class<MsgListener> msgListenerClass
    );

    <LiveChatClient extends BaseLiveChatClient<?, ?, MsgListener>, MsgListener extends IBaseMsgListener<?, ?>> void unregister(
            LiveChatClient liveChatClient, Class<MsgListener> msgListenerClass
    );
}
