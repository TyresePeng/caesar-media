package org.caesar.media.service;

import cn.hutool.json.JSONObject;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.UnknownFieldSet;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.caesar.crawler.live.douyin.client.browser.DouyinLiveChatBrowser;
import org.caesar.crawler.live.douyin.client.client.DouyinClient;
import org.caesar.crawler.live.douyin.client.client.DouyinLiveChatClient;
import org.caesar.crawler.live.douyin.client.config.DouyinLiveChatClientConfig;
import org.caesar.crawler.live.douyin.client.handler.DouyinBinaryFrameHandler;
import org.caesar.crawler.live.douyin.client.listener.IDouyinMsgListener;
import org.caesar.crawler.live.douyin.codec.msg.*;
import org.caesar.crawler.live.netty.base.enums.PublishTimeType;
import org.caesar.crawler.live.netty.base.enums.SearchChannelType;
import org.caesar.crawler.live.netty.base.enums.SearchSortType;
import org.caesar.media.browser.factory.PlaywrightFactory;
import org.caesar.media.factory.PlaywrightFactoryPool;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 抖音业务
 * @Author: peng.guo
 * @Create: 2025-07-01 16:58
 * @Version 1.0
 **/
@Service
@AllArgsConstructor
@Slf4j
public class DouyinService {

    private final PlaywrightFactoryPool playwrightFactoryPool;
    private final SimpMessagingTemplate messagingTemplate;
    private final PlatformUserService platformUserService;
    private final Map<Long, DouyinLiveChatClient> wsClientMap = new ConcurrentHashMap<>();


    /**
     * 关键字查询接口（外部调用）
     * <p>
     * 利用 Spring Retry 自动重试，最多尝试5次，延时及倍数递增。
     * </p>
     *
     * @param keyword           查询关键字
     * @param offset            数据分页起始位置
     * @param count             请求数据条数
     * @param publishTimeType   发布时段过滤枚举
     * @param searchChannelType 搜索渠道类型枚举
     * @param searchSortType    搜索结果排序类型枚举
     * @return 返回接口响应的 JSON 对象，查询失败返回 null
     */
    @Retryable(
            value = Exception.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 500, multiplier = 3)
    )
    public JSONObject queryKeyWord(String keyword,
                                   int offset,
                                   int count,
                                   PublishTimeType publishTimeType,
                                   SearchChannelType searchChannelType,
                                   SearchSortType searchSortType) {
        PlaywrightFactory playwrightFactory = playwrightFactoryPool.acquire();
        JSONObject resp;
        try {
             resp = DouyinClient.queryKeyWord(playwrightFactory, keyword, offset, count, publishTimeType, searchChannelType, searchSortType);
        }finally {
            playwrightFactoryPool.release(playwrightFactory);
        }
        return resp;
    }

    @Retryable(
            value = Exception.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 500, multiplier = 3)
    )
    public void connectRoom(Long roomId) {
        log.info("开始连接直播间：{}", roomId);
        DouyinLiveChatClientConfig config = DouyinLiveChatClientConfig.builder()
                .roomId(roomId)
                .build();
        DouyinLiveChatClient douyinLiveChatClient = new DouyinLiveChatClient(config, new IDouyinMsgListener() {
            @Override
            public void onDanmuMsg(DouyinBinaryFrameHandler binaryFrameHandler, DouyinDanmuMsg msg) {
                JSONObject message = new JSONObject();
                message.putIfAbsent("content",msg.getContent());
                message.putIfAbsent("nickname", msg.getUsername());
                sendMessage(roomId, message);
            }
            @Override
            public void onLikeMsg(DouyinBinaryFrameHandler binaryFrameHandler, DouyinLikeMsg msg) {
                JSONObject message = new JSONObject();
                message.putIfAbsent("content", "收到点赞");
                message.putIfAbsent("nickname", msg.getUsername());
                sendMessage(roomId, message);
            }

            @Override
            public void onEnterRoomMsg(DouyinBinaryFrameHandler binaryFrameHandler, DouyinEnterRoomMsg msg) {
                JSONObject message = new JSONObject();
                message.putIfAbsent("content", "进入直播间");
                message.putIfAbsent("nickname", msg.getUsername());
                sendMessage(roomId, message);
            }
        });
        douyinLiveChatClient.connect();
        wsClientMap.put(roomId, douyinLiveChatClient);
    }

    /**
     * 断开 WebSocket 连接
     *
     * @param webCaseId WebCaseId
     */
    public void disconnectRoom(Long webCaseId) {
        DouyinLiveChatClient douyinLiveChatClient = wsClientMap.remove(webCaseId);
        if (douyinLiveChatClient != null) {
            douyinLiveChatClient.destroy();
            log.info("🔌 断开直播间 [{}] 的 WebSocket 连接", webCaseId);
        } else {
            log.warn("没有找到 WebSocket 连接用于 webCaseId={}", webCaseId);
        }
    }
    /**
     * 发送消息给前端
     * @param webCaseId 直播间id
     * @param message 信息
     */
    public void  sendMessage(Long webCaseId, JSONObject message){
        messagingTemplate.convertAndSend("/topic/room/"+webCaseId, message);
    }
    /**
     * 定时任务：检查会话是否有效
     * <p>
     * 定时调用关键词查询测试接口，验证 Playwright 会话是否有效。
     * 会话无效则更新平台用户状态为失效，触发重启浏览器授权。
     * 会话有效则释放 PlaywrightFactory 资源供下次复用。
     * </p>
     */
