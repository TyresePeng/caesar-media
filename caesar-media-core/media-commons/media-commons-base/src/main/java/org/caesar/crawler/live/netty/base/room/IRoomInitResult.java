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

package org.caesar.crawler.live.netty.base.room;


import org.caesar.crawler.live.netty.base.constant.RoomLiveStatusEnum;
import org.caesar.crawler.live.netty.base.constant.RoomLiveStreamQualityEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mjz
 * @date 2025/1/4
 */
public interface IRoomInitResult {

    /**
     * 获取房间标题
     */
    String getRoomTitle();

    /**
     * 获取房间描述
     */
    default String getRoomDescription() {
        return null;
    }

    /**
     * 获取房间直播状态
     */
    RoomLiveStatusEnum getRoomLiveStatus();

    /**
     * 获取房间直播流地址
     *
     * @param qualities 直播流质量
     * @see RoomLiveStreamQualityEnum
     */
    default List<IRoomLiveStreamInfo> getRoomLiveStreamUrls(RoomLiveStreamQualityEnum... qualities) {
        return null;
    }

    default Map<RoomLiveStreamQualityEnum, List<String>> getRoomLiveStreamUrlMap(RoomLiveStreamQualityEnum... qualities) {
        List<IRoomLiveStreamInfo> roomLiveStreamUrls = getRoomLiveStreamUrls(qualities);
        Map<RoomLiveStreamQualityEnum, List<String>> map = new HashMap<>();
        if (roomLiveStreamUrls != null && !roomLiveStreamUrls.isEmpty()) {
            for (IRoomLiveStreamInfo roomLiveStreamUrl : roomLiveStreamUrls) {
                map.put(roomLiveStreamUrl.getQuality(), roomLiveStreamUrl.getUrls());
            }
        }
        return map;
    }
}
