package org.caesar.media.service;

import org.caesar.media.config.LiveRecordProperties;
import org.caesar.media.dto.LiveRecordParam;
import org.caesar.media.enums.ErrorCode;
import org.caesar.media.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * 直播录制服务测试
 * 
 * @author peng.guo
 */
@ExtendWith(MockitoExtension.class)
class LiveRecordServiceTest {

    @Mock
    private LiveRecordProperties liveRecordProperties;

    @InjectMocks
    private LiveRecordService liveRecordService;

    @BeforeEach
    void setUp() {
        when(liveRecordProperties.getMaxConcurrentRecords()).thenReturn(10);
        when(liveRecordProperties.getPath()).thenReturn("/tmp/test/");
        when(liveRecordProperties.getFfmpegPath()).thenReturn("ffmpeg");
    }

    @Test
    void testStartRecordingWithEmptyStreamUrl() {
        // 准备测试数据
        LiveRecordParam param = new LiveRecordParam();
        param.setRoomId("123");
        param.setStreamUrl("");
        param.setQuality("HD");

        // 执行测试并验证异常
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            liveRecordService.startRecording(param);
        });

        assertEquals(ErrorCode.LIVE_ROOM_OFFLINE.getCode(), exception.getCode());
    }

    @Test
    void testStartRecordingWithNullStreamUrl() {
        // 准备测试数据
        LiveRecordParam param = new LiveRecordParam();
        param.setRoomId("123");
        param.setStreamUrl(null);
        param.setQuality("HD");

        // 执行测试并验证异常
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            liveRecordService.startRecording(param);
        });

        assertEquals(ErrorCode.LIVE_ROOM_OFFLINE.getCode(), exception.getCode());
    }

    @Test
    void testGetKey() {
        // 准备测试数据
        LiveRecordParam param = new LiveRecordParam();
        param.setRoomId("123");
        param.setQuality("HD");

        // 执行测试
        String key = liveRecordService.getKey(param);

        // 验证结果
        assertEquals("123_HD", key);
    }

    @Test
    void testIsRecording() {
        // 测试没有录制任务的情况
        boolean isRecording = liveRecordService.isRecording("123");
        assertFalse(isRecording);
    }
}