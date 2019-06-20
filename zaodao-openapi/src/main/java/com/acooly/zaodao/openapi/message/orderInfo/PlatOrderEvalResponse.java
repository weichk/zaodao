package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 订单评价
 *
 * @author xiaohong
 * @create 2017-12-11 11:40
 **/
@Data
@OpenApiMessage(service = "platOrderEval", type = ApiMessageType.Response)
public class PlatOrderEvalResponse extends ApiResponse {
}
