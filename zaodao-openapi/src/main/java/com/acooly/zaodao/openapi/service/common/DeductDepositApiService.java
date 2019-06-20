/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 15:00 创建
 *
 */
package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.common.message.ApiNotify;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.common.DeductDepositNotify;
import com.acooly.zaodao.openapi.message.common.DeductDepositRequest;
import com.acooly.zaodao.openapi.message.common.DeductDepositResponse;
import com.acooly.zaodao.platform.entity.PlatOrderInfo;
import com.acooly.zaodao.platform.order.DeductDepositOrder;
import com.acooly.zaodao.platform.result.DeductDepositResult;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiService(name = "deductDeposit", desc = "充值", responseType = ResponseType.ASNY)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class DeductDepositApiService extends BaseApiService<DeductDepositRequest, DeductDepositResponse> {
    @Autowired
    private PlatOrderInfoService platOrderInfoService;

    @Override
    protected void doService(DeductDepositRequest request, DeductDepositResponse response) {
        DeductDepositOrder order = request.toOrder(DeductDepositOrder.class);
        order.setGid(Ids.gid());
        DeductDepositResult result = platOrderInfoService.deductDeposit(order);
        result.throwExceptionIfNotSuccess();
        BeanCopier.copy(result, response);
    }

    @Override
    public ApiNotify getApiNotifyBean() {
        return new DeductDepositNotify();
    }
}
