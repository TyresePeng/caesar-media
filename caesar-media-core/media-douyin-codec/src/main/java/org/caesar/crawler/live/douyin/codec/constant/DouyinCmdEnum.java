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

package org.caesar.crawler.live.douyin.codec.constant;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author mjz
 * @date 2024/1/2
 */
@Getter
@RequiredArgsConstructor
public enum DouyinCmdEnum {

    /**
     * 弹幕
     */
    WebcastChatMessage,
    /**
     * 礼物
     */
    WebcastGiftMessage,
    /**
     * 点赞
     */
    WebcastLikeMessage,
    /**
     * 入房
     */
    WebcastMemberMessage,
    /**
     * 房间信息
     */
    WebcastRoomStatsMessage,
    /**
     * 关注{@link org.caesar.crawler.live.douyin.codec.protobuf.DouyinWebcastSocialMessageMsgOuterClass.DouyinWebcastSocialMessageMsg#getAction()}=1、分享{@link org.caesar.crawler.live.douyin.codec.protobuf.DouyinWebcastSocialMessageMsgOuterClass.DouyinWebcastSocialMessageMsg#getAction()}=3
     */
    WebcastSocialMessage,
    WebcastRoomUserSeqMessage,
    /**
     * 粉丝团
     */
    WebcastFansclubMessage,
    /**
     * 直播状态变化
     */
    WebcastControlMessage,
    ;

    public static DouyinCmdEnum getByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        for (DouyinCmdEnum value : values()) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        return null;
    }
}
