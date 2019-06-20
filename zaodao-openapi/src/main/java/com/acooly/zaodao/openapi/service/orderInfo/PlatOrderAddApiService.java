package com.acooly.zaodao.openapi.service.orderInfo;

import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderAddRequest;
import com.acooly.zaodao.openapi.message.orderInfo.PlatOrderAddResponse;
import com.acooly.zaodao.platform.enums.ReservationType;
import com.acooly.zaodao.platform.order.PlatOrderInfoOrder;
import com.acooly.zaodao.platform.result.PlatOrderInfoResult;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**添加订单
 * Created by xiaohong on 2017/9/26.
 */
@OpenApiService(name = "platOrderAdd", desc = "添加订单", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_orderInfo", name = "订单")
public class PlatOrderAddApiService extends BaseApiService<PlatOrderAddRequest, PlatOrderAddResponse> {
    @Autowired
    private PlatOrderInfoService platOrderInfoService;

    @Override
    protected void doService(PlatOrderAddRequest request, PlatOrderAddResponse platOrderAddResponse) {
        PlatOrderInfoOrder order = request.toOrder(PlatOrderInfoOrder.class);
        order.setGid(Ids.gid());
        if(request.getReservationType() != null) {
            order.setReservationType(ReservationType.find(request.getReservationType().getCode()));
        }
        PlatOrderInfoResult platOrderInfoResult = platOrderInfoService.createPlatOrder(order);
        platOrderInfoResult.throwExceptionIfNotSuccess();
        platOrderAddResponse.setPlatOrderNo(platOrderInfoResult.getPlatOrderNo());
    }
}
