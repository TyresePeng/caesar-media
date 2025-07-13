package org.caesar.media.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * AI弹幕运行状态枚举
 * 
 * @author peng.guo
 */
@Getter
@RequiredArgsConstructor
public enum AiDanmuStatus {
    
    /**
     * 已停止
     */
    STOPPED("STOPPED", "已停止"),
    
    /**
     * 运行中
     */
    RUNNING("RUNNING", "运行中"),
    
    /**
     * 启动中
     */
    STARTING("STARTING", "启动中"),
    
    /**
     * 错误状态
     */
    ERROR("ERROR", "错误状态");
    
    private final String code;
    private final String message;
}