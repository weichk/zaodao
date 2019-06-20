package com.acooly.zaodao.openapi.service.customer;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.customer.CustomerFocusAddRequest;
import com.acooly.zaodao.openapi.message.customer.CustomerFocusAddResponse;
import com.acooly.zaodao.platform.order.CustomerFocusAddOrder;
import com.acooly.zaodao.platform.service.manage.CustomerFocusService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户关注
 *
 * @author xiaohong
 * @create 2017-10-30 17:36
 **/
@OpenApiService(name = "customerFocusAdd", desc = "用户关注添加", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_customer", name = "用户")
public class CustomerFocusAddApiService extends BaseApiService<CustomerFocusAddRequest, CustomerFocusAddResponse> {
    @Autowired
    private CustomerFocusService customerFocusService;

    @Override
    protected void doService(CustomerFocusAddRequest request, CustomerFocusAddResponse response) {
        CustomerFocusAddOrder customerFocusAddOrder = request.toOrder(CustomerFocusAddOrder.class);
        customerFocusAddOrder.setGid(Ids.gid());
        ResultBase resultBase = customerFocusService.addCustomerFocus(customerFocusAddOrder);
        resultBase.throwExceptionIfNotSuccess();
    }
}
