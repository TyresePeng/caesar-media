package org.caesar.media.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Description: 多直播间数据传输对象
 * @Author: peng.guo
 * @Create: 2025-01-06 16:35
 * @Version 1.0
 **/
@Data
public class MultiLiveRoomDto {

    /**
     * 房间唯一标识
     */
    private String roomId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 房间输入（链接或ID）
     */
    @NotBlank(message = "直播间地址不能为空")
    private String roomInput;

    /**
     * 显示名称
     */
    @Size(max = 50, message = "显示名称不能超过50个字符")
    private String displayName;

    /**
     * 房间标题
     */
    private String roomTitle;

    /**
     * 房间状态
     */
    private String roomStatus;

    /**
     * 是否已加载
     */
    private Boolean isLoaded = false;

    /**
     * 是否正在监听
     */
    private Boolean isMonitoring = false;

    /**
     * 未读消息数
     */
    private Integer unreadCount = 0;

    /**
     * 流地址映射
     */
    private Map<String, String> streamUrls;

    /**
     * 录制状态映射
     */
    private Map<String, Boolean> recordingStatus;

    /**
     * AI弹幕是否启用
     */
    private Boolean aiDanmuEnabled = false;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后活跃时间
     */
    private LocalDateTime lastActiveTime;

    /**
     * 排序权重
     */
    private Integer sortOrder = 0;

    /**
     * 备注
     */
    @Size(max = 200, message = "备注不能超过200个字符")
    private String remark;
}