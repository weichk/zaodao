package com.acooly.zaodao.openapi.service.orderInfo;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.orderInfo.AmountModifyRequest;
import com.acooly.zaodao.openapi.message.orderInfo.AmountModifyResponse;
import com.acooly.zaodao.platform.order.AmountModifyOrder;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 导游修改订单金额
 *
 * @author xiaohong
 * @create 2018-05-08 17:34
 **/
@OpenApiService(name = "amountModify", desc = "修改订单金额", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_orderInfo", name = "订单")
public class AmountModifyApiService extends BaseApiService<AmountModifyRequest, AmountModifyResponse> {
    @Autowired
    private PlatOrderInfoService platOrderInfoService;

    @Override
    protected void doService(AmountModifyRequest request, AmountModifyResponse response) {
        AmountModifyOrder order = request.toOrder(AmountModifyOrder.class);
        order.setGid(Ids.gid());
        ResultBase result = platOrderInfoService.modifyOrderAmount(order);
        result.throwExceptionIfNotSuccess();
    }
}
