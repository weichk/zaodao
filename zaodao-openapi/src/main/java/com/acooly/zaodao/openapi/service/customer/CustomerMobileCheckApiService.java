package com.acooly.zaodao.openapi.service.customer;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.customer.CustomerMobileCheckRequest;
import com.acooly.zaodao.openapi.message.customer.CustomerMobileCheckResponse;
import com.acooly.zaodao.openapi.message.dto.MobileCheckDto;
import com.acooly.zaodao.platform.order.CustomerMobileCheckOrder;
import com.acooly.zaodao.platform.result.CustomerMobileCheckResult;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 开放平台验证手机号
 *
 * @author xiaohong
 * @create 2018-01-05 11:16
 **/
@OpenApiService(name = "customerMobileCheck", desc = "用户手机号验证", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_openPlatform", name = "开放平台")
public class CustomerMobileCheckApiService extends BaseApiService<CustomerMobileCheckRequest, CustomerMobileCheckResponse> {
    @Autowired
    private CustomerService customerService;

    @Override
    protected void doService(CustomerMobileCheckRequest request, CustomerMobileCheckResponse response) {
        CustomerMobileCheckOrder order = request.toOrder(CustomerMobileCheckOrder.class);
        order.setGid(Ids.gid());
        CustomerMobileCheckResult result = customerService.checkCustomerMobile(order);
        result.throwExceptionIfNotSuccess();
        MobileCheckDto dto = new MobileCheckDto();
        BeanCopier.copy(result, dto);
        response.setMobileCheckDto(dto);
    }
}
