package com.acooly.zaodao.openapi.service.orderInfo;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderFinishRequest;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderFinishResponse;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import com.acooly.zaodao.platform.order.PlatOrderFinishOrder;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 订单完成
 *
 * @author xiaohong
 * @create 2017-12-11 11:28
 **/
@OpenApiService(name = "platOrderFinish", desc = "订单完成", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_orderInfo", name = "订单")
public class PlatOrderFinishApiService extends BaseApiService<PlatOrderFinishRequest, PlatOrderFinishResponse> {
    @Autowired
    private PlatOrderInfoService platOrderInfoService;

    @Override
    protected void doService(PlatOrderFinishRequest request, PlatOrderFinishResponse response) {
        PlatOrderFinishOrder order = request.toOrder(PlatOrderFinishOrder.class);
        order.setGid(Ids.gid());
        ResultBase resultBase = platOrderInfoService.setPlatOrderFinish(order);
        resultBase.throwExceptionIfNotSuccess();
    }
}
