package org.caesar.media.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Description: 批量操作参数
 * @Author: peng.guo
 * @Create: 2025-01-06 16:40
 * @Version 1.0
 **/
@Data
public class BatchOperationParam {

    /**
     * 房间ID列表
     */
    @NotEmpty(message = "房间ID列表不能为空")
    private List<String> roomIds;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 操作类型
     */
    private String operationType;
}