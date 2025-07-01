package org.caesar.crawler.live.douyin.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

/**
 * @author peng.guo
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum SearchSortType {
    // 综合排序
    GENERAL("0"),
    // 最多点赞
    MOST_LIKE("1"),
    // 最新发布
    LATEST("2");

    private final String value;
    public static SearchSortType fromValue(String value) {
        if (value == null){
            return GENERAL;
        }
        for (SearchSortType type : SearchSortType.values()) {
            if (type.value.equals(value) ) {
                return type;
            }
        }
        log.error("Unknown value: " + value);
        return GENERAL;
    }
}
