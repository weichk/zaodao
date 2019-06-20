/*
 * 修订记录:
 * zhike@yiji.com 2017-05-24 13:59 创建
 *
 */
package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.zaodao.openapi.message.common.ZdForgetPasswordRequest;
import com.acooly.zaodao.openapi.message.common.ZdForgetPasswordResponse;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.zaodao.platform.order.ForgetPasswordOrder;
import com.acooly.zaodao.platform.service.manage.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiService(name = "zdForgetPassword", desc = "忘记密码", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class ZdForgetPasswordApiService extends BaseApiService<ZdForgetPasswordRequest,ZdForgetPasswordResponse> {
    @Autowired
    private PasswordService passwordService;

    @Override
    protected void doService(ZdForgetPasswordRequest request, ZdForgetPasswordResponse response) {
        ForgetPasswordOrder forgetPasswordOrder = request.toOrder(ForgetPasswordOrder.class);
        BeanCopier.copy(request, forgetPasswordOrder);
        forgetPasswordOrder.setGid(Ids.gid());
        ResultBase resultBase = passwordService.ForgetPassword(forgetPasswordOrder);
        resultBase.throwExceptionIfNotSuccess();
    }
}
