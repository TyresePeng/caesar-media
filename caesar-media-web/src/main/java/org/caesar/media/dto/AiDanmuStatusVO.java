package org.caesar.media.dto;

import lombok.Data;
import org.caesar.media.enums.AiDanmuStatus;

import java.time.LocalDateTime;

/**
 * AI弹幕状态VO
 * 
 * @author peng.guo
 */
@Data
public class AiDanmuStatusVO {
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 用户昵称
     */
    private String nickname;
    
    /**
     * 用户头像
     */
    private String avatar;
    
    /**
     * 是否在线
     */
    private Boolean online;
    
    /**
     * AI弹幕状态
     */
    private AiDanmuStatus status;
    
    /**
     * 当前直播间ID
     */
    private Long roomId;
    
    /**
     * 直播间描述
     */
    private String roomDescription;
    
    /**
     * 随机等待秒数
     */
    private Integer randomSeconds;
    
    /**
     * AI人格设定
     */
    private String aiPersonality;
    
    /**
     * 已发送弹幕数量
     */
    private Long sentCount;
    
    /**
     * 最后发送时间
     */
    private LocalDateTime lastSentTime;
    
    /**
     * 最后发送内容
     */
    private String lastSentContent;
    
    /**
     * 启动时间
     */
    private LocalDateTime startTime;
    
    /**
     * 运行时长（分钟）
     */
    private Long runDurationMinutes;
    
    /**
     * 错误消息
     */
    private String errorMessage;
}