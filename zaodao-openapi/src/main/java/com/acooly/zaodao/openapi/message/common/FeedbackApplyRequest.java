/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 10:54 创建
 *
 */
package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "feedbackApply", type = ApiMessageType.Request)
public class FeedbackApplyRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识", demo = "O00117052610240611600000")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "反馈标题", constraint = "反馈标题", demo = "早导网app使用情况")
    private String title;

    @NotBlank
    @OpenApiField(desc = "反馈内容", constraint = "反馈内容", demo = "app不错哟")
    private String content;
}
