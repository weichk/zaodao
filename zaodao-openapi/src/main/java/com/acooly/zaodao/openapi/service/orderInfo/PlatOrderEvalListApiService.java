package com.acooly.zaodao.openapi.service.orderInfo;

import com.acooly.core.common.facade.PageResult;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderEvalListRequest;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderEvalListResponse;
import com.acooly.zaodao.platform.dto.PlatOrderEvalDto;
import com.acooly.zaodao.platform.order.PlatOrderEvalListOrder;
import com.acooly.zaodao.platform.service.manage.PlatOrderEvalService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 评价列表
 *
 * @author xiaohong
 * @create 2017-12-12 9:32
 **/
@OpenApiService(name = "platOrderEvalList", desc = "订单评价列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_orderInfo", name = "订单")
public class PlatOrderEvalListApiService extends BaseApiService<PlatOrderEvalListRequest, PlatOrderEvalListResponse>{
    @Autowired
    private PlatOrderEvalService platOrderEvalService;

    @Override
    protected void doService(PlatOrderEvalListRequest request, PlatOrderEvalListResponse response) {
        PlatOrderEvalListOrder order = request.toOrder(PlatOrderEvalListOrder.class);
        PageResult<PlatOrderEvalDto> orderInfoPageResult = platOrderEvalService.getPlateOrderEvalList(order);
        orderInfoPageResult.throwExceptionIfNotSuccess();
        response.setPageResult(orderInfoPageResult, (u, t) -> { });
    }
}
