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

package org.caesar.crawler.live.douyin.codec.msg;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.caesar.crawler.live.douyin.codec.msg.base.BaseDouyinMsg;
import org.caesar.crawler.live.douyin.codec.protobuf.SocialMessage;
import org.caesar.crawler.live.netty.base.constant.SocialActionEnum;
import org.caesar.crawler.live.netty.base.msg.ISocialMsg;
import org.caesar.crawler.live.netty.util.serializer.ProtobufToBase64Serializer;

/**
 * @author mjz
 * @date 2024/3/28
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DouyinSocialMsg extends BaseDouyinMsg implements ISocialMsg {

    @JsonSerialize(using = ProtobufToBase64Serializer.class)
    private SocialMessage msg;

    @Override
    public String getBadgeName() {
        return msg.getUser().getFansClub().getData().getClubName();
    }

    @Override
    public byte getBadgeLevel() {
        return (byte) msg.getUser().getFansClub().getData().getLevel();
    }

    @Override
    public String getUid() {
        return Long.toString(msg.getUser().getId());
    }

    @Override
    public String getUsername() {
        return msg.getUser().getNickName();
    }

    @Override
    public String getUserAvatar() {
        return CollUtil.getFirst(msg.getUser().getAvatarThumb().getUrlListListList());
    }

    @Override
    public SocialActionEnum getSocialAction() {
        if (msg == null) {
            return null;
        }

        if (msg.getAction() == 1L) {
            // 关注
            return SocialActionEnum.SUBSCRIBE;
        }
        if (msg.getAction() == 3L) {
            // 分享
            return SocialActionEnum.SHARE;
        }

        return null;
    }
}
