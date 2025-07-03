package org.caesar.crawler.live.netty.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author peng.guo
 */
@Getter
@AllArgsConstructor
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
        return GENERAL;
    }
}
