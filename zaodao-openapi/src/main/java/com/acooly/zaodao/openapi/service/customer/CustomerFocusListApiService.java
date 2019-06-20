package com.acooly.zaodao.openapi.service.customer;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.customer.CustomerFocusListRequest;
import com.acooly.zaodao.openapi.message.customer.CustomerFocusListResponse;
import com.acooly.zaodao.platform.dto.CustomerFocusDto;
import com.acooly.zaodao.platform.order.CustomerFocusListOrder;
import com.acooly.zaodao.platform.service.manage.CustomerFocusService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 关注/粉丝列表
 *
 * @author xiaohong
 * @create 2017-10-31 9:23
 **/
@OpenApiService(name = "customerFocusList", desc = "用户关注/粉丝列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_customer", name = "用户")
public class CustomerFocusListApiService extends BaseApiService<CustomerFocusListRequest, CustomerFocusListResponse> {
    @Autowired
    private CustomerFocusService customerFocusService;

    @Override
    protected void doService(CustomerFocusListRequest request, CustomerFocusListResponse response) {
        CustomerFocusListOrder customerFocusListOrder = request.toOrder(CustomerFocusListOrder.class);
        customerFocusListOrder.setGid(Ids.gid());
        PageResult<CustomerFocusDto> customerFocusDtoPageResult = customerFocusService.getCustomerFocusList(customerFocusListOrder);
        customerFocusDtoPageResult.throwExceptionIfNotSuccess();
        response.setPageResult(customerFocusDtoPageResult, (u, t) ->{});
    }
}
