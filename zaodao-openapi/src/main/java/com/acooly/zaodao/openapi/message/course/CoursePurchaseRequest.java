package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by okobboko on 2017/10/12.
 */
@Data
@OpenApiMessage(service = "coursePurchase", type = ApiMessageType.Request)
public class CoursePurchaseRequest extends ApiRequest {
    @OpenApiField(desc = "用户id", constraint = "用户id")
    @NotNull
    private String userId;

    @OpenApiField(desc = "课程Id", constraint = "课程Id")
    @NotNull
    private Long courseId;
}
