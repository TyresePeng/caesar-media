package org.caesar.media.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @Description: 获取抖音用户视频帖子请求参数
 * @Author: peng.guo
 * @Create: 2025-08-07
 * @Version 1.0
 **/
@Data
public class AwemePostParam {

    /**
     * 用户的sec_user_id，必填
     * 这是获取用户视频的唯一必需参数
     */
    @NotBlank(message = "用户ID不能为空")
    private String secUserId;

    /**
     * 分页游标，首次请求传0，后续传上次响应的max_cursor
     */
    @Min(value = 0, message = "游标值不能小于0")
    private Long maxCursor = 0L;

    /**
     * 每页数量，默认18，最大35
     */
    @Min(value = 1, message = "每页数量不能小于1")
    private Integer count = 18;

    /**
     * 指定用户ID，如果指定则使用该用户的session
     * 可选参数，不填则使用默认session
     */
    private Long userId;
}
