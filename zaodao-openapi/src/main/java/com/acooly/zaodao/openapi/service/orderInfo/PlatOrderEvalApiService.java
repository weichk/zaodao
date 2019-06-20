package com.acooly.zaodao.openapi.service.orderInfo;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderDetailRequest;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderDetailResponse;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderEvalRequest;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderEvalResponse;
import com.acooly.zaodao.platform.order.PlatOrderEvalOrder;
import com.acooly.zaodao.platform.service.manage.PlatOrderEvalService;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 订单评价
 *
 * @author xiaohong
 * @create 2017-12-11 11:37
 **/
@OpenApiService(name = "platOrderEval", desc = "订单评价", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_orderInfo", name = "订单")
public class PlatOrderEvalApiService extends BaseApiService<PlatOrderEvalRequest, PlatOrderEvalResponse> {

    @Autowired
    private PlatOrderEvalService platOrderEvalService;

    @Override
    protected void doService(PlatOrderEvalRequest request, PlatOrderEvalResponse response) {
        PlatOrderEvalOrder order = request.toOrder(PlatOrderEvalOrder.class);
        order.setGid(Ids.gid());
        ResultBase resultBase = platOrderEvalService.addPlatOrderEval(order);
        resultBase.throwExceptionIfNotSuccess();
    }
}
