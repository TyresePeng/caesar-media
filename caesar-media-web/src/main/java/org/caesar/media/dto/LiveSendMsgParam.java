package org.caesar.media.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description: 发送消息入参
 * @Author: peng.guo
 * @Create: 2025-07-08 18:46
 * @Version 1.0
 **/
@Data
public class LiveSendMsgParam {
    /**
     * 直播间id
     */
    @NotNull(message = "直播间ID不能为空")
    private Long roomId;

    /**
     * 消息
     */
    @NotBlank(message = "消息内容不能为空")
    private String msg;

    /**
     * 指定用户id
     */
    private Long userId;
}
