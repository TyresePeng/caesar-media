package org.caesar.crawler.live.douyin.client.client;

import cn.hutool.json.JSONObject;
import com.microsoft.playwright.options.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.caesar.crawler.live.douyin.client.utils.RequestParametersUtils;
import org.caesar.crawler.live.douyin.codec.api.DouyinApis;
import org.caesar.crawler.live.netty.base.enums.PublishTimeType;
import org.caesar.crawler.live.netty.base.enums.SearchChannelType;
import org.caesar.crawler.live.netty.base.enums.SearchSortType;
import org.caesar.media.browser.factory.PlaywrightFactory;

import java.util.List;

/**
 * @Description: 抖音请求客户端
 * <p>
 * 负责通过 Playwright 模拟浏览器环境获取必要的 cookie、userAgent 及 localStorage 信息，
 * 进而构造请求参数并调用抖音开放接口完成关键字查询。
 * <p>
 * 具备自动重试机制和定时会话检查，保障请求稳定性和会话有效性。
 * </p>
 * @Author: peng.guo
 * @Create: 2025-05-22 16:08
 * @Version 1.1 (优化了代码结构和注释)
 **/
@Slf4j
public class DouyinClient {


    /**
     * 关键字查询接口（内部调用）
     * <p>
     * 使用传入的 PlaywrightFactory 进行上下文操作，模拟浏览器环境完成请求参数获取，
     * 并调用抖音关键字查询接口。
     * </p>
     *
     * @param factory           PlaywrightFactory 实例，用于创建浏览器上下文
     * @param keyword           查询关键字
     * @param offset            数据分页起始位置
     * @param count             请求数据条数
     * @param publishTimeType   发布时段过滤枚举
     * @param searchChannelType 搜索渠道类型枚举
     * @param searchSortType    搜索结果排序类型枚举
     * @return 返回接口响应的 JSON 对象，查询失败返回 null
     */
    public static JSONObject queryKeyWord(PlaywrightFactory factory,
                                          String keyword,
                                          int offset,
                                          int count,
                                          PublishTimeType publishTimeType,
                                          SearchChannelType searchChannelType,
                                          SearchSortType searchSortType) {
        // 在 Playwright 浏览器上下文中执行相关操作
        List<Cookie> cookie = factory.getCookie();
        return DouyinApis.queryKeyWord(RequestParametersUtils.convertCookiesToString(cookie), keyword, offset, count, publishTimeType, searchChannelType, searchSortType);
    }


    /**
     * 关键字查询接口（内部调用）
     * <p>
     * 使用传入的 PlaywrightFactory 进行上下文操作，模拟浏览器环境完成请求参数获取，
     * 并调用抖音关键字查询接口。
     * </p>
     *
     * @param cookie           cookie
     * @param keyword           查询关键字
     * @param offset            数据分页起始位置
     * @param count             请求数据条数
     * @param publishTimeType   发布时段过滤枚举
     * @param searchChannelType 搜索渠道类型枚举
     * @param searchSortType    搜索结果排序类型枚举
     * @return 返回接口响应的 JSON 对象，查询失败返回 null
     */
    public static JSONObject queryKeyWord(String cookie,
                                          String keyword,
                                          int offset,
                                          int count,
                                          PublishTimeType publishTimeType,
                                          SearchChannelType searchChannelType,
                                          SearchSortType searchSortType) {
        // 在 Playwright 浏览器上下文中执行相关操作
        return DouyinApis.queryKeyWord(cookie, keyword, offset, count, publishTimeType, searchChannelType, searchSortType);
    }

    /**
     * 关键字查询接口（内部调用）
     * <p>
     * 使用传入的 PlaywrightFactory 进行上下文操作，模拟浏览器环境完成请求参数获取，
     * 并调用抖音关键字查询接口。
     * </p>
     *
     * @param keyword           查询关键字
     * @param offset            数据分页起始位置
     * @param count             请求数据条数
     * @param publishTimeType   发布时段过滤枚举
     * @param searchChannelType 搜索渠道类型枚举
     * @param searchSortType    搜索结果排序类型枚举
     * @return 返回接口响应的 JSON 对象，查询失败返回 null
     */
    public static JSONObject queryKeyWord(String keyword,
                                          int offset,
                                          int count,
                                          PublishTimeType publishTimeType,
                                          SearchChannelType searchChannelType,
                                          SearchSortType searchSortType) {
        // 在 Playwright 浏览器上下文中执行相关操作
        return DouyinApis.queryKeyWord(null, keyword, offset, count, publishTimeType, searchChannelType, searchSortType);
    }

    /**
     * 获取用户视频帖子（使用PlaywrightFactory）
     *
     * @param factory    PlaywrightFactory 实例
     * @param secUserId  用户的sec_user_id
     * @param maxCursor  分页游标，首次请求传0
     * @param count      每页数量，默认10
     * @return 用户视频列表响应
     */
    public static JSONObject getAwemePost(PlaywrightFactory factory, String secUserId, long maxCursor, int count) {
        List<Cookie> cookie = factory.getCookie();
        return DouyinApis.getAwemePost(RequestParametersUtils.convertCookiesToString(cookie), secUserId, maxCursor, count);
    }

    /**
     * 获取用户视频帖子（使用Cookie字符串）
     *
     * @param cookie     Cookie 字符串
     * @param secUserId  用户的sec_user_id
     * @param maxCursor  分页游标，首次请求传0
     * @param count      每页数量，默认10
     * @return 用户视频列表响应
     */
    public static JSONObject getAwemePost(String cookie, String secUserId, long maxCursor, int count) {
        return DouyinApis.getAwemePost(cookie, secUserId, maxCursor, count);
    }

