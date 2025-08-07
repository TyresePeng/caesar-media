package org.caesar.media.service;

import org.caesar.media.dto.MultiLiveRoomDto;
import org.caesar.media.dto.MultiLiveRoomQuery;

import java.util.List;

/**
 * @Description: 多直播间服务接口
 * @Author: peng.guo
 * @Create: 2025-01-06 16:48
 * @Version 1.0
 **/
public interface MultiLiveRoomService {

    /**
     * 获取用户的直播间列表
     *
     * @param query 查询参数
     * @return 直播间列表
     */
    List<MultiLiveRoomDto> getUserRooms(MultiLiveRoomQuery query);

    /**
     * 添加直播间
     *
     * @param roomDto 直播间信息
     * @return 添加结果
     */
    MultiLiveRoomDto addRoom(MultiLiveRoomDto roomDto);

    /**
     * 更新直播间信息
     *
     * @param roomId 房间ID
     * @param roomDto 更新数据
     * @return 更新结果
     */
    MultiLiveRoomDto updateRoom(String roomId, MultiLiveRoomDto roomDto);

    /**
     * 删除直播间
     *
     * @param roomId 房间ID
     */
    void deleteRoom(String roomId);

    /**
     * 获取直播间详细信息
     *
     * @param roomId 房间ID
     * @return 房间详情
     */
    MultiLiveRoomDto getRoomDetail(String roomId);

    /**
     * 批量开启监听
     *
     * @param roomIds 房间ID列表
     */
    void batchStartMonitor(List<String> roomIds);

    /**
     * 批量停止监听
     *
     * @param roomIds 房间ID列表
     */
    void batchStopMonitor(List<String> roomIds);

    /**
     * 获取房间监听状态
     *
     * @param roomIds 房间ID列表
     * @return 状态列表
     */
    List<MultiLiveRoomDto> getMonitorStatus(List<String> roomIds);

    /**
     * 更新房间最后活跃时间
     *
     * @param roomId 房间ID
     */
    void updateActiveTime(String roomId);

    /**
     * 清理不活跃的房间
     *
     * @return 清理数量
     */
    int cleanupInactiveRooms();
}