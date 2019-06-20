package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 订单完成
 *
 * @author xiaohong
 * @create 2017-12-11 11:30
 **/
@Data
@OpenApiMessage(service = "platOrderFinish", type = ApiMessageType.Response)
public class PlatOrderFinishResponse extends ApiResponse {
}
