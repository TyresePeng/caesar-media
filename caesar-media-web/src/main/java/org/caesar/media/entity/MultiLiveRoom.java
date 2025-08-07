package org.caesar.media.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description: 多直播间管理实体
 * @Author: peng.guo
 * @Create: 2025-01-06 16:42
 * @Version 1.0
 **/
@Data
@TableName("multi_live_room")
public class MultiLiveRoom {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 房间输入（链接或ID）
     */
    private String roomInput;

    /**
     * 显示名称
     */
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
    private Boolean isLoaded;

    /**
     * 是否正在监听
     */
    private Boolean isMonitoring;

    /**
     * 未读消息数
     */
    private Integer unreadCount;

    /**
     * 流地址映射(JSON格式存储)
     */
    private String streamUrls;

    /**
     * 录制状态映射(JSON格式存储)
     */
    private String recordingStatus;

    /**
     * AI弹幕是否启用
     */
    private Boolean aiDanmuEnabled;

    /**
     * 排序权重
     */
    private Integer sortOrder;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 最后活跃时间
     */
    private LocalDateTime lastActiveTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Boolean deleted;
}