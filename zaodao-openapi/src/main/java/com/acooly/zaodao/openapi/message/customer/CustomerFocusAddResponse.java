package com.acooly.zaodao.openapi.message.customer;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 用户关注响应
 *
 * @author xiaohong
 * @create 2017-10-30 17:39
 **/
@Data
@OpenApiMessage(service = "customerFocusAdd", type = ApiMessageType.Response)
public class CustomerFocusAddResponse extends ApiResponse {

}
