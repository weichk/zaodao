package com.acooly.zaodao.openapi.service.customer;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.customer.CustomerIngoreRequest;
import com.acooly.zaodao.openapi.message.customer.CustomerIngoreResponse;
import com.acooly.zaodao.platform.order.CustomerIngoreOrder;
import com.acooly.zaodao.platform.service.manage.CustomerIngoreService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 屏蔽用户
 *
 * @author xiaohong
 * @create 2018-01-10 10:16
 **/
@OpenApiService(name = "customerIngore", desc = "用户屏蔽", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_customer", name = "用户")
public class CustomerIngoreApiService extends BaseApiService<CustomerIngoreRequest, CustomerIngoreResponse> {
    @Autowired
    private CustomerIngoreService customerIngoreService;

    @Override
    protected void doService(CustomerIngoreRequest request, CustomerIngoreResponse response) {
        CustomerIngoreOrder order = request.toOrder(CustomerIngoreOrder.class);
        ResultBase resultBase = customerIngoreService.addCustomerIngore(order);
        resultBase.throwExceptionIfNotSuccess();
    }
}
