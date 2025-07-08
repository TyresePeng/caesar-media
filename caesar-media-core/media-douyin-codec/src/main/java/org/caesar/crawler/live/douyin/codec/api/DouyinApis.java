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

package org.caesar.crawler.live.douyin.codec.api;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.caesar.crawler.live.douyin.codec.build.DouyinParamBuild;
import org.caesar.crawler.live.douyin.codec.constant.DouyinGiftCountCalculationTimeEnum;
import org.caesar.crawler.live.douyin.codec.constant.DouyinRoomStatusEnum;
import org.caesar.crawler.live.netty.base.enums.PublishTimeType;
import org.caesar.crawler.live.netty.base.enums.SearchChannelType;
import org.caesar.crawler.live.netty.base.enums.SearchSortType;
import org.caesar.crawler.live.douyin.codec.msg.DouyinGiftMsg;
import org.caesar.crawler.live.douyin.codec.protobuf.GiftMessage;
import org.caesar.crawler.live.douyin.codec.room.DouyinRoomInitResult;
import org.caesar.crawler.live.netty.base.exception.BaseException;
import org.caesar.crawler.live.netty.util.OrJacksonUtil;
import org.caesar.crawler.live.netty.util.OrLiveChatCookieUtil;
import org.caesar.crawler.live.netty.util.OrLiveChatHttpUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * @author mjz
 * @date 2024/1/3
 */
@Slf4j
public class DouyinApis {

    public static final String KEY_COOKIE_TTWID = "ttwid";
    public static final String KEY_COOKIE_MS_TOKEN = "msToken";
    public static final String KEY_COOKIE_AC_NONCE = "__ac_nonce";
    public static final String MS_TOKEN_BASE_STRING = RandomUtil.BASE_CHAR_NUMBER_LOWER + "=_";
    public static final int MS_TOKEN_LENGTH = 116;
    public static final int AC_NONCE_LENGTH = 21;
    public static final String PATTERN_USER_UNIQUE_ID = "\\\\\"user_unique_id\\\\\":\\\\\"(\\d+)\\\\\"";
    public static final String PATTERN_ROOM_ID = "\\\\\"roomId\\\\\":\\\\\"(\\d+)\\\\\"";
    public static final String PATTERN_ROOM_STATUS = "\\\\\"status_str\\\\\":\\\\\"(\\d+)\\\\\"";
    public static final String PATTERN_ROOM_INFO = "\\\\\"roomInfo\\\\\":(\\{.*?\\}),\\\\\"emojiList\\\\\"";
    // 抖音主页地址（
    private static final String DOUYIN_URL = "https://www.douyin.com/?recommend=1";
    // 固定来源组 ID
    private static final String FROM_GROUP_ID = "7378810571505847586";
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36";

    /**
     * 礼物连击缓存
     */
    private static final TimedCache<String, GiftMessage> DOUYIN_GIFT_MSG_CACHE = new TimedCache<>(300 * 1000L, new ConcurrentHashMap<>());

    @SneakyThrows
    public static DouyinRoomInitResult roomInit(Object roomId, String cookie, DouyinRoomInitResult roomInitResult) {
        Map<String, String> cookieMap = OrLiveChatCookieUtil.parseCookieString(cookie);

        @Cleanup HttpResponse response1 = OrLiveChatHttpUtil.createGet("https://live.douyin.com/").cookie(cookie).execute();
        String ttwid = OrLiveChatCookieUtil.getCookieByName(cookieMap, KEY_COOKIE_TTWID, () -> response1.getCookie(KEY_COOKIE_TTWID).getValue());
        String msToken = OrLiveChatCookieUtil.getCookieByName(cookieMap, KEY_COOKIE_MS_TOKEN, () -> RandomUtil.randomString(MS_TOKEN_BASE_STRING, MS_TOKEN_LENGTH));
        String __ac_nonce = OrLiveChatCookieUtil.getCookieByName(cookieMap, KEY_COOKIE_AC_NONCE, () -> RandomUtil.randomString(AC_NONCE_LENGTH));

        @Cleanup HttpResponse response2 = OrLiveChatHttpUtil.createGet("https://live.douyin.com/" + roomId).cookie(StrUtil.emptyToDefault(cookie, KEY_COOKIE_TTWID + "=" + ttwid + "; " + KEY_COOKIE_MS_TOKEN + "=" + msToken + "; " + KEY_COOKIE_AC_NONCE + "=" + __ac_nonce)).execute();
        if (response2.getStatus() != HttpStatus.HTTP_OK) {
            throw new BaseException("获取" + roomId + "真实房间ID失败");
        }
        String body2 = response2.body();
        // 生成7300000000000000000到7999999999999999999之间的随机数
        String user_unique_id = StrUtil.emptyToDefault(ReUtil.getGroup1(PATTERN_USER_UNIQUE_ID, body2), String.valueOf(RandomUtil.randomLong(730_000_000_000_000_0000L, 7_999_999_999_999_999_999L)));
        long realRoomId;
        String realRoomIdString = ReUtil.getGroup1(PATTERN_ROOM_ID, body2);
        try {
            realRoomId = NumberUtil.parseLong(realRoomIdString);
        } catch (Exception e) {
            throw new BaseException("获取" + roomId + "真实房间ID失败");
        }
        int roomStatus;
        String roomStatusString = ReUtil.getGroup1(PATTERN_ROOM_STATUS, body2);
        try {
            roomStatus = NumberUtil.parseInt(roomStatusString);
        } catch (Exception e) {
            throw new BaseException("获取" + roomId + "直播间状态失败");
        }

        String roomInfoString = null;
        List<String> allGroups = ReUtil.getAllGroups(Pattern.compile(PATTERN_ROOM_INFO), body2, false, true);
        for (String allGroup : allGroups) {
            if (!StrUtil.equals(allGroup, "{}")) {
                roomInfoString = StringEscapeUtils.unescapeJson(allGroup);
                break;
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("roomInfoString: {}", roomInfoString);
        }

        JsonNode roomInfoJsonNode = OrJacksonUtil.getInstance().readTree(roomInfoString);

        roomInitResult = Optional.ofNullable(roomInitResult).orElseGet(() -> DouyinRoomInitResult.builder().build());
        roomInitResult.setTtwid(ttwid);
        roomInitResult.setMsToken(msToken);
        roomInitResult.setAcNonce(__ac_nonce);
        roomInitResult.setRealRoomId(realRoomId);
        roomInitResult.setUserUniqueId(user_unique_id);
        roomInitResult.setRoomStatus(DouyinRoomStatusEnum.getByCode(roomStatus));
        roomInitResult.setRoomInfoJsonNode(roomInfoJsonNode);
        return roomInitResult;
    }

    public static DouyinRoomInitResult roomInit(Object roomId, String cookie) {
        return roomInit(roomId, cookie, null);
    }

    public static DouyinRoomInitResult roomInit(Object roomId) {
        return roomInit(roomId, null, null);
    }

    public static DouyinRoomInitResult roomInit(Object roomId, DouyinRoomInitResult roomInitResult) {
        return roomInit(roomId, null, roomInitResult);
    }

    /**
     * 计算抖音直播间收到礼物的个数
     *
     * @param msg DouyinGiftMsg
     * @return 礼物个数
     */
    public static int calculateGiftCount(DouyinGiftMsg msg, DouyinGiftCountCalculationTimeEnum calculationTimeEnum) {
        if (msg == null || msg.getMsg() == null) {
            return 0;
        }

        GiftMessage giftMessage = msg.getMsg();
        long giftCount = 0;
        if (calculationTimeEnum == DouyinGiftCountCalculationTimeEnum.COMBO_END) {
            if (!giftMessage.getGift().getCombo() || giftMessage.getRepeatEnd() == 1) {// 非连击中
                long comboCount = giftMessage.getComboCount();
                if (giftMessage.getGroupCount() != 1L) {// 每点击一次送礼的数量不是1时
                    comboCount = giftMessage.getGroupCount() * comboCount;
                }
                giftCount = comboCount;
            }
        } else {
            // DouyinGiftCountCalculationTimeEnum.IMMEDIATELY
            long groupId = giftMessage.getGroupId();
            long giftId = giftMessage.getGiftId();
            // groupId有时会重复
            String key = groupId + "-" + msg.getUid() + "-" + giftId;
            if (DOUYIN_GIFT_MSG_CACHE.containsKey(key)) {
                GiftMessage giftMessageByGroupId = DOUYIN_GIFT_MSG_CACHE.get(key);
                long repeatCountByGroupId = giftMessageByGroupId.getRepeatCount();
                giftCount = giftMessage.getRepeatCount() - repeatCountByGroupId;
            } else {
                giftCount = giftMessage.getRepeatCount();
            }
            // 存在顺序错误的情况，后收到的消息礼物个数反而减少了，跳过缓存该消息，但仍保存计算后的小于0的礼物个数
            if (giftCount > 0) {
                DOUYIN_GIFT_MSG_CACHE.put(key, giftMessage);
            }
        }

        msg.setCalculatedGiftCount((int) giftCount);
        return (int) giftCount;
    }

    /**
     * 搜索关键词
     *
     * @param cookie            cookie
     * @param keyword           关键词
     * @param offset            搜索偏移
     * @param count             搜索数量
     * @param publishTimeType   发布时间类型
     * @param searchChannelType 搜索渠道类型
     * @param searchSortType    搜索排序类型
     */
    public static JSONObject queryKeyWord(String cookie,
                                          String keyword,
                                          int offset,
                                          int count,
                                          PublishTimeType publishTimeType,
                                          SearchChannelType searchChannelType,
                                          SearchSortType searchSortType) {
        Map<String, String> cookieMap = OrLiveChatCookieUtil.parseCookieString(cookie);
        String msToken = OrLiveChatCookieUtil.getCookieByName(cookieMap, KEY_COOKIE_MS_TOKEN, () -> RandomUtil.randomString(MS_TOKEN_BASE_STRING, MS_TOKEN_LENGTH));
        // 构造请求参数
        Map<String, String> queryParams = DouyinParamBuild.buildQueryKeyWordParams(keyword, offset, count,
                publishTimeType, searchChannelType, searchSortType);
        // 获取浏览器 User-Agent
        // 构造请求头部
        Map<String, String> headers = DouyinParamBuild.buildDouYinHeaders(USER_AGENT, cookie);

        String uri = "/aweme/v1/web/general/search/single/";
        // 获取完整请求参数，包含签名参数 a_bogus
        Map<String, Object> commonParams = DouyinParamBuild.getCommonParams(queryParams, uri, msToken, USER_AGENT);
        HttpResponse httpResponse = OrLiveChatHttpUtil.doGet("https://www.douyin.com" + uri, commonParams, headers);
        if (httpResponse.getStatus() != HttpStatus.HTTP_OK) {
            throw new BaseException("关键字查询,查询" + keyword + "失败");
        }
        return new JSONObject(httpResponse.body());
    }
}
