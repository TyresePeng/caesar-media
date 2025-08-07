package org.caesar.media.controller.multilive;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.caesar.media.common.ApiResponse;
import org.caesar.media.dto.MultiLiveRoomDto;
import org.caesar.media.dto.MultiLiveRoomQuery;
import org.caesar.media.dto.BatchOperationParam;
import org.caesar.media.service.MultiLiveRoomService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description: 多直播间管理控制器
 * @Author: peng.guo
 * @Create: 2025-01-06 16:30
 * @Version 1.0
 **/
@RestController
@RequestMapping("/multi-live-rooms")
@RequiredArgsConstructor
@Slf4j
@Validated
public class MultiLiveRoomController {

    private final MultiLiveRoomService multiLiveRoomService;

    /**
     * 获取用户的直播间列表
     *
     * @param query 查询参数
     * @return 直播间列表
     */
    @GetMapping("/list")
    public ApiResponse<List<MultiLiveRoomDto>> getRoomList(MultiLiveRoomQuery query) {
        List<MultiLiveRoomDto> rooms = multiLiveRoomService.getUserRooms(query);
        return ApiResponse.success(rooms);
    }

    /**
     * 添加直播间
     *
     * @param roomDto 直播间信息
     * @return 添加结果
     */
    @PostMapping("/add")
    public ApiResponse<MultiLiveRoomDto> addRoom(@Valid @RequestBody MultiLiveRoomDto roomDto) {
        MultiLiveRoomDto result = multiLiveRoomService.addRoom(roomDto);
        return ApiResponse.success(result);
    }

    /**
     * 更新直播间信息
     *
     * @param roomId 房间ID
     * @param roomDto 更新数据
     * @return 更新结果
     */
    @PutMapping("/{roomId}")
    public ApiResponse<MultiLiveRoomDto> updateRoom(@NotNull @PathVariable String roomId,
                                                    @Valid @RequestBody MultiLiveRoomDto roomDto) {
        MultiLiveRoomDto result = multiLiveRoomService.updateRoom(roomId, roomDto);
        return ApiResponse.success(result);
    }

    /**
     * 删除直播间
     *
     * @param roomId 房间ID
     * @return 删除结果
     */
    @DeleteMapping("/{roomId}")
    public ApiResponse<Void> deleteRoom(@NotNull @PathVariable String roomId) {
        multiLiveRoomService.deleteRoom(roomId);
        return ApiResponse.success();
    }

    /**
     * 获取直播间详细信息
     *
     * @param roomId 房间ID
     * @return 房间详情
     */
    @GetMapping("/{roomId}")
    public ApiResponse<MultiLiveRoomDto> getRoomDetail(@NotNull @PathVariable String roomId) {
        MultiLiveRoomDto room = multiLiveRoomService.getRoomDetail(roomId);
        return ApiResponse.success(room);
    }

    /**
     * 批量开启监听
     *
     * @param param 批量操作参数
     * @return 操作结果
     */
    @PostMapping("/batch-start-monitor")
    public ApiResponse<Void> batchStartMonitor(@Valid @RequestBody BatchOperationParam param) {
        multiLiveRoomService.batchStartMonitor(param.getRoomIds());
        return ApiResponse.success();
    }

    /**
     * 批量停止监听
     *
     * @param param 批量操作参数
     * @return 操作结果
     */
    @PostMapping("/batch-stop-monitor")
    public ApiResponse<Void> batchStopMonitor(@Valid @RequestBody BatchOperationParam param) {
        multiLiveRoomService.batchStopMonitor(param.getRoomIds());
        return ApiResponse.success();
    }

    /**
     * 获取房间监听状态
     *
     * @param roomIds 房间ID列表
     * @return 状态列表
     */
    @GetMapping("/monitor-status")
    public ApiResponse<List<MultiLiveRoomDto>> getMonitorStatus(@RequestParam List<String> roomIds) {
        List<MultiLiveRoomDto> status = multiLiveRoomService.getMonitorStatus(roomIds);
        return ApiResponse.success(status);
    }

    /**
     * 更新房间最后活跃时间
     *
     * @param roomId 房间ID
     * @return 更新结果
     */
    @PostMapping("/{roomId}/update-active-time")
    public ApiResponse<Void> updateActiveTime(@NotNull @PathVariable String roomId) {
        multiLiveRoomService.updateActiveTime(roomId);
        return ApiResponse.success();
    }

    /**
     * 清理不活跃的房间
     *
     * @return 清理结果
     */
    @DeleteMapping("/cleanup-inactive")
    public ApiResponse<Integer> cleanupInactiveRooms() {
        int cleaned = multiLiveRoomService.cleanupInactiveRooms();
        return ApiResponse.success(cleaned);
    }
}