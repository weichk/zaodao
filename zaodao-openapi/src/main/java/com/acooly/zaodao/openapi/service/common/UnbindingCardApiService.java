package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.common.UnbindingCardRequest;
import com.acooly.zaodao.openapi.message.common.UnbindingCardResponse;
import com.acooly.zaodao.platform.order.UnbindingCardOrder;
import com.acooly.zaodao.platform.service.manage.CustomerCardService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 解绑银行卡
 *
 * @author xiaohong
 * @create 2017-11-24 20:09
 **/
@OpenApiService(name = "unbindingCard", desc = "解绑银行卡", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class UnbindingCardApiService extends BaseApiService<UnbindingCardRequest, UnbindingCardResponse> {
    @Autowired
    private CustomerCardService customerCardService;

    @Override
    protected void doService(UnbindingCardRequest request, UnbindingCardResponse response) {
        UnbindingCardOrder unbindingCardOrder = request.toOrder(UnbindingCardOrder.class);
        unbindingCardOrder.setGid(Ids.gid());
        ResultBase resultBase = customerCardService.unbindingCard(unbindingCardOrder);
        resultBase.throwExceptionIfNotSuccess();
    }
}
