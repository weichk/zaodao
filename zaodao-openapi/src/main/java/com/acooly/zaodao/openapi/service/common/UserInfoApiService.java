/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 10:20 创建
 *
 */
package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.common.UserInfoRequest;
import com.acooly.zaodao.openapi.message.common.UserInfoResponse;
import com.acooly.zaodao.platform.result.UserInfoResult;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiService(name = "userInfo", desc = "用户信息", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class UserInfoApiService extends BaseApiService<UserInfoRequest, UserInfoResponse> {

    @Autowired
    private CustomerService customerService;

    @Override
    protected void doService(UserInfoRequest request, UserInfoResponse response) {
        UserInfoResult dto = customerService.findUserInfo(request.getUserId());
        BeanCopier.copy(dto, response);
    }
}
