package com.acooly.zaodao.openapi.message.customer;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户签到响应
 *
 * @author xiaohong
 * @create 2017-11-03 11:34
 **/
@Data
@OpenApiMessage(service = "customerSigninAdd", type = ApiMessageType.Response)
public class CustomerSigninAddResponse extends ApiResponse {
    @OpenApiField(desc = "签到次数", constraint = "签到次数")
    private Integer times;
}
