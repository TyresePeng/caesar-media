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
        params.put("aid", AID);
        params.put("app_name", "douyin_web");
        params.put("device_platform", "web");
        params.put("cookie_enabled", "true");
        params.put("browser_language", "zh-CN");
        params.put("browser_platform", "MacIntel");
        params.put("browser_name", "Chrome");
        params.put("browser_version", "136.0.0.0");
        params.put("screen_width", "1440");
        params.put("screen_height", "900");
        return params;
    }

    public static Map<String, Object> getCommonParams(Map<String, String> param, String uri, String xmst, String userAgent) {
        Map<String, Object> params = buildBaseParams();
        params.put("language", "zh-CN");
        params.put("enter_from", "link_share");
        params.put("is_need_double_stream", "false");
        params.put("webid", getWebId());
        params.put("msToken", xmst);

        if (param != null && !param.isEmpty()) {
            params.putAll(param);
        }
        String queryString = buildQueryString(params);
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
