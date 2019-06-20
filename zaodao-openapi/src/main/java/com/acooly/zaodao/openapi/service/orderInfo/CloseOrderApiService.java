/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 14:47 创建
 *
 */
package com.acooly.zaodao.openapi.service.orderInfo;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.zaodao.openapi.message.orderInfo.CloseOrderRequest;
import com.acooly.zaodao.openapi.message.orderInfo.CloseOrderResponse;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import com.acooly.zaodao.platform.order.PlatOrderStatusChangeOrder;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 关闭订单
 *
 * @author zhike@yiji.com
 */
@OpenApiService(name = "closeOrder", desc = "关闭订单", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_orderInfo", name = "订单")
public class CloseOrderApiService extends BaseApiService<CloseOrderRequest, CloseOrderResponse> {
    @Autowired
    private PlatOrderInfoService platOrderInfoService;

    @Override
    protected void doService(CloseOrderRequest request, CloseOrderResponse response) {
        PlatOrderStatusChangeOrder order = request.toOrder(PlatOrderStatusChangeOrder.class);
        order.setGid(Ids.gid());
        order.setStatus(PlatOrderInfoOrderStatus.close);
        ResultBase resultBase = platOrderInfoService.changePlatOrderStatus(order);
        resultBase.throwExceptionIfNotSuccess();
    }
}
