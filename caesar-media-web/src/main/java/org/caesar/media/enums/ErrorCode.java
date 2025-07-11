package org.caesar.media.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 错误码枚举
 * 
 * @author peng.guo
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    
    // 系统错误 1000x
    SYSTEM_ERROR("10000", "系统异常"),
    INVALID_PARAMETERS("10001", "参数错误"),
    RESOURCE_NOT_FOUND("10002", "资源不存在"),
    
    // 直播间相关错误 1100x
    LIVE_ROOM_NOT_FOUND("11001", "直播间不存在"),
    LIVE_ROOM_OFFLINE("11002", "直播间已下线"),
    LIVE_ROOM_CONNECT_FAILED("11003", "连接直播间失败"),
    LIVE_ROOM_SEND_MSG_FAILED("11004", "发送消息失败"),
    
    // 录制相关错误 1200x
    RECORDING_ALREADY_STARTED("12001", "录制已在进行中"),
    RECORDING_START_FAILED("12002", "启动录制失败"),
    RECORDING_STOP_FAILED("12003", "停止录制失败"),
    RECORDING_FILE_NOT_FOUND("12004", "录制文件不存在"),
    RECORDING_MAX_CONCURRENT_REACHED("12005", "已达到最大并发录制数"),
    FFMPEG_NOT_FOUND("12006", "FFmpeg未找到"),
    
    // 用户相关错误 1300x
    USER_NOT_FOUND("13001", "用户不存在"),
    USER_SESSION_INVALID("13002", "用户会话无效"),
    USER_PERMISSION_DENIED("13003", "用户权限不足"),
    
    // 平台相关错误 1400x
    PLATFORM_USER_NOT_FOUND("14001", "平台用户不存在"),
    PLATFORM_SESSION_EXPIRED("14002", "平台会话已过期"),
    PLAYWRIGHT_INIT_FAILED("14003", "浏览器初始化失败");
    
    private final String code;
    private final String message;
}