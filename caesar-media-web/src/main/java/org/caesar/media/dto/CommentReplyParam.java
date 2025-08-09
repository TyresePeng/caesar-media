package org.caesar.media.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @Description: 获取抖音评论回复请求参数
 * @Author: peng.guo
 * @Create: 2025-08-07
 * @Version 1.0
 **/
@Data
public class CommentReplyParam {

    /**
     * 视频ID，必填
     */
    @NotBlank(message = "视频ID不能为空")
    private String awemeId;

    /**
     * 评论ID，必填
     */
    @NotBlank(message = "评论ID不能为空")
    private String commentId;

    /**
     * 分页游标，首次请求传0
     */
    @Min(value = 0, message = "游标值不能小于0")
    private Long cursor = 0L;

    /**
     * 每页数量，默认20
     */
    @Min(value = 1, message = "每页数量不能小于1")
    private Integer count = 20;

    /**
     * 指定用户ID，如果指定则使用该用户的session
     * 可选参数，不填则使用默认session
     */
    private Long userId;
}