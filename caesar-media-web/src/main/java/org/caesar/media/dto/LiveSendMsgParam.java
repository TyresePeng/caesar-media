package org.caesar.media.dto;

import lombok.Data;

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
   private Long roomId;
   /**
     * 消息
     */
    private String msg;

    /**
     * 指定用户id
     */
    private Long userId;
}
