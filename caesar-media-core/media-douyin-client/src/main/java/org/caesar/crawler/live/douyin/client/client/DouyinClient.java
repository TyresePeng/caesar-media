package org.caesar.crawler.live.douyin.client.client;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.LoadState;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.caesar.crawler.live.douyin.client.build.DouyinParamBuild;
import org.caesar.crawler.live.douyin.client.enums.PublishTimeType;
import org.caesar.crawler.live.douyin.client.enums.SearchChannelType;
import org.caesar.crawler.live.douyin.client.enums.SearchSortType;
import org.caesar.crawler.live.douyin.client.utils.RequestParametersUtils;
import org.caesar.crawler.live.netty.base.exception.MediaException;
import org.caesar.crawler.live.netty.util.OrLiveChatHttpUtil;
import org.caesar.media.browser.factory.PlaywrightFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // Playwright stealth 插件脚本路径（用于防检测）
    private static final String STEALTH_SCRIPT_PATH = "js/stealth.min.js";

    // 抖音主页地址（用于模拟访问）
    private static final String DOUYIN_URL = "https://www.douyin.com/?recommend=1";

    // 固定来源组 ID
    private static final String FROM_GROUP_ID = "7378810571505847586";


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
        return factory.doWithContext(ctx -> {
            // 注入 stealth.min.js 脚本，防止浏览器被识别为自动化
            ctx.addInitScript(STEALTH_SCRIPT_PATH);

            // 新建页面，导航到抖音主页，等待 DOM 加载完成
            Page page = ctx.newPage();
            page.navigate(DOUYIN_URL);
            page.waitForLoadState(LoadState.DOMCONTENTLOADED);

            // 获取页面 cookie 列表并转换为字符串
            List<Cookie> cookies = ctx.cookies();
            String cookieStr = RequestParametersUtils.convertCookiesToString(cookies);

            // 构造请求参数
            Map<String, String> queryParams = buildQueryParams(keyword, offset, count,
                    publishTimeType, searchChannelType, searchSortType);

            // 获取浏览器 User-Agent
            String userAgent = page.evaluate("() => navigator.userAgent").toString();

            // 构造请求头部
            Map<String, String> headers = buildHeaders(userAgent, cookieStr);

            // 从 localStorage 读取 xmst（用于签名）
            Map<String, String> localStorage = (Map<String, String>) page.evaluate("() => window.localStorage");

            String uri ="/aweme/v1/web/general/search/single/";
            // 获取完整请求参数，包含签名参数 a_bogus
            Map<String, Object> commonParams = DouyinParamBuild.getCommonParams(queryParams,uri, localStorage.get("xmst"), userAgent);

            HttpResponse httpResponse = OrLiveChatHttpUtil.doGet("https://www.douyin.com" + uri, commonParams, headers);
            if (!httpResponse.isOk()) {
                log.error("请求抖音关键字查询接口失败: {}", httpResponse.getStatus());
                throw new MediaException("请求抖音关键字查询接口失败: " + httpResponse.getStatus());
            }
            String response = httpResponse.body();
            if (StrUtil.isBlank(response)){
                throw new MediaException("抖音响应为空");
            }
            // 若响应为空，返回 null；否则转换为 JSONObject 并返回
            return StringUtils.isBlank(response) ? null : new JSONObject(response);
        });
    }

    /**
     * 构建搜索请求参数
     *
     * @param keyword           查询关键词
     * @param offset            分页偏移
     * @param count             请求条数
     * @param publishTimeType   发布时间过滤类型
     * @param searchChannelType 搜索渠道类型
     * @param searchSortType    搜索排序类型
     * @return 构造好的查询参数 Map
     */
    private static Map<String, String> buildQueryParams(String keyword,
                                                 int offset,
                                                 int count,
                                                 PublishTimeType publishTimeType,
                                                 SearchChannelType searchChannelType,
                                                 SearchSortType searchSortType) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("search_channel", searchChannelType.getValue());
        queryParams.put("enable_history", "1");
        queryParams.put("keyword", keyword);
        queryParams.put("search_source", "switch_tab");
        queryParams.put("query_correct_type", "1");
        // 固定使用 1
        queryParams.put("is_filter_search", "1");
        queryParams.put("from_group_id", FROM_GROUP_ID);
        queryParams.put("offset", String.valueOf(offset));
        queryParams.put("count", String.valueOf(count));
        queryParams.put("need_filter_settings", "0");
        // 固定示例，可改为动态
        queryParams.put("search_id", "2025061210580105A538BD0C0AA8FDF79B");

        // 构建 filter_selected 字段（嵌套 JSON 字符串）
        JSONObject filterSelected = new JSONObject();
        filterSelected.put("sort_type", searchSortType.getValue());
        filterSelected.put("publish_time", publishTimeType.getValue());
        queryParams.put("filter_selected", filterSelected.toString());

        return queryParams;
    }

    /**
     * 构建请求头
     *
     * @param userAgent 浏览器 User-Agent
     * @param cookieStr Cookie 字符串
     * @return 构造好的请求头 Map
     */
    private static Map<String, String> buildHeaders(String userAgent, String cookieStr) {
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", userAgent);
        headers.put("Cookie", cookieStr);
        headers.put("Host", "www.douyin.com");
        headers.put("Origin", DOUYIN_URL);
        headers.put("Referer", DOUYIN_URL);
        headers.put("Content-Type", "application/json;charset=UTF-8");
        return headers;
    }
}
