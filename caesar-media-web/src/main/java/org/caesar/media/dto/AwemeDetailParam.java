package org.caesar.media.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description: 获取抖音视频详情请求参数
 * @Author: peng.guo
 * @Create: 2025-08-07
 * @Version 1.0
 **/
@Data
public class AwemeDetailParam {

    /**
     * 视频ID，必填
     */
    @NotBlank(message = "视频ID不能为空")
    private String awemeId;

    /**
     * 指定用户ID，如果指定则使用该用户的session
     * 可选参数，不填则使用默认session
     */
    private Long userId;
}