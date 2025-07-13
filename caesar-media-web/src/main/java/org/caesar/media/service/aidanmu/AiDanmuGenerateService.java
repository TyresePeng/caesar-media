package org.caesar.media.service.aidanmu;

/**
 * AI弹幕生成服务接口
 * 
 * @author peng.guo
 */
public interface AiDanmuGenerateService {
    
    /**
     * 生成弹幕内容
     * 
     * @param roomDescription 直播间描述
     * @param aiPersonality AI人格设定
     * @return 生成的弹幕内容
     */
    String generateDanmu(String roomDescription, String aiPersonality);
    
    /**
     * 测试AI服务是否可用
     * 
     * @return true-可用，false-不可用
     */
    boolean isAvailable();
}