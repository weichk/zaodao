package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.common.UserHeadModifyRequest;
import com.acooly.zaodao.openapi.message.common.UserHeadModifyResponse;
import com.acooly.zaodao.platform.order.UserHeadModifyOrder;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xiaohong on 2017/10/17.
 */
@OpenApiService(name = "userHead", desc = "用户头像", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class UserHeadModifyApiService extends BaseApiService<UserHeadModifyRequest, UserHeadModifyResponse> {
    @Autowired
    private CustomerService customerService;

    @Override
    protected void doService(UserHeadModifyRequest userHeadModifyRequest, UserHeadModifyResponse userHeadModifyResponse) {
        UserHeadModifyOrder userHeadModifyOrder = userHeadModifyRequest.toOrder(UserHeadModifyOrder.class);
        userHeadModifyOrder.setGid(Ids.gid());
        ResultBase resultBase = customerService.modifyUserHead(userHeadModifyOrder);
        resultBase.throwExceptionIfNotSuccess();
    }
}
