package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;

/**
 * 兑换比率
 *
 * @author xiaohong
 * @create 2017-11-27 17:24
 **/
@Data
@OpenApiMessage(service = "exchangeRate", type = ApiMessageType.Request)
public class ExchangeRateRequest extends ApiRequest {
}
