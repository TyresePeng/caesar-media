package org.caesar.media.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.caesar.media.entity.MultiLiveRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description: 多直播间Mapper
 * @Author: peng.guo
 * @Create: 2025-01-06 16:45
 * @Version 1.0
 **/
@Mapper
public interface MultiLiveRoomMapper extends BaseMapper<MultiLiveRoom> {

    /**
     * 批量更新监听状态
     *
     * @param roomIds 房间ID列表
     * @param isMonitoring 监听状态
     * @param userId 用户ID
     * @return 更新数量
     */
    int batchUpdateMonitoringStatus(@Param("roomIds") List<String> roomIds, 
                                   @Param("isMonitoring") Boolean isMonitoring,
                                   @Param("userId") Long userId);

    /**
     * 更新最后活跃时间
     *
     * @param roomId 房间ID
     * @param userId 用户ID
     * @param lastActiveTime 最后活跃时间
     * @return 更新数量
     */
    int updateLastActiveTime(@Param("roomId") String roomId, 
                            @Param("userId") Long userId, 
                            @Param("lastActiveTime") LocalDateTime lastActiveTime);

    /**
     * 清理不活跃的房间
     *
     * @param beforeTime 时间阈值
     * @param userId 用户ID
     * @return 清理数量
     */
    int cleanupInactiveRooms(@Param("beforeTime") LocalDateTime beforeTime, 
                            @Param("userId") Long userId);

    /**
     * 获取用户房间列表
     *
     * @param userId 用户ID
     * @param keyword 关键字
     * @param isMonitoring 监听状态
     * @param isLoaded 加载状态
     * @return 房间列表
     */
    List<MultiLiveRoom> getUserRooms(@Param("userId") Long userId,
                                    @Param("keyword") String keyword,
                                    @Param("isMonitoring") Boolean isMonitoring,
                                    @Param("isLoaded") Boolean isLoaded);
}