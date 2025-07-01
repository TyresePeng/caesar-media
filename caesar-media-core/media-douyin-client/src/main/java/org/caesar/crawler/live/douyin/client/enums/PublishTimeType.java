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
        log.error("Unknown value: " + value);
        return UNLIMITED;
    }
}
