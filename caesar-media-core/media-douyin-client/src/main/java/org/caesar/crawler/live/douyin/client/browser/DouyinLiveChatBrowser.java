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

package org.caesar.crawler.live.douyin.client.browser;

import cn.hutool.core.io.IoUtil;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import lombok.extern.slf4j.Slf4j;
import org.caesar.media.browser.factory.PlaywrightFactory;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mjz
 * @date 2024/1/2
 */
@Slf4j
public class DouyinLiveChatBrowser {

    public static String STEALTH_SDK;

    static {
        InputStream resourceAsStreamStealth = DouyinLiveChatBrowser.class.getResourceAsStream("/js/stealth.min.js");
        STEALTH_SDK = IoUtil.readUtf8(resourceAsStreamStealth);
    }

    // 缓存 roomId -> Page 映射
    private static final Map<Long, Page> roomPageCache = new ConcurrentHashMap<>();

    public static boolean sendMsg(PlaywrightFactory playwrightFactory, Long roomId, String msg) {
        return playwrightFactory.doWithContext(ctx -> {
            try {
                ctx.addInitScript(STEALTH_SDK);
                Page page = roomPageCache.get(roomId);
                String targetUrl = "https://live.douyin.com/" + roomId;

                // 检查 Page 是否有效
                if (page == null || page.isClosed()) {
                    page = ctx.newPage();
                    page.navigate(targetUrl);
                    page.waitForLoadState(LoadState.DOMCONTENTLOADED);
                    roomPageCache.put(roomId, page);
                    log.info("新打开直播间页面: {}", targetUrl);
                } else {
                    log.info("复用已缓存直播间页面: {}", page.url());
                    // 若不在目标页面，重新导航
                    if (!page.url().contains(String.valueOf(roomId))) {
                        page.navigate(targetUrl);
                        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
                        log.info("页面导航至目标直播间: {}", targetUrl);
                    }
                }
                // 填充消息并发送
                Locator chatInput = page.locator("#chatInput div").nth(3);
                chatInput.waitFor(new Locator.WaitForOptions().setTimeout(20_000));
                chatInput.fill(msg);
                log.info("填充聊天内容成功: {}", msg);

                Locator sendButton = page.locator("#chatInput").getByRole(AriaRole.IMG).nth(1);
                sendButton.waitFor(new Locator.WaitForOptions().setTimeout(10_000));
                sendButton.click();
                log.info("发送按钮点击成功");
                return true;

            } catch (Exception e) {
                log.error("发送直播间消息失败: {}", e.getMessage(), e);
                return false;
            }
        });
    }
}
