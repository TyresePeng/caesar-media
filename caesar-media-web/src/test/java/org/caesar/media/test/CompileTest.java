package org.caesar.media.test;

import org.caesar.media.dto.AiDanmuStatusVO;
import org.caesar.media.dto.AiDanmuStartParam;
import org.caesar.media.enums.AiDanmuStatus;

/**
 * 编译测试类 - 验证所有导入是否正确
 */
public class CompileTest {
    
    public void test() {
        AiDanmuStatusVO statusVO = new AiDanmuStatusVO();
        AiDanmuStartParam startParam = new AiDanmuStartParam();
        AiDanmuStatus status = AiDanmuStatus.RUNNING;
        
        System.out.println("所有类导入成功");
    }
}