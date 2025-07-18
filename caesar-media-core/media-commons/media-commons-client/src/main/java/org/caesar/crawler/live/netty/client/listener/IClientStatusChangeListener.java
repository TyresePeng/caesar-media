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

package org.caesar.crawler.live.netty.client.listener;

import org.caesar.crawler.live.netty.client.enums.ClientStatusEnums;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * 客户端状态变化Listener
 *
 * @author mjz
 * @date 2024/3/6
 * @since 0.2.2
 */
public interface IClientStatusChangeListener extends PropertyChangeListener {

    @Override
    default void propertyChange(PropertyChangeEvent evt) {
        this.onStatusChange(evt, (ClientStatusEnums) evt.getOldValue(), (ClientStatusEnums) evt.getNewValue());
    }

    void onStatusChange(PropertyChangeEvent evt, ClientStatusEnums oldStatus, ClientStatusEnums newStatus);
}
