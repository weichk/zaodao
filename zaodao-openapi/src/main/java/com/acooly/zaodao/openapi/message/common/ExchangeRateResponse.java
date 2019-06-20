package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.zaodao.openapi.message.dto.ExchangeRateDto;
import lombok.Data;

/**
 * @author xiaohong
 * @create 2017-11-27 17:24
 **/
@Data
@OpenApiMessage(service = "exchangeRate", type = ApiMessageType.Response)
public class ExchangeRateResponse extends ApiResponse{
    @OpenApiField(desc = "金钱转换钙片比率", constraint = "金钱转换钙片比率", demo = "10(1元10个钙片)")
    private ExchangeRateDto exchangeRateDto;
}
