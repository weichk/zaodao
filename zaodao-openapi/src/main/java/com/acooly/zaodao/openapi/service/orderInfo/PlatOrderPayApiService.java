package com.acooly.zaodao.openapi.service.orderInfo;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderPayRequest;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderPayResponse;
import com.acooly.zaodao.platform.order.PlatOrderPayOrder;
import com.acooly.zaodao.platform.result.PlatOrderPayResult;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 订单支付
 *
 * @author xiaohong
 * @create 2017-11-22 14:39
 **/
@OpenApiService(name = "platOrderPay", desc = "支付订单", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_orderInfo", name = "订单")
public class PlatOrderPayApiService extends BaseApiService<PlatOrderPayRequest, PlatOrderPayResponse> {
    @Autowired
    private PlatOrderInfoService platOrderInfoService;

    @Override
    protected void doService(PlatOrderPayRequest request, PlatOrderPayResponse response) {
        PlatOrderPayOrder platOrderPayOrder = request.toOrder(PlatOrderPayOrder.class);
        platOrderPayOrder.setGid(Ids.gid());
        PlatOrderPayResult platOrderPayResult = platOrderInfoService.payPlatOrder(platOrderPayOrder);
        platOrderPayResult.throwExceptionIfNotSuccess();
        response.setCodeUrl(platOrderPayResult.getCodeUrl());
        response.setCodeUrlContent(platOrderPayResult.getCodeUrlContent());
    }
}
