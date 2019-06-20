package com.acooly.zaodao.openapi.service.orderInfo;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderListRequest;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderListResponse;
import com.acooly.zaodao.platform.dto.OrderInfoDto;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import com.acooly.zaodao.platform.order.QueryPlateOrder;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xiaohong on 2017/9/26.
 */
@OpenApiService(name = "platOrderList", desc = "订单列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_orderInfo", name = "订单")
public class PlatOrderListApiService extends BaseApiService<PlatOrderListRequest, PlatOrderListResponse> {
    @Autowired
    private PlatOrderInfoService platOrderInfoService;

    @Override
    protected void doService(PlatOrderListRequest request, PlatOrderListResponse response) {
        QueryPlateOrder order = request.toOrder(QueryPlateOrder.class);
        BeanCopier.copy(request, order);

        PageResult<OrderInfoDto> orderInfoPageResult = platOrderInfoService.getPagePlateOrderList(order);
        orderInfoPageResult.throwExceptionIfNotSuccess();
        response.setPageResult(orderInfoPageResult, (u, t) ->{
            if (PlatOrderInfoOrderStatus.noPay.equals(u.getOrderStatus())
                    || PlatOrderInfoOrderStatus.fail.equals(u.getOrderStatus())) {
                t.setPayAgain(true);
                t.setCancel(true);
            } else {
                t.setPayAgain(false);
                t.setCancel(false);
            }
            t.setOrderStatusText(u.getOrderStatus().getMessage());
            t.setOrderAmount(Money.cent(u.getOrderAmount()));
            t.setPricePerday(Money.cent(u.getPricePerday()));
        });
    }
}