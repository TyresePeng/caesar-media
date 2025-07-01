package org.caesar.media.browser.function;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

@FunctionalInterface
public interface ContextPageConsumer {
    void accept(BrowserContext context, Page page) throws Exception;
}
