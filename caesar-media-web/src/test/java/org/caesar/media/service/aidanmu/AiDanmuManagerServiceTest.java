//package org.caesar.media.service.aidanmu;
//
//import org.caesar.media.dto.AiDanmuStatusVO;
//import org.caesar.media.enums.AiDanmuStatus;
//import org.caesar.media.exception.BusinessException;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * AI弹幕管理服务测试（使用智谱AI）
// *
// * @author peng.guo
// */
//@SpringBootTest
//@TestPropertySource(properties = {
//    "ai.danmu.enabled=true",
//    "ai.danmu.api.api-key=7d01fe379e2d4592a32abf1596900ff0.qemgh4GRk0JD3c6f",
//    "ai.danmu.api.url=https://open.bigmodel.cn/api/paas/v4/chat/completions",
//    "ai.danmu.api.model=glm-4-flash",
//    "ai.danmu.default-config.random-seconds=5", // 缩短测试时间
//    "ai.danmu.api.timeout=10000" // 缩短超时时间
//})
//public class AiDanmuManagerServiceTest {
//
//    @Resource
//    private AiDanmuManagerService aiDanmuManagerService;
//
//    @Test
//    public void testStartAiDanmuWithRealAi() {
//        System.out.println("=== 测试启动真实AI弹幕 ===");
//
//        Long userId = 1001L;
//        Long roomId = 2001L;
//        String roomDescription = "美妆护肤产品直播间，正在介绍新款面膜";
//        Integer randomSeconds = 10;
//        String aiPersonality = "热情的美妆爱好者";
//
//        try {
//            // 启动AI弹幕
//            aiDanmuManagerService.startAiDanmu(userId, roomId, roomDescription, randomSeconds, aiPersonality);
//            System.out.println("AI弹幕启动成功");
//
//            // 检查状态
//            AiDanmuStatusVO status = aiDanmuManagerService.getAiDanmuStatus(userId);
//            assertNotNull(status);
//            assertEquals(userId, status.getUserId());
//            assertEquals(roomId, status.getRoomId());
//            assertEquals(roomDescription, status.getRoomDescription());
//            assertEquals(aiPersonality, status.getAiPersonality());
//
//            System.out.println("AI弹幕状态: " + status.getStatus());
//            System.out.println("直播间ID: " + status.getRoomId());
//            System.out.println("直播间描述: " + status.getRoomDescription());
//            System.out.println("AI人格: " + status.getAiPersonality());
//
//            // 等待一段时间，观察AI弹幕执行
//            System.out.println("等待AI弹幕执行...");
//            Thread.sleep(15000); // 等待15秒观察执行情况
//
//            // 再次检查状态
//            status = aiDanmuManagerService.getAiDanmuStatus(userId);
//            System.out.println("15秒后状态: " + status.getStatus());
//            System.out.println("已发送弹幕数: " + status.getSentCount());
//
//        } catch (BusinessException e) {
//            if (e.getMessage().contains("用户不存在")) {
//                System.out.println("用户不存在，跳过测试: " + e.getMessage());
//            } else {
//                fail("AI弹幕启动失败: " + e.getMessage());
//            }
//        } catch (Exception e) {
//            System.err.println("测试过程中出现异常: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            // 清理：停止AI弹幕
//            try {
//                aiDanmuManagerService.stopAiDanmu(userId);
//                System.out.println("AI弹幕已停止");
//            } catch (Exception e) {
//                System.out.println("停止AI弹幕时忽略异常: " + e.getMessage());
//            }
//        }
//    }
//
//    @Test
//    public void testMultipleUsersWithRealAi() {
//        System.out.println("=== 测试多用户真实AI弹幕 ===");
//
//        Long[] userIds = {2001L, 2002L, 2003L};
//        Long[] roomIds = {3001L, 3002L, 3003L};
//        String[] descriptions = {
//            "时尚服装直播间，展示春季新款",
//            "数码产品直播间，介绍最新手机",
//            "美食直播间，制作地方特色小吃"
//        };
//        String[] personalities = {
//            "时尚达人",
//            "科技发烧友",
//            "美食爱好者"
//        };
//
//        try {
//            // 启动多个用户的AI弹幕
//            for (int i = 0; i < userIds.length; i++) {
//                try {
//                    aiDanmuManagerService.startAiDanmu(
//                        userIds[i], roomIds[i], descriptions[i], 8, personalities[i]
//                    );
//                    System.out.println("用户 " + userIds[i] + " AI弹幕启动成功");
//                } catch (BusinessException e) {
//                    if (e.getMessage().contains("用户不存在")) {
//                        System.out.println("用户 " + userIds[i] + " 不存在，跳过");
//                        continue;
//                    } else {
//                        throw e;
//                    }
//                }
//            }
//
//            // 获取所有状态
//            List<AiDanmuStatusVO> allStatus = aiDanmuManagerService.getAllAiDanmuStatus();
//            System.out.println("当前活跃AI弹幕数量: " + allStatus.size());
//
//            for (AiDanmuStatusVO status : allStatus) {
//                System.out.println("用户ID: " + status.getUserId() +
//                                 ", 状态: " + status.getStatus() +
//                                 ", 直播间: " + status.getRoomId() +
//                                 ", 描述: " + status.getRoomDescription());
//            }
//
//            // 等待观察执行
//            System.out.println("等待多用户AI弹幕执行...");
//            Thread.sleep(20000); // 等待20秒
//
//            // 再次检查状态
//            allStatus = aiDanmuManagerService.getAllAiDanmuStatus();
//            System.out.println("20秒后活跃AI弹幕数量: " + allStatus.size());
//
//            for (AiDanmuStatusVO status : allStatus) {
//                System.out.println("用户 " + status.getUserId() +
//                                 " 已发送: " + status.getSentCount() + " 条弹幕");
//            }
//
//        } catch (Exception e) {
//            System.err.println("多用户测试失败: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            // 清理：停止所有AI弹幕
//            for (Long userId : userIds) {
//                try {
//                    aiDanmuManagerService.stopAiDanmu(userId);
//                    System.out.println("用户 " + userId + " AI弹幕已停止");
//                } catch (Exception e) {
//                    System.out.println("停止用户 " + userId + " AI弹幕时忽略异常: " + e.getMessage());
//                }
//            }
//        }
//    }
//
//    @Test
//    public void testParameterValidation() {
//        System.out.println("=== 测试参数验证 ===");
//
//        // 测试空用户ID
//        assertThrows(BusinessException.class, () -> {
//            aiDanmuManagerService.startAiDanmu(null, 1001L, "测试", 10, "测试");
//        });
//
//        // 测试空直播间ID
//        assertThrows(BusinessException.class, () -> {
//            aiDanmuManagerService.startAiDanmu(1L, null, "测试", 10, "测试");
//        });
//
//        // 测试负数随机秒数
//        assertThrows(BusinessException.class, () -> {
//            aiDanmuManagerService.startAiDanmu(1L, 1001L, "测试", -1, "测试");
//        });
//
//        // 测试过大随机秒数
//        assertThrows(BusinessException.class, () -> {
//            aiDanmuManagerService.startAiDanmu(1L, 1001L, "测试", 301, "测试");
//        });
//
//        System.out.println("参数验证工作正常");
//    }
//
//    @Test
//    public void testStopNonExistentDanmu() {
//        System.out.println("=== 测试停止不存在的AI弹幕 ===");
//
//        Long userId = 9999L;
//
//        // 停止不存在的AI弹幕（应该抛出异常）
//        assertThrows(BusinessException.class, () -> {
//            aiDanmuManagerService.stopAiDanmu(userId);
//        });
//
//        System.out.println("停止不存在的AI弹幕被正确拒绝");
//    }
//
//    @Test
//    public void testAiDanmuWithDefaultParams() {
//        System.out.println("=== 测试使用默认参数的AI弹幕 ===");
//
//        Long userId = 4001L;
//        Long roomId = 5001L;
//
//        try {
//            // 使用默认参数启动
//            aiDanmuManagerService.startAiDanmu(userId, roomId, null, null, null);
//            System.out.println("使用默认参数启动成功");
//
//            // 检查状态
//            AiDanmuStatusVO status = aiDanmuManagerService.getAiDanmuStatus(userId);
//            assertNotNull(status);
//            assertEquals(AiDanmuStatus.RUNNING, status.getStatus());
//
//            System.out.println("默认直播间描述: " + status.getRoomDescription());
//            System.out.println("默认AI人格: " + status.getAiPersonality());
//
//            // 等待观察
//            Thread.sleep(10000);
//
//            status = aiDanmuManagerService.getAiDanmuStatus(userId);
//            System.out.println("10秒后发送弹幕数: " + status.getSentCount());
//
//        } catch (BusinessException e) {
//            if (e.getMessage().contains("用户不存在")) {
//                System.out.println("用户不存在，跳过测试: " + e.getMessage());
//            } else {
//                fail("默认参数测试失败: " + e.getMessage());
//            }
//        } catch (Exception e) {
//            System.err.println("默认参数测试异常: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            try {
//                aiDanmuManagerService.stopAiDanmu(userId);
//            } catch (Exception e) {
//                System.out.println("清理时忽略异常: " + e.getMessage());
//            }
//        }
//    }
//
//    @Test
//    public void testCleanup() {
//        System.out.println("=== 清理所有测试数据 ===");
//
//        // 停止所有可能的测试用户AI弹幕
//        Long[] testUserIds = {1001L, 2001L, 2002L, 2003L, 4001L, 5001L, 6001L, 7001L, 8001L, 9001L};
//
//        for (Long userId : testUserIds) {
//            try {
//                aiDanmuManagerService.stopAiDanmu(userId);
//                System.out.println("已停止用户 " + userId + " 的AI弹幕");
//            } catch (Exception e) {
//                // 忽略不存在的用户或其他异常
//            }
//        }
//
//        // 检查清理结果
//        List<AiDanmuStatusVO> remainingStatus = aiDanmuManagerService.getAllAiDanmuStatus();
//        System.out.println("清理后剩余活跃AI弹幕数量: " + remainingStatus.size());
//
//        System.out.println("清理完成");
//    }
//}
