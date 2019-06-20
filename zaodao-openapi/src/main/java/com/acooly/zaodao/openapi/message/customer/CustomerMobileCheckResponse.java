package com.acooly.zaodao.openapi.message.customer;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.zaodao.openapi.message.dto.MobileCheckDto;
import lombok.Data;

/**
 * 开放平台手机号验证
 *
 * @author xiaohong
 * @create 2018-01-05 11:24
 **/
@Data
@OpenApiMessage(service = "customerMobileCheck", type = ApiMessageType.Response)
public class CustomerMobileCheckResponse extends ApiResponse {

    @OpenApiField(desc = "验证手机号实体", constraint = "验证手机号实体")
    private MobileCheckDto mobileCheckDto;
}
