package org.caesar.media.factory;

import lombok.extern.log4j.Log4j2;
import org.caesar.crawler.live.netty.base.exception.MediaException;
import org.caesar.media.browser.factory.PlaywrightFactory;
import org.caesar.media.entity.PlatformUser;
import org.caesar.media.service.PlatformUserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * PlaywrightFactoryPool 用于管理多个 PlaywrightFactory 实例的对象池。
 * 支持自动初始化、获取、释放、关闭等操作，确保 Playwright 使用的高效性与线程安全。
 * <p>
 * 新增功能：
 * 1. 支持带异常抛出的 Consumer 操作，方便业务代码捕获异常；
 * 2. 支持根据 PlaywrightFactory 唯一 ID 执行指定实例操作。
 * <p>
 * 设计思路：
 * - 维护一个线程安全的 BlockingQueue 池，存放所有可用的 PlaywrightFactory 实例；
 * - 通过 acquire() 阻塞获取实例，使用完毕后 release() 归还；
 * - 提供 withFactory 系列方法，自动获取归还资源，简化业务调用；
 * - 新增 findFactoryById 辅助方法，从池中查找指定 id 的实例。
 *
 * @author peng.guo
 */
@Component
@Log4j2
public class PlaywrightFactoryPool {

    /**
     * 注入平台用户服务，用于获取活跃用户列表初始化 Playwright 会话
     */
    @Resource
    private PlatformUserService platformUserService;

    /**
     * PlaywrightFactory 对象池，使用阻塞队列保证线程安全
     */
    private final BlockingQueue<PlaywrightFactory> pool = new LinkedBlockingQueue<>();


    /**
     * 容器启动后初始化池中的 PlaywrightFactory 实例，
     * 以及为每个活跃用户初始化对应会话状态
     */
    @PostConstruct
    public void initPool() {
        // 先检测依赖，确保 Playwright 可用
        dependencyCheck();

        // 获取所有活跃用户列表
        List<PlatformUser> platformUserList = platformUserService.platformActiveUserList();

        try {
            for (PlatformUser platformUser : platformUserList) {
                // 为每个用户创建并初始化 PlaywrightFactory 实例
                PlaywrightFactory factory = createFactoryWithSessions(platformUser);
                pool.offer(factory);
            }
        } catch (Exception e) {
            log.error("初始化 PlaywrightFactory 实例失败", e);
        }

        log.info("PlaywrightFactoryPool 初始化完成，共创建 {} 个工厂实例", pool.size());
    }

    /**
     * 依赖检测，尝试创建并初始化一个 PlaywrightFactory，若失败则抛异常
     */
    public void dependencyCheck() {
        try {
            PlaywrightFactory factory = new PlaywrightFactory();
            factory.init(true, true, "");
        } catch (Exception e) {
            log.error("尝试调用Playwright失败", e);
            throw e;
        }
    }

    /**
     * 创建 PlaywrightFactory 并使用用户的 sessionStorage 初始化会话状态
     *
     * @param platformUser 活跃用户实体
     * @return 初始化完毕的 PlaywrightFactory 实例
     */
    private PlaywrightFactory createFactoryWithSessions(PlatformUser platformUser) {
        PlaywrightFactory factory = new PlaywrightFactory();
        try {
            factory.initWithStorageStateJson(false, platformUser.getSessionStorage(), platformUser.getId());
        } catch (Exception e) {
            log.warn("初始化用户会话失败，userId={}, error={}", platformUser.getId(), e.getMessage());
        }
        return factory;
    }

    /**
     * 阻塞获取一个可用的 PlaywrightFactory 实例
     *
     * @return PlaywrightFactory 实例
     * @throws MediaException 当线程被中断时抛出
     */
    public PlaywrightFactory acquire() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 恢复中断状态
            log.error("获取 PlaywrightFactory 失败", e);
            throw new MediaException("PlaywrightFactoryPool acquire error: " + e.getMessage(), e);
        }
    }

    /**
     * 将使用完毕的 PlaywrightFactory 归还池中
     *
     * @param factory 待归还的实例
     */
    public void release(PlaywrightFactory factory) {
        boolean success = pool.offer(factory);
        if (!success) {
            log.warn("PlaywrightFactory 归还失败，池已满");
        }
    }

    /**
     * 使用 PlaywrightFactory 执行指定操作（带返回值），自动获取与归还资源
     *
     * @param action 操作逻辑
     * @param <T>    返回类型
     * @return 操作结果，执行异常时抛出
     */
    public <T> T withFactory(Function<PlaywrightFactory, T> action) {
        PlaywrightFactory factory = acquire();
        try {
            return action.apply(factory);
        } catch (Exception e) {
            log.error("PlaywrightFactory 执行失败", e);
            throw e;
        } finally {
            release(factory);
        }
    }

    /**
     * 使用 PlaywrightFactory 执行指定操作（无返回值），自动获取与归还资源
     *
     * @param action 操作逻辑
     */
    public void withFactory(Consumer<PlaywrightFactory> action) {
        PlaywrightFactory factory = acquire();
        try {
            action.accept(factory);
        } finally {
            release(factory);
        }
    }


    /**
     * 自定义函数式接口，支持带异常抛出的 Consumer，
     * 方便调用方捕获并处理异常
     *
     * @param <T> 参数类型
     */
    @FunctionalInterface
    public interface ThrowingConsumer<T> {
        void accept(T t) throws Exception;
    }

    /**
     * 使用 PlaywrightFactory 执行指定操作（无返回值，带异常抛出），
     * 自动获取与归还资源
     *
     * @param action 操作逻辑，允许抛异常
     * @throws Exception 调用时抛出异常
     */
    public void withFactoryThrows(ThrowingConsumer<PlaywrightFactory> action) throws Exception {
        PlaywrightFactory factory = acquire();
        try {
            action.accept(factory);
        } finally {
            release(factory);
        }
    }

    /**
     * 根据指定 PlaywrightFactory 的唯一 ID 执行操作
     * <p>
     * 注意：
     * 1. 该方法会从池中查找对应的实例，临时从池中移除执行操作，执行完毕后归还池中
     * 2. 需要 PlaywrightFactory 实现 getId() 方法，且保证 ID 唯一
     * 3. 若找不到指定 ID 的实例，会抛出 MediaException
     *
     * @param id     PlaywrightFactory 唯一标识
     * @param action 需要执行的操作
     * @param <T>    返回类型
     * @return 操作结果
     * @throws MediaException 找不到对应实例时抛出
     */
    public <T> T withFactoryById(Long id, Function<PlaywrightFactory, T> action) {
        PlaywrightFactory targetFactory = null;
        // 使用同步块保证对池的遍历和修改线程安全
        synchronized (pool) {
            for (PlaywrightFactory factory : pool) {
                if (id.equals(factory.getPlaywrightId())) {
                    targetFactory = factory;
                    break;
                }
            }
            if (targetFactory != null) {
                pool.remove(targetFactory);
            }
        }
        if (targetFactory == null) {
            throw new MediaException("未找到指定 ID 的 PlaywrightFactory: " + id);
        }
        try {
            return action.apply(targetFactory);
        } finally {
            release(targetFactory);
        }
    }

    /**
     * 容器销毁时，关闭池中所有 PlaywrightFactory 实例
     */
    @PreDestroy
    public void shutdown() {
        log.info("正在关闭 PlaywrightFactoryPool...");
        PlaywrightFactory factory;
        while ((factory = pool.poll()) != null) {
            try {
                factory.close();
            } catch (Exception e) {
                log.warn("关闭 PlaywrightFactory 失败", e);
            }
        }
        log.info("PlaywrightFactoryPool 已关闭");
    }

}
