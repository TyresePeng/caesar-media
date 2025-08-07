package org.caesar.media.dto;

import lombok.Data;

/**
 * @Description: 多直播间查询参数
 * @Author: peng.guo
 * @Create: 2025-01-06 16:38
 * @Version 1.0
 **/
@Data
public class MultiLiveRoomQuery {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 搜索关键字
     */
    private String keyword;

    /**
     * 是否正在监听
     */
    private Boolean isMonitoring;

    /**
     * 是否已加载
     */
    private Boolean isLoaded;

    /**
     * 排序字段 (createTime, lastActiveTime, displayName)
     */
    private String sortBy = "lastActiveTime";

    /**
     * 排序方向 (ASC, DESC)
     */
    private String sortDirection = "DESC";

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 页大小
     */
    private Integer pageSize = 50;
}