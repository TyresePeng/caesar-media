package org.caesar.media.dto;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * AI弹幕启动参数
 * 
 * @author peng.guo
 */
@Data
public class AiDanmuStartParam {
    
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    
    /**
     * 直播间ID
     */
    @NotNull(message = "直播间ID不能为空")
    private Long roomId;
    
    /**
     * 直播间描述
     */
    @NotBlank(message = "直播间描述不能为空")
    @Size(max = 500, message = "直播间描述不能超过500字符")
    private String roomDescription;
    
    /**
     * 随机等待秒数
     */
    @Min(value = 1, message = "随机等待时间不能少于1秒")
    @Max(value = 10, message = "随机等待时间不能超过10秒")
    private Integer randomSeconds = 2;
    
    /**
     * AI人格设定
     */
    @Size(max = 200, message = "AI人格设定不能超过200字符")
    private String aiPersonality = "热情的观众";
}