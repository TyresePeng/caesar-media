package org.caesar.media.controller.douyin;

import cn.hutool.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.caesar.crawler.live.douyin.codec.api.DouyinApis;
import org.caesar.crawler.live.douyin.codec.room.DouyinRoomInitResult;
import org.caesar.crawler.live.netty.base.enums.PublishTimeType;
import org.caesar.crawler.live.netty.base.enums.SearchChannelType;
import org.caesar.crawler.live.netty.base.enums.SearchSortType;
import org.caesar.media.common.ApiResponse;
import org.caesar.media.dto.LiveRecordParam;
import org.caesar.media.dto.LiveSendMsgParam;
import org.caesar.media.service.DouyinService;
import org.caesar.media.service.LiveRecordService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @Description: 抖音控制器
 * @Author: peng.guo
 * @Create: 2025-05-27 15:50
 * @Version 1.0
 **/
@RestController
@RequestMapping("/douyin")
@RequiredArgsConstructor
@Slf4j
@Validated
public class DouyinController {

    private final DouyinService douyinService;
    private final LiveRecordService liveRecordService;

    /**
     * 查询关键词
     *
     * @param keyword           关键词
     * @param offset            偏移量
     * @param count             数量
     * @param publishTimeType   发布时间类型
     * @param searchChannelType 搜索频道类型
     * @param searchSortType    搜索排序类型
     * @return 查询结果
     */
    @GetMapping("query-key-word")
    public ApiResponse<JSONObject> queryKeyWord(@NotBlank(message = "关键词不能为空") String keyword,
                                                int offset,
                                                int count,
                                                PublishTimeType publishTimeType,
                                                SearchChannelType searchChannelType,
                                                SearchSortType searchSortType) {
        return ApiResponse.success(douyinService.queryKeyWord(keyword,
                offset,
                count,
                publishTimeType,
                searchChannelType,
                searchSortType
        ));
    }

    /**
     * 根据直播间id查询直播间信息
     *
     * @param roomId 房间号
     * @return 查询结果
     */
    @GetMapping("query-room")
    public ApiResponse<DouyinRoomInitResult> queryRoom(@NotBlank(message = "房间号不能为空") String roomId) {
        return ApiResponse.success(DouyinApis.roomInit(Long.valueOf(roomId)));
    }

    /**
     * 链接直播间
     *
     * @param roomId 房间号
     */
    @GetMapping("connect-room")
    public ApiResponse<Void> connectRoom(@NotNull(message = "房间号不能为空") Long roomId) {
        douyinService.connectRoom(roomId);
        return ApiResponse.success();
    }

    /**
     * 发送消息
     *
     * @param param LiveSendMsgParam 发送消息
     */
    @PostMapping("send-msg")
    public ApiResponse<Void> sendMsg(@Valid @RequestBody LiveSendMsgParam param) {
        douyinService.sendMsg(param.getRoomId(), param.getUserId(), param.getMsg());
        return ApiResponse.success();
    }

    /**
     * 断开直播间
     *
     * @param roomId 房间号
     */
    @GetMapping("disconnect-room")
    public ApiResponse<Void> disconnectRoom(@NotNull(message = "房间号不能为空") Long roomId) {
        douyinService.disconnectRoom(roomId);
        return ApiResponse.success();
    }

    /**
     * 直播录制
     *
     * @param liveRecordParam param
     */
    @PostMapping("live-record")
    public ApiResponse<Void> liveRecord(@Valid @RequestBody LiveRecordParam liveRecordParam) {
        liveRecordService.startRecording(liveRecordParam);
        return ApiResponse.success();
    }

    /**
     * 停止直播录制
     *
     * @param liveRecordParam param
     */
    @PostMapping("stop-live-record")
    public ApiResponse<Void> stopLiveRecord(@Valid @RequestBody LiveRecordParam liveRecordParam) {
        liveRecordService.stopRecording(liveRecordParam);
        return ApiResponse.success();
    }

    /**
     * 获取直播录制状态
     *
     * @param roomId 房间号
     */
    @GetMapping("live-record-status")
    public ApiResponse<Boolean> liveRecordStatus(@NotBlank(message = "房间号不能为空") String roomId) {
        return ApiResponse.success(liveRecordService.isRecording(roomId));
    }

    /**
     * 直播录制文件下载接口
     *
     * @param response HttpServletResponse
     */
    @PostMapping("/record/download")
    public void downloadRecording(@Valid @RequestBody LiveRecordParam liveRecordParam, HttpServletResponse response) {
        liveRecordService.downloadRecordingToResponse(liveRecordParam, response);
    }
}
