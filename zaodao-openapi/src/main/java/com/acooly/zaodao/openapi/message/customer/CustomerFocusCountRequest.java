package com.acooly.zaodao.openapi.message.customer;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户关注个数请求
 *
 * @author xiaohong
 * @create 2017-10-30 17:58
 **/
@Data
@OpenApiMessage(service = "customerFocusCount", type = ApiMessageType.Request)
public class CustomerFocusCountRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;
}
