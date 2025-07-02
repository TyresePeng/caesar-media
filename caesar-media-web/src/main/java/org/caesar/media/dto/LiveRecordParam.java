package org.caesar.media.dto;

import lombok.Data;

/**
 * @Description: 直播录制入参数
 * @Author: peng.guo
 * @Create: 2025-07-02 16:11
 * @Version 1.0
 **/
@Data
public class LiveRecordParam {
    /**
     * 房间id
     */
    private String roomId;
    /**
     * 录制流地址
     */
    private String streamUrl;

    /**
     * 录制质量
     */
    private String quality;
}
