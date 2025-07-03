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
    public static JSONObject queryKeyWord(
                                          String keyword,
                                          int offset,
                                          int count,
                                          PublishTimeType publishTimeType,
                                          SearchChannelType searchChannelType,
                                          SearchSortType searchSortType) {
        // 在 Playwright 浏览器上下文中执行相关操作
        return DouyinApis.queryKeyWord( keyword, offset, count, publishTimeType, searchChannelType, searchSortType);
    }
}
