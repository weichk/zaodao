package com.acooly.zaodao.openapi.service.customer;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.customer.CustomerSigninAddRequest;
import com.acooly.zaodao.openapi.message.customer.CustomerSigninAddResponse;
import com.acooly.zaodao.platform.order.CustomerSigninAddOrder;
import com.acooly.zaodao.platform.result.CustomerSigninAddResult;
import com.acooly.zaodao.platform.service.manage.CustomerFocusService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户签到
 *
 * @author xiaohong
 * @create 2017-11-03 11:32
 **/
@OpenApiService(name = "customerSigninAdd", desc = "用户签到", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_customer", name = "用户")
public class CustomerSigninAddApiService extends BaseApiService<CustomerSigninAddRequest,CustomerSigninAddResponse> {
    @Autowired
    private CustomerFocusService customerFocusService;

    @Override
    protected void doService(CustomerSigninAddRequest request, CustomerSigninAddResponse response) {
        CustomerSigninAddOrder customerSigninAddOrder = request.toOrder(CustomerSigninAddOrder.class);
        customerSigninAddOrder.setGid(Ids.gid());
        CustomerSigninAddResult customerSigninAddResult = customerFocusService.addCustomerSignin(customerSigninAddOrder);
        customerSigninAddResult.throwExceptionIfNotSuccess();
        response.setTimes(customerSigninAddResult.getTimes());
    }
}
