package com.acooly.zaodao.openapi.message.customer;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户屏蔽
 *
 * @author xiaohong
 * @create 2018-01-10 10:18
 **/
@Data
@OpenApiMessage(service = "customerIngore", type = ApiMessageType.Request)
public class CustomerIngoreRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "被屏蔽用户ID", constraint = "被屏蔽用户ID")
    private String ingoreUserId;
}
