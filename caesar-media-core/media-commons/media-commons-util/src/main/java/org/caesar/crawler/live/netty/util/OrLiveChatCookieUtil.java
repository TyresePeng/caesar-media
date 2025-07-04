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

package org.caesar.crawler.live.netty.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author mjz
 * @date 2023/8/27
 */
public class OrLiveChatCookieUtil {

    public static String toString(List<HttpCookie> cookies) {
        if (CollUtil.isEmpty(cookies)) {
            return StrUtil.EMPTY;
        }

        return cookies.stream().map(httpCookie -> {
            httpCookie.setVersion(0);
            return httpCookie.toString();
        }).collect(Collectors.joining("; "));
    }

    public static Map<String, String> parseCookieString(String cookies) {
        Map<String, String> map = new HashMap<>();
        if (StrUtil.isNotBlank(cookies) && !StrUtil.isNullOrUndefined(cookies)) {
            try {
                String[] split = cookies.split("; ");
                for (String s : split) {
                    String key = StrUtil.subBefore(s, '=', false);
                    String value = StrUtil.subAfter(s, '=', false);
                    map.put(key, value);
                }
            } catch (Exception e) {
                throw new RuntimeException("cookie解析失败 " + cookies, e);
            }
        }
        return map;
    }

    public static String toCookieString(Map<String, String> cookies) {
        if (cookies.isEmpty()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> stringStringEntry : cookies.entrySet()) {
            String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            sb.append(key)
                    .append("=")
                    .append(value)
                    .append("; ");
        }
        int length = sb.length();
        sb.delete(length - 2, length);
        return sb.toString();
    }


    public static String getCookieByName(Map<String, String> cookieMap, String name, Supplier<String> supplier) {
        String str = MapUtil.getStr(cookieMap, name);
        if (StrUtil.isNotBlank(str)) {
            return str;
        } else {
            return supplier.get();
        }
    }

    public static String getCookieByName(String cookie, String name, Supplier<String> supplier) {
        String str = MapUtil.getStr(parseCookieString(cookie), name);
        if (StrUtil.isNotBlank(str)) {
            return str;
        } else {
            return supplier.get();
        }
    }
}
