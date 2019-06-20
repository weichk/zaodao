package com.acooly.zaodao.openapi.service.customer;

import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.customer.CustomerFocusCountRequest;
import com.acooly.zaodao.openapi.message.customer.CustomerFocusCountResponse;
import com.acooly.zaodao.platform.order.CustomerFocusCountOrder;
import com.acooly.zaodao.platform.result.CustomerFocusCountResult;
import com.acooly.zaodao.platform.service.manage.CreditSigninService;
import com.acooly.zaodao.platform.service.manage.CustomerFocusService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户关注个数
 *
 * @author xiaohong
 * @create 2017-10-30 17:57
 **/
@OpenApiService(name = "customerFocusCount", desc = "用户关注个数", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_customer", name = "用户")
public class CustomerFocusCountApiService extends BaseApiService<CustomerFocusCountRequest, CustomerFocusCountResponse> {
    @Autowired
    private CustomerFocusService customerFocusService;

    @Override
    protected void doService(CustomerFocusCountRequest request, CustomerFocusCountResponse response) {
        CustomerFocusCountOrder customerFocusCountOrder = request.toOrder(CustomerFocusCountOrder.class);
        CustomerFocusCountResult customerFocusCountResult = customerFocusService.getCustomerFocusCount(customerFocusCountOrder);
        customerFocusCountResult.throwExceptionIfNotSuccess();
        response.setCount(customerFocusCountResult.getCount());
        response.setFocusCount(customerFocusCountResult.getFocusCount());
    }
}
