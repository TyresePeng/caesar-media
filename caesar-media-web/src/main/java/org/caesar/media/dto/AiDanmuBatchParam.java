package org.caesar.media.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * AI弹幕批量操作参数
 * 
 * @author peng.guo
 */
@Data
public class AiDanmuBatchParam {
    
    /**
     * 用户ID列表
     */
    @NotEmpty(message = "用户ID列表不能为空")
    private List<Long> userIds;
    
    /**
     * 直播间ID（批量启动时需要）
     */
    private Long roomId;
    
    /**
     * 直播间描述（批量启动时需要）
     */
    private String roomDescription;
    
    /**
     * 随机等待秒数（批量启动时需要）
     */
    private Integer randomSeconds = 2;
    
    /**
     * AI人格设定（批量启动时需要）
     */
    private String aiPersonality = "热情的观众";
}