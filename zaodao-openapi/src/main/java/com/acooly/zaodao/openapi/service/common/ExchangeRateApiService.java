package com.acooly.zaodao.openapi.service.common;

import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.common.ExchangeRateRequest;
import com.acooly.zaodao.openapi.message.common.ExchangeRateResponse;
import com.acooly.zaodao.openapi.message.dto.ExchangeRateDto;
import org.springframework.beans.factory.annotation.Value;

/**
 * 兑换比率
 *
 * @author xiaohong
 * @create 2017-11-27 17:22
 **/

@OpenApiService(name = "exchangeRate", desc = "兑换比率", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class ExchangeRateApiService extends BaseApiService<ExchangeRateRequest, ExchangeRateResponse> {
    @Value("${zaodao.rate.money2ca}")
    private String rateMoney2ca;

    @Override
    protected void doService(ExchangeRateRequest request, ExchangeRateResponse response) {
        ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
        exchangeRateDto.setMoney2CaRate(rateMoney2ca);
        response.setExchangeRateDto(exchangeRateDto);
    }
}
