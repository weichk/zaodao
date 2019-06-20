package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.common.BankCardListRequest;
import com.acooly.zaodao.openapi.message.common.BankCardListResponse;
import com.acooly.zaodao.platform.dto.CustomerCardDto;
import com.acooly.zaodao.platform.entity.CustomerCard;
import com.acooly.zaodao.platform.order.BankCardListOrder;
import com.acooly.zaodao.platform.service.manage.CustomerCardService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 银行卡列表
 *
 * @author xiaohong
 * @create 2017-11-24 19:05
 **/
@OpenApiService(name = "bankCardList", desc = "用户银行卡", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class BankCardListApiService extends BaseApiService<BankCardListRequest, BankCardListResponse> {
    @Autowired
    private CustomerCardService customerCardService;

    @Override
    protected void doService(BankCardListRequest request, BankCardListResponse response) {
        BankCardListOrder bankCardListOrder = request.toOrder(BankCardListOrder.class);
        bankCardListOrder.setGid(Ids.gid());
        PageResult<CustomerCardDto> result = customerCardService.getCustomerCardList(bankCardListOrder);
        result.throwExceptionIfNotSuccess();
        response.setPageResult(result, (u,t)->{});
    }
}
