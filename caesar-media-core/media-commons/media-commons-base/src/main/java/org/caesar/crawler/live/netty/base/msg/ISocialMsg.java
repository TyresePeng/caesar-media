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

package org.caesar.crawler.live.netty.base.msg;

import org.caesar.crawler.live.netty.base.constant.SocialActionEnum;

/**
 * 社交消息
 *
 * @author mjz
 * @date 2024/5/9
 * @since 0.7.1
 */
public interface ISocialMsg extends IMsg {

    /**
     * 粉丝牌名称
     */
    String getBadgeName();

    /**
     * 粉丝牌等级
     */
    byte getBadgeLevel();

    /**
     * 弹幕发送者id
     */
    String getUid();

    /**
     * 弹幕发送者用户名
     */
    String getUsername();

    /**
     * 弹幕发送者头像地址
     *
     * @since 0.0.11
     */
    default String getUserAvatar() {
        return null;
    }

    /**
     * 动作
     */
    SocialActionEnum getSocialAction();

}
