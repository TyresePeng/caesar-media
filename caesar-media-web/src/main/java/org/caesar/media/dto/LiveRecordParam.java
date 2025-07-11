package org.caesar.media.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
    @NotBlank(message = "房间ID不能为空")
    private String roomId;
    
    /**
     * 录制流地址
     */
    @NotBlank(message = "录制流地址不能为空")
    @Pattern(regexp = "^(http|https|rtmp)://.*", message = "录制流地址格式不正确")
    private String streamUrl;

    /**
     * 录制质量
     */
    @NotBlank(message = "录制质量不能为空")
    @Pattern(regexp = "^(HD|SD|UHD)\\d*$", message = "录制质量格式不正确")
    private String quality;
}
