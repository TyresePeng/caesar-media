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
public enum SearchChannelType {
    // 综合
    GENERAL("aweme_general"),
    // 视频
    VIDEO("aweme_video_web"),
    // 用户
    USER("aweme_user_web"),
    // 直播
    LIVE("aweme_live");

    private final String value;


    /***
     * 根据 value 反查枚举类型
     */
    public static SearchChannelType fromValue(String value) {
        if (value == null){
            return GENERAL;
        }
        for (SearchChannelType type : SearchChannelType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        log.error("Unknown value:{}",value);
        return GENERAL;

    }
}