    /**
     * 获取用户视频帖子（无Cookie，可能受限）
     *
     * @param secUserId  用户的sec_user_id
     * @param maxCursor  分页游标，首次请求传0
     * @param count      每页数量，默认10
     * @return 用户视频列表响应
     */
    public static JSONObject getAwemePost(String secUserId, long maxCursor, int count) {
        return DouyinApis.getAwemePost(null, secUserId, maxCursor, count);
    }

    /**
     * 获取视频详情（使用PlaywrightFactory）
     *
     * @param factory PlaywrightFactory 实例
     * @param awemeId 视频ID
     * @return 视频详情响应
     */
    public static JSONObject getAwemeDetail(PlaywrightFactory factory, String awemeId) {
        List<Cookie> cookie = factory.getCookie();
        return DouyinApis.getAwemeDetail(RequestParametersUtils.convertCookiesToString(cookie), awemeId);
    }

    /**
     * 获取视频详情（使用Cookie字符串）
     *
     * @param cookie  Cookie 字符串
     * @param awemeId 视频ID
     * @return 视频详情响应
     */
    public static JSONObject getAwemeDetail(String cookie, String awemeId) {
        return DouyinApis.getAwemeDetail(cookie, awemeId);
    }

    /**
     * 获取视频详情（无Cookie，可能受限）
     *
     * @param awemeId 视频ID
     * @return 视频详情响应
     */
    public static JSONObject getAwemeDetail(String awemeId) {
        return DouyinApis.getAwemeDetail(null, awemeId);
    }

    /**
     * 获取评论列表（使用PlaywrightFactory）
     *
     * @param factory PlaywrightFactory 实例
     * @param awemeId 视频ID
     * @param cursor  分页游标，首次请求传0
     * @param count   每页数量，默认20
     * @return 评论列表响应
     */
    public static JSONObject getCommentList(PlaywrightFactory factory, String awemeId, long cursor, int count) {
        List<Cookie> cookie = factory.getCookie();
        return DouyinApis.getCommentList(RequestParametersUtils.convertCookiesToString(cookie), awemeId, cursor, count);
    }

    /**
     * 获取评论列表（使用Cookie字符串）
     *
     * @param cookie  Cookie 字符串
     * @param awemeId 视频ID
     * @param cursor  分页游标，首次请求传0
     * @param count   每页数量，默认20
     * @return 评论列表响应
     */
    public static JSONObject getCommentList(String cookie, String awemeId, long cursor, int count) {
        return DouyinApis.getCommentList(cookie, awemeId, cursor, count);
    }

    /**
     * 获取评论列表（无Cookie，可能受限）
     *
     * @param awemeId 视频ID
     * @param cursor  分页游标，首次请求传0
     * @param count   每页数量，默认20
     * @return 评论列表响应
     */
    public static JSONObject getCommentList(String awemeId, long cursor, int count) {
        return DouyinApis.getCommentList(null, awemeId, cursor, count);
    }

    /**
     * 获取评论回复（使用PlaywrightFactory）
     *
     * @param factory   PlaywrightFactory 实例
     * @param awemeId   视频ID
     * @param commentId 评论ID
     * @param cursor    分页游标，首次请求传0
     * @param count     每页数量，默认20
     * @return 评论回复响应
     */
    public static JSONObject getCommentReply(PlaywrightFactory factory, String awemeId, String commentId, long cursor, int count) {
        List<Cookie> cookie = factory.getCookie();
        return DouyinApis.getCommentReply(RequestParametersUtils.convertCookiesToString(cookie), awemeId, commentId, cursor, count);
    }

    /**
     * 获取评论回复（使用Cookie字符串）
     *
     * @param cookie    Cookie 字符串
     * @param awemeId   视频ID
     * @param commentId 评论ID
     * @param cursor    分页游标，首次请求传0
     * @param count     每页数量，默认20
     * @return 评论回复响应
     */
    public static JSONObject getCommentReply(String cookie, String awemeId, String commentId, long cursor, int count) {
        return DouyinApis.getCommentReply(cookie, awemeId, commentId, cursor, count);
    }

    /**
     * 获取评论回复（无Cookie，可能受限）
     *
     * @param awemeId   视频ID
     * @param commentId 评论ID
     * @param cursor    分页游标，首次请求传0
     * @param count     每页数量，默认20
     * @return 评论回复响应
     */
    public static JSONObject getCommentReply(String awemeId, String commentId, long cursor, int count) {
        return DouyinApis.getCommentReply(null, awemeId, commentId, cursor, count);
    }

    /**
     * 获取用户信息（使用PlaywrightFactory）
     *
     * @param factory   PlaywrightFactory 实例
     * @param secUserId 用户的sec_user_id
     * @return 用户信息响应
     */
    public static JSONObject getUserProfile(PlaywrightFactory factory, String secUserId) {
        List<Cookie> cookie = factory.getCookie();
        return DouyinApis.getUserProfile(RequestParametersUtils.convertCookiesToString(cookie), secUserId);
    }

    /**
     * 获取用户信息（使用Cookie字符串）
     *
     * @param cookie    Cookie 字符串
     * @param secUserId 用户的sec_user_id
     * @return 用户信息响应
     */
    public static JSONObject getUserProfile(String cookie, String secUserId) {
        return DouyinApis.getUserProfile(cookie, secUserId);
    }

    /**
     * 获取用户信息（无Cookie，可能受限）
     *
     * @param secUserId 用户的sec_user_id
     * @return 用户信息响应
     */
    public static JSONObject getUserProfile(String secUserId) {
        return DouyinApis.getUserProfile(null, secUserId);
    }
}
