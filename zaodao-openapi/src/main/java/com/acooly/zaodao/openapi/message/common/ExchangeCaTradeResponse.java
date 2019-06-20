package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 钙片兑换交易响应
 *
 * @author xiaohong
 * @create 2017-11-28 19:38
 **/
@Data
@OpenApiMessage(service = "exchangeCaTrade", type = ApiMessageType.Response)
public class ExchangeCaTradeResponse extends ApiResponse {
}
