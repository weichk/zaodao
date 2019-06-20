package com.acooly.zaodao.openapi.message.customer;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 用户屏蔽
 *
 * @author xiaohong
 * @create 2018-01-10 10:19
 **/
@Data
@OpenApiMessage(service = "customerIngore", type = ApiMessageType.Response)
public class CustomerIngoreResponse extends ApiResponse {

}
