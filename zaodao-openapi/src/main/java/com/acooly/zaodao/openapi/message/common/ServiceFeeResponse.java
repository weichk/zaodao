package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.OrderServiceFeeInfoDto;
import lombok.Data;

/**
 * 服务费
 *
 * @author xiaohong
 * @create 2018-06-01 18:06
 **/
@Data
@OpenApiMessage(service = "serviceFee", type = ApiMessageType.Response)
public class ServiceFeeResponse extends PageApiResponse<OrderServiceFeeInfoDto> {
}
