package org.caesar.crawler.live.douyin.codec.build;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.caesar.crawler.live.netty.base.enums.PublishTimeType;
import org.caesar.crawler.live.netty.base.enums.SearchChannelType;
import org.caesar.crawler.live.netty.base.enums.SearchSortType;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Description: 抖音参数构建
 * @Author: peng.guo
 * @Create: 2025-06-30 16:53
 * @Version 1.0
 **/
@Slf4j
public class DouyinParamBuild {

    public static String JS_SDK;
    // 抖音主页地址（
    private static final String DOUYIN_URL = "https://www.douyin.com/?recommend=1";
    // 固定来源组 ID
    private static final String FROM_GROUP_ID = "7378810571505847586";
    static {
        InputStream resourceAsStream = DouyinParamBuild.class.getResourceAsStream("/js/douyin.js");
        JS_SDK = IoUtil.readUtf8(resourceAsStream);
    }

    private static final String AID = "6383";
    private static final Random RANDOM = new Random();

    /**
     * 构建抖音搜索请求参数
     *
     * @param keyword           查询关键词
     * @param offset            分页偏移
     * @param count             请求条数
     * @param publishTimeType   发布时间过滤类型
     * @param searchChannelType 搜索渠道类型
     * @param searchSortType    搜索排序类型
     * @return 构造好的查询参数 Map
     */
    public static Map<String, String> buildQueryKeyWordParams(String keyword,
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
     * 构建抖音用户视频帖子请求参数
     *
     * @param secUserId 用户的sec_user_id
     * @param maxCursor 分页游标，首次请求传0或空字符串
     * @param count     每页数量，默认18
     * @return 构造好的查询参数 Map
     */
    public static Map<String, String> buildAwemePostParams(String secUserId, long maxCursor, int count) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("sec_user_id", secUserId);
        queryParams.put("count", String.valueOf(count));
        queryParams.put("max_cursor", String.valueOf(maxCursor));
        queryParams.put("locate_query", "false");
        queryParams.put("publish_video_strategy_type", "2");
        return queryParams;
    }

    /**
     * 构建抖音视频详情请求参数
     *
     * @param awemeId 视频ID
     * @return 构造好的查询参数 Map
     */
    public static Map<String, String> buildAwemeDetailParams(String awemeId) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("aweme_id", awemeId);
        queryParams.put("cursor", "0");
        queryParams.put("count", "20");
        queryParams.put("item_type", "0");
        return queryParams;
    }

    /**
     * 构建抖音评论列表请求参数
     *
     * @param awemeId   视频ID
     * @param cursor    分页游标，首次请求传0
     * @param count     每页数量，默认20
     * @return 构造好的查询参数 Map
     */
    public static Map<String, String> buildCommentListParams(String awemeId, long cursor, int count) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("aweme_id", awemeId);
        queryParams.put("cursor", String.valueOf(cursor));
        queryParams.put("count", String.valueOf(count));
        queryParams.put("item_type", "0");
        queryParams.put("rcFT", "");
        queryParams.put("insert_ids", "");
        return queryParams;
    }

    /**
     * 构建抖音评论回复请求参数
     *
     * @param awemeId   视频ID
     * @param commentId 评论ID
     * @param cursor    分页游标，首次请求传0
     * @param count     每页数量，默认20
     * @return 构造好的查询参数 Map
     */
    public static Map<String, String> buildCommentReplyParams(String awemeId, String commentId, long cursor, int count) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("aweme_id", awemeId);
        queryParams.put("comment_id", commentId);
        queryParams.put("cursor", String.valueOf(cursor));
        queryParams.put("count", String.valueOf(count));
        queryParams.put("item_type", "0");
        return queryParams;
    }

    /**
     * 构建抖音用户信息请求参数
     *
     * @param secUserId 用户的sec_user_id
     * @return 构造好的查询参数 Map
     */
    public static Map<String, String> buildUserProfileParams(String secUserId) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("sec_user_id", secUserId);
        queryParams.put("from_tab_name", "main");
        queryParams.put("publish_video_strategy_type", "2");
        return queryParams;
    }

    /**
     * 构建抖音请求头
     *
     * @param userAgent 浏览器 User-Agent
     * @param cookieStr Cookie 字符串
     * @return 构造好的请求头 Map
     */
    public static Map<String, String> buildDouYinHeaders(String userAgent, String cookieStr) {
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", userAgent);
        headers.put("Cookie", cookieStr);
        headers.put("Host", "www.douyin.com");
        headers.put("Origin", DOUYIN_URL);
        headers.put("Referer", DOUYIN_URL);
        headers.put("Content-Type", "application/json;charset=UTF-8");
        // 添加其他必要头部
        headers.put("accept", "application/json, text/plain, */*");
        headers.put("accept-language", "zh-CN,zh;q=0.9");
        headers.put("sec-fetch-dest", "empty");
        headers.put("sec-fetch-mode", "cors");
        headers.put("sec-fetch-site", "same-site");
        return headers;
    }
    /**
     * UUID 模拟生成（抖音前端实现方式）
     */
    private static String generateUUIDPattern(Integer t) {
        if (t != null) {
            int randInt = (int) (16 * RANDOM.nextDouble());
            int shifted = randInt >> (t / 4);
            return String.valueOf(t ^ shifted);
        } else {
            return String.join("-",
                    String.valueOf((int) 1e7),
                    String.valueOf((int) 1e3),
                    String.valueOf((int) 4e3),
                    String.valueOf((int) 8e3),
                    String.valueOf((int) 1e11)
            );
        }
    }

    /**
     * 构造 webid 参数，模拟客户端生成规则
     */
    public static String getWebId() {
        String base = generateUUIDPattern(null);
        StringBuilder webId = new StringBuilder();

        for (char c : base.toCharArray()) {
            if (c == '0' || c == '1' || c == '8') {
                webId.append(generateUUIDPattern(Character.getNumericValue(c)));
            } else {
                webId.append(c);
            }
        }
        return webId.toString().replace("-", "").substring(0, 19);
    }

    private static Map<String, Object> buildBaseParams() {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("device_platform", "webapp");
        params.put("aid", AID);
        params.put("channel", "channel_pc_web");
        params.put("version_code", "190600");
        params.put("version_name", "19.6.0");
        params.put("update_version_code", "170400");
        params.put("pc_client_type", "1");
//        params.put("app_name", "douyin_web");
        params.put("cookie_enabled", "true");
        params.put("browser_language", "zh-CN");
        params.put("browser_platform", "MacIntel");
        params.put("browser_name", "Chrome");
        params.put("browser_version", "125.0.0.0");
        params.put("browser_online", "true");
        params.put("engine_name", "Blink");
        params.put("engine_version", "109.0");
        params.put("os_name", "Mac OS");
        params.put("os_version", "10.15.7");
        params.put("cpu_core_num", "8");
        params.put("device_memory", "8");
        params.put("platform", "PC");
        params.put("screen_width", "2560");
        params.put("screen_height", "1440");
        params.put("effective_type", "4g");
        params.put("round_trip_time", "50");
        return params;
    }

    public static Map<String, Object> getCommonParams(Map<String, String> param, String uri, String xmst, String userAgent) {
        Map<String, Object> params = buildBaseParams();
        params.put("webid", getWebId());
        params.put("msToken", xmst);

        if (param != null && !param.isEmpty()) {
            params.putAll(param);
        }
        String queryString = buildQueryString(params);
        log.debug("Query String: {}", queryString);
        String a_bogus = getABogusFromJS(uri, queryString, userAgent);
        params.put("a_bogus", a_bogus);
        return params;
    }


    /**
     * 根据 URL 自动选择签名函数并获取 a_bogus 签名
     */
    public static String getABogusFromJS(String url, String params, String userAgent) {
        String function = url.contains("/reply") ? "sign_reply" : "sign_datail";
        return executeJsFunction(function, params, userAgent);
    }

    private static String executeJsFunction(String functionName, Object... args) {
        try (Context context = Context.create("js")) {
            context.eval("js", JS_SDK);
            Value func = context.getBindings("js").getMember(functionName);
            if (func == null || !func.canExecute()) {
                throw new RuntimeException("JS function not found or not executable: " + functionName);
            }
            Value result = func.execute(args);
            return result.asString();
        } catch (Exception e) {
            log.error("Error executing JavaScript function: {}", functionName, e);
            return null;
        }
    }

    public static String buildQueryString(Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        StringJoiner joiner = new StringJoiner("&");
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            try {
                if (StringUtils.isNoneBlank(entry.getKey()) && entry.getValue()!=null) {
                    String encodedKey = URLEncoder.encode(entry.getKey(), "UTF-8");
                    String encodedValue = URLEncoder.encode(entry.getValue().toString(), "UTF-8");
                    joiner.add(encodedKey + "=" + encodedValue);
                }
            } catch (UnsupportedEncodingException e) {
                // UTF-8 是标准编码，正常不会抛异常
                throw new RuntimeException("URL encoding failed", e);
            }
        }
        return joiner.toString();
    }
}
