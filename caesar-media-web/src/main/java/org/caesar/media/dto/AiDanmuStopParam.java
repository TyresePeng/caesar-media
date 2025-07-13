package org.caesar.media.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * AI弹幕停止参数
 * 
 * @author peng.guo
 */
@Data
public class AiDanmuStopParam {
    
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;
}