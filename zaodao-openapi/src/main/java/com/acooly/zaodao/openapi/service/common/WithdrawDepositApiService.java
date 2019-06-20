/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 15:04 创建
 *
 */
package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.zaodao.openapi.message.common.WithdrawDepositNotify;
import com.acooly.zaodao.openapi.message.common.WithdrawDepositRequest;
import com.acooly.zaodao.openapi.message.common.WithdrawDepositResponse;
import com.acooly.openapi.framework.common.message.ApiNotify;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.zaodao.platform.order.WithdrawDepositOrder;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiService(name = "withdrawDeposit", desc = "提现", responseType = ResponseType.ASNY)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class WithdrawDepositApiService extends BaseApiService<WithdrawDepositRequest, WithdrawDepositResponse> {

    @Autowired
    private PlatOrderInfoService platOrderInfoService;

    @Override
    protected void doService(WithdrawDepositRequest request, WithdrawDepositResponse response) {
        WithdrawDepositOrder order = request.toOrder(WithdrawDepositOrder.class);
        order.setGid(Ids.gid());
        ResultBase result = platOrderInfoService.withdrawPlat(order);
        result.throwExceptionIfNotSuccess();
    }

    @Override
    public ApiNotify getApiNotifyBean() {
        return new WithdrawDepositNotify();
    }
}