//    @Scheduled(cron = "${dy-account-session-check.cron}")
    public void checkSession() {
        PlaywrightFactory playwrightFactory = playwrightFactoryPool.acquire();
        if (playwrightFactory == null) {
            log.warn("未获取到有效的 PlaywrightFactory 资源，跳过会话检查");
            return;
        }
        try {
            // 查询示例关键词“跳舞”检测会话有效性
            JSONObject jsonObject = DouyinClient.queryKeyWord(playwrightFactory,
                    "跳舞", 0, 10,
                    PublishTimeType.UNLIMITED,
                    SearchChannelType.GENERAL,
                    SearchSortType.GENERAL);
            boolean isValid = jsonObject != null
                    && jsonObject.getJSONArray("data") != null
                    && !jsonObject.getJSONArray("data").isEmpty();

            if (!isValid) {
                log.info("会话已失效，重新启动浏览器，进行授权");
                Long playwrightId = playwrightFactory.getPlaywrightId();
                platformUserService.updateStatus(2, playwrightId);
            } else {
                // 会话有效，释放资源供复用
                playwrightFactoryPool.release(playwrightFactory);
            }
        } catch (Exception e) {
            log.error("检查会话失败: {}", e.getMessage(), e);
        }
    }

    /**
     * 发送弹幕
     * @param roomId 直播间id
     * @param msg 信息id
     */
    public void sendMsg(Long roomId, Long userId,String msg) {
        if (userId!=null){
            playwrightFactoryPool.withFactoryById(userId, factory -> DouyinLiveChatBrowser.sendMsg(factory,roomId, msg));
        }else {
            playwrightFactoryPool.withFactory(factory -> {DouyinLiveChatBrowser.sendMsg(factory,roomId, msg);});
        }
    }

    /**
     * 获取抖音用户视频帖子
     *
     * @param secUserId 用户的sec_user_id
     * @param maxCursor 分页游标，首次请求传0
     * @param count     每页数量，默认10
     * @param userId    指定用户ID，可选
     * @return 用户视频列表
     */
    @Retryable(
            value = Exception.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 500, multiplier = 3)
    )
    public JSONObject getAwemePost(String secUserId, Long maxCursor, Integer count, Long userId) {
        if (userId != null) {
            PlaywrightFactory playwrightFactory = playwrightFactoryPool.acquire();
            JSONObject resp;
            try {
                resp = DouyinClient.getAwemePost(playwrightFactory, secUserId, maxCursor, count);
            } finally {
                playwrightFactoryPool.release(playwrightFactory);
            }
            return resp;
        } else {
            PlaywrightFactory playwrightFactory = playwrightFactoryPool.acquire();
            JSONObject resp;
            try {
                resp = DouyinClient.getAwemePost(playwrightFactory, secUserId, maxCursor, count);
            } finally {
                playwrightFactoryPool.release(playwrightFactory);
            }
            return resp;
        }
    }

    /**
     * 获取抖音视频详情
     *
     * @param awemeId 视频ID
     * @param userId  指定用户ID，可选
     * @return 视频详情
     */
    @Retryable(
            value = Exception.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 500, multiplier = 3)
    )
    public JSONObject getAwemeDetail(String awemeId, Long userId) {
        if (userId != null) {
            PlaywrightFactory playwrightFactory = playwrightFactoryPool.acquire();
            JSONObject resp;
            try {
                resp = DouyinClient.getAwemeDetail(playwrightFactory, awemeId);
            } finally {
                playwrightFactoryPool.release(playwrightFactory);
            }
            return resp;
        } else {
            PlaywrightFactory playwrightFactory = playwrightFactoryPool.acquire();
            JSONObject resp;
            try {
                resp = DouyinClient.getAwemeDetail(playwrightFactory, awemeId);
            } finally {
                playwrightFactoryPool.release(playwrightFactory);
            }
            return resp;
        }
    }

    /**
     * 获取抖音评论列表
     *
     * @param awemeId 视频ID
     * @param cursor  分页游标，首次请求传0
     * @param count   每页数量，默认20
     * @param userId  指定用户ID，可选
     * @return 评论列表
     */
    @Retryable(
            value = Exception.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 500, multiplier = 3)
    )
    public JSONObject getCommentList(String awemeId, Long cursor, Integer count, Long userId) {
        if (userId != null) {
            PlaywrightFactory playwrightFactory = playwrightFactoryPool.acquire();
            JSONObject resp;
            try {
                resp = DouyinClient.getCommentList(playwrightFactory, awemeId, cursor, count);
            } finally {
                playwrightFactoryPool.release(playwrightFactory);
            }
            return resp;
        } else {
            PlaywrightFactory playwrightFactory = playwrightFactoryPool.acquire();
            JSONObject resp;
            try {
                resp = DouyinClient.getCommentList(playwrightFactory, awemeId, cursor, count);
            } finally {
                playwrightFactoryPool.release(playwrightFactory);
            }
            return resp;
        }
    }

    /**
     * 获取抖音评论回复
     *
     * @param awemeId   视频ID
     * @param commentId 评论ID
     * @param cursor    分页游标，首次请求传0
     * @param count     每页数量，默认20
     * @param userId    指定用户ID，可选
     * @return 评论回复
     */
    @Retryable(
            value = Exception.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 500, multiplier = 3)
    )
    public JSONObject getCommentReply(String awemeId, String commentId, Long cursor, Integer count, Long userId) {
        if (userId != null) {
            PlaywrightFactory playwrightFactory = playwrightFactoryPool.acquire();
            JSONObject resp;
            try {
                resp = DouyinClient.getCommentReply(playwrightFactory, awemeId, commentId, cursor, count);
            } finally {
                playwrightFactoryPool.release(playwrightFactory);
            }
            return resp;
        } else {
            PlaywrightFactory playwrightFactory = playwrightFactoryPool.acquire();
            JSONObject resp;
            try {
                resp = DouyinClient.getCommentReply(playwrightFactory, awemeId, commentId, cursor, count);
            } finally {
                playwrightFactoryPool.release(playwrightFactory);
            }
            return resp;
        }
    }

    /**
     * 获取抖音用户信息
     *
     * @param secUserId 用户的sec_user_id
     * @param userId    指定用户ID，可选
     * @return 用户信息
     */
    @Retryable(
            value = Exception.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 500, multiplier = 3)
    )
    public JSONObject getUserProfile(String secUserId, Long userId) {
        if (userId != null) {
            PlaywrightFactory playwrightFactory = playwrightFactoryPool.acquire();
            JSONObject resp;
            try {
                resp = DouyinClient.getUserProfile(playwrightFactory, secUserId);
            } finally {
                playwrightFactoryPool.release(playwrightFactory);
            }
            return resp;
        } else {
            PlaywrightFactory playwrightFactory = playwrightFactoryPool.acquire();
            JSONObject resp;
            try {
                resp = DouyinClient.getUserProfile(playwrightFactory, secUserId);
            } finally {
                playwrightFactoryPool.release(playwrightFactory);
            }
            return resp;
        }
    }
}
