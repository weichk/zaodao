package com.acooly.zaodao.openapi.message.customer;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 投诉
 *
 * @author xiaohong
 * @create 2018-01-04 16:07
 **/
@Data
@OpenApiMessage(service = "customerComplain", type = ApiMessageType.Response)
public class ComplainApiResponse extends ApiResponse {
}
