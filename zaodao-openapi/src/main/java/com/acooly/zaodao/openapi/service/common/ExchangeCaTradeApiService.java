package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.common.ExchangeCaTradeRequest;
import com.acooly.zaodao.openapi.message.common.ExchangeCaTradeResponse;
import com.acooly.zaodao.platform.order.ExchangeCaTradeOrder;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 钙片交易
 *
 * @author xiaohong
 * @create 2017-11-28 19:35
 **/
@OpenApiService(name = "exchangeCaTrade", desc = "钙片兑换交易", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class ExchangeCaTradeApiService extends BaseApiService<ExchangeCaTradeRequest, ExchangeCaTradeResponse> {
    @Autowired
    private PlatOrderInfoService platOrderInfoService;
    @Override
    protected void doService(ExchangeCaTradeRequest request, ExchangeCaTradeResponse response) {
        ExchangeCaTradeOrder order = request.toOrder(ExchangeCaTradeOrder.class);
        order.setGid(Ids.gid());
        ResultBase resultBase = platOrderInfoService.exchangeCaTrade(order);
        resultBase.throwExceptionIfNotSuccess();
    }
}
