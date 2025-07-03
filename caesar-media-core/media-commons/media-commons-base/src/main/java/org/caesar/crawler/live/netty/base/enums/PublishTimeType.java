package org.caesar.crawler.live.netty.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author peng.guo
 */
@Getter
@AllArgsConstructor
public enum PublishTimeType {
    // 不限
    UNLIMITED("0"),
    // 一天内
    ONE_DAY("1"),
    // 一周内
    ONE_WEEK("7"),
    // 半年内
    SIX_MONTH("180");

    private final String value;


    public static PublishTimeType fromValue(String value) {
        if (value == null) {
            return UNLIMITED;
        }
        for (PublishTimeType type : PublishTimeType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return UNLIMITED;
    }
}
