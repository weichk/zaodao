package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * App关键字
 *
 * @author xiaohong
 * @create 2018-06-14 9:53
 **/
@Data
@OpenApiMessage(service = "applicationKey", type = ApiMessageType.Request)
public class ApplicationKeyRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识")
    private String userId;
}
