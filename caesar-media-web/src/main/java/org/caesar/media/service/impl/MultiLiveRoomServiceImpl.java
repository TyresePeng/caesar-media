package org.caesar.media.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.caesar.media.dto.MultiLiveRoomDto;
import org.caesar.media.dto.MultiLiveRoomQuery;
import org.caesar.media.entity.MultiLiveRoom;
import org.caesar.media.mapper.MultiLiveRoomMapper;
import org.caesar.media.service.DouyinService;
import org.caesar.media.service.MultiLiveRoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 多直播间服务实现
 * @Author: peng.guo
 * @Create: 2025-01-06 16:52
 * @Version 1.0
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class MultiLiveRoomServiceImpl implements MultiLiveRoomService {

    private final MultiLiveRoomMapper multiLiveRoomMapper;
    private final DouyinService douyinService;

    @Override
    public List<MultiLiveRoomDto> getUserRooms(MultiLiveRoomQuery query) {
        // 设置默认用户ID（这里可以从SecurityContext获取）
        if (query.getUserId() == null) {
            query.setUserId(1L); // 临时使用固定用户ID
        }

        List<MultiLiveRoom> rooms = multiLiveRoomMapper.getUserRooms(
                query.getUserId(),
                query.getKeyword(),
                query.getIsMonitoring(),
                query.getIsLoaded()
        );

        return rooms.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MultiLiveRoomDto addRoom(MultiLiveRoomDto roomDto) {
        // 设置默认用户ID
        if (roomDto.getUserId() == null) {
            roomDto.setUserId(1L);
        }

        // 检查是否已存在
        LambdaQueryWrapper<MultiLiveRoom> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MultiLiveRoom::getUserId, roomDto.getUserId())
               .eq(MultiLiveRoom::getRoomInput, roomDto.getRoomInput());
        
        if (multiLiveRoomMapper.selectCount(wrapper) > 0) {
            throw new RuntimeException("该直播间已存在");
        }

        // 转换为实体
        MultiLiveRoom room = convertToEntity(roomDto);
        room.setCreateTime(LocalDateTime.now());
        room.setLastActiveTime(LocalDateTime.now());
        room.setIsLoaded(false);
        room.setIsMonitoring(false);
        room.setUnreadCount(0);
        room.setAiDanmuEnabled(false);
        room.setSortOrder(0);

        // 如果没有设置显示名称，使用房间输入作为名称
        if (StrUtil.isBlank(room.getDisplayName())) {
            room.setDisplayName(extractRoomId(roomDto.getRoomInput()));
        }

        multiLiveRoomMapper.insert(room);
        
        return convertToDto(room);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MultiLiveRoomDto updateRoom(String roomId, MultiLiveRoomDto roomDto) {
        MultiLiveRoom existingRoom = multiLiveRoomMapper.selectById(roomId);
        if (existingRoom == null) {
            throw new RuntimeException("直播间不存在");
        }

        // 更新字段
        MultiLiveRoom updateRoom = new MultiLiveRoom();
        updateRoom.setId(roomId);
        updateRoom.setDisplayName(roomDto.getDisplayName());
        updateRoom.setRoomTitle(roomDto.getRoomTitle());
        updateRoom.setRoomStatus(roomDto.getRoomStatus());
        updateRoom.setIsLoaded(roomDto.getIsLoaded());
        updateRoom.setIsMonitoring(roomDto.getIsMonitoring());
        updateRoom.setUnreadCount(roomDto.getUnreadCount());
        updateRoom.setAiDanmuEnabled(roomDto.getAiDanmuEnabled());
        updateRoom.setRemark(roomDto.getRemark());
        updateRoom.setLastActiveTime(LocalDateTime.now());

        // 处理JSON字段
        if (roomDto.getStreamUrls() != null) {
            updateRoom.setStreamUrls(JSONUtil.toJsonStr(roomDto.getStreamUrls()));
        }
        if (roomDto.getRecordingStatus() != null) {
            updateRoom.setRecordingStatus(JSONUtil.toJsonStr(roomDto.getRecordingStatus()));
        }

        multiLiveRoomMapper.updateById(updateRoom);
        
        return getRoomDetail(roomId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoom(String roomId) {
        MultiLiveRoom room = multiLiveRoomMapper.selectById(roomId);
        if (room == null) {
            throw new RuntimeException("直播间不存在");
        }

        // 如果正在监听，先停止监听
        if (Boolean.TRUE.equals(room.getIsMonitoring())) {
            try {
                Long roomIdLong = Long.valueOf(extractRoomId(room.getRoomInput()));
                douyinService.disconnectRoom(roomIdLong);
            } catch (Exception e) {
                log.warn("停止监听失败，但继续删除房间: {}", e.getMessage());
            }
        }

        multiLiveRoomMapper.deleteById(roomId);
    }

    @Override
    public MultiLiveRoomDto getRoomDetail(String roomId) {
        MultiLiveRoom room = multiLiveRoomMapper.selectById(roomId);
        if (room == null) {
            throw new RuntimeException("直播间不存在");
        }
        return convertToDto(room);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchStartMonitor(List<String> roomIds) {
        if (CollUtil.isEmpty(roomIds)) {
            return;
        }

        // 获取房间列表
        List<MultiLiveRoom> rooms = multiLiveRoomMapper.selectBatchIds(roomIds);
        
        for (MultiLiveRoom room : rooms) {
            if (Boolean.TRUE.equals(room.getIsLoaded()) && !Boolean.TRUE.equals(room.getIsMonitoring())) {
                try {
                    Long roomIdLong = Long.valueOf(extractRoomId(room.getRoomInput()));
                    douyinService.connectRoom(roomIdLong);
                    
                    // 更新状态
                    LambdaUpdateWrapper<MultiLiveRoom> wrapper = new LambdaUpdateWrapper<>();
                    wrapper.eq(MultiLiveRoom::getId, room.getId())
                           .set(MultiLiveRoom::getIsMonitoring, true)
                           .set(MultiLiveRoom::getLastActiveTime, LocalDateTime.now());
                    multiLiveRoomMapper.update(null, wrapper);
                    
                } catch (Exception e) {
                    log.error("启动监听失败，房间ID: {}, 错误: {}", room.getId(), e.getMessage());
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchStopMonitor(List<String> roomIds) {
        if (CollUtil.isEmpty(roomIds)) {
            return;
        }

        // 获取房间列表
        List<MultiLiveRoom> rooms = multiLiveRoomMapper.selectBatchIds(roomIds);
        
        for (MultiLiveRoom room : rooms) {
            if (Boolean.TRUE.equals(room.getIsMonitoring())) {
                try {
                    Long roomIdLong = Long.valueOf(extractRoomId(room.getRoomInput()));
                    douyinService.disconnectRoom(roomIdLong);
                    
                    // 更新状态
                    LambdaUpdateWrapper<MultiLiveRoom> wrapper = new LambdaUpdateWrapper<>();
                    wrapper.eq(MultiLiveRoom::getId, room.getId())
                           .set(MultiLiveRoom::getIsMonitoring, false)
                           .set(MultiLiveRoom::getLastActiveTime, LocalDateTime.now());
                    multiLiveRoomMapper.update(null, wrapper);
                    
                } catch (Exception e) {
                    log.error("停止监听失败，房间ID: {}, 错误: {}", room.getId(), e.getMessage());
                }
            }
        }
    }

    @Override
    public List<MultiLiveRoomDto> getMonitorStatus(List<String> roomIds) {
        if (CollUtil.isEmpty(roomIds)) {
            return CollUtil.newArrayList();
        }

        List<MultiLiveRoom> rooms = multiLiveRoomMapper.selectBatchIds(roomIds);
        return rooms.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateActiveTime(String roomId) {
        LambdaUpdateWrapper<MultiLiveRoom> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(MultiLiveRoom::getId, roomId)
               .set(MultiLiveRoom::getLastActiveTime, LocalDateTime.now());
        multiLiveRoomMapper.update(null, wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int cleanupInactiveRooms() {
        // 清理7天未活跃的房间
        LocalDateTime beforeTime = LocalDateTime.now().minusDays(7);
        return multiLiveRoomMapper.cleanupInactiveRooms(beforeTime, 1L);
    }

    /**
     * 实体转DTO
     */
    private MultiLiveRoomDto convertToDto(MultiLiveRoom room) {
        MultiLiveRoomDto dto = BeanUtil.copyProperties(room, MultiLiveRoomDto.class);
        
        // 处理JSON字段
        if (StrUtil.isNotBlank(room.getStreamUrls())) {
            try {
                dto.setStreamUrls(JSONUtil.toBean(room.getStreamUrls(), Map.class));
            } catch (Exception e) {
                log.warn("解析streamUrls失败: {}", e.getMessage());
            }
        }
        
        if (StrUtil.isNotBlank(room.getRecordingStatus())) {
            try {
                dto.setRecordingStatus(JSONUtil.toBean(room.getRecordingStatus(), Map.class));
            } catch (Exception e) {
                log.warn("解析recordingStatus失败: {}", e.getMessage());
            }
        }
        
        return dto;
    }

    /**
     * DTO转实体
     */
    private MultiLiveRoom convertToEntity(MultiLiveRoomDto dto) {
        MultiLiveRoom room = BeanUtil.copyProperties(dto, MultiLiveRoom.class);
        
        // 处理JSON字段
        if (dto.getStreamUrls() != null) {
            room.setStreamUrls(JSONUtil.toJsonStr(dto.getStreamUrls()));
        }
        if (dto.getRecordingStatus() != null) {
            room.setRecordingStatus(JSONUtil.toJsonStr(dto.getRecordingStatus()));
        }
        
        return room;
    }

    /**
     * 从直播间输入提取房间ID
     */
    private String extractRoomId(String roomInput) {
        if (StrUtil.isBlank(roomInput)) {
            return roomInput;
        }
        
        // 如果是纯数字，直接返回
        if (roomInput.matches("^\\d+$")) {
            return roomInput;
        }
        
        // 从抖音链接中提取房间ID
        if (roomInput.contains("live.douyin.com/")) {
            String[] parts = roomInput.split("/");
            for (String part : parts) {
                if (part.matches("^\\d+$")) {
                    return part;
                }
            }
        }
        
        return roomInput;
    }
}