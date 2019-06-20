/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 10:20 创建
 *
 */
package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.common.BindingCardRequest;
import com.acooly.zaodao.openapi.message.common.BindingCardResponse;
import com.acooly.zaodao.platform.order.BindingCardOrder;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiService(name = "bindingCard", desc = "绑定银行卡", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class BindingCardApiService extends BaseApiService<BindingCardRequest, BindingCardResponse> {
    @Autowired
    private PlatOrderInfoService platOrderInfoService;

    @Override
    protected void doService(BindingCardRequest request, BindingCardResponse response) {
        BindingCardOrder bindingCardOrder = request.toOrder(BindingCardOrder.class);
        bindingCardOrder.setGid(Ids.gid());
        ResultBase resultBase = platOrderInfoService.bindingCard(bindingCardOrder);
        resultBase.throwExceptionIfNotSuccess();
    }
}
