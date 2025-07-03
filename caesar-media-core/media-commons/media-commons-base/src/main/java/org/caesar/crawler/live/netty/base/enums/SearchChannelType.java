package org.caesar.crawler.live.netty.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author peng.guo
 */
@Getter
@AllArgsConstructor
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
        return GENERAL;

    }
}
