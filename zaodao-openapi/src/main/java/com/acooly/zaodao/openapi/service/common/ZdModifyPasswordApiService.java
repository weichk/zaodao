/*
 * 修订记录:
 * zhike@yiji.com 2017-05-24 11:02 创建
 *
 */
package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.zaodao.openapi.message.common.ZdModifyPasswordRequest;
import com.acooly.zaodao.openapi.message.common.ZdModifyPasswordResponse;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.zaodao.platform.order.ModifyPasswordOrder;
import com.acooly.zaodao.platform.service.manage.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiService(name = "zdModifyPassword", desc = "修改密码", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class ZdModifyPasswordApiService extends BaseApiService<ZdModifyPasswordRequest,ZdModifyPasswordResponse> {
    @Autowired
    private PasswordService passwordService;
    @Override
    protected void doService(ZdModifyPasswordRequest request, ZdModifyPasswordResponse response) {
        ModifyPasswordOrder modifyPasswordOrder = request.toOrder(ModifyPasswordOrder.class);
        BeanCopier.copy(request, modifyPasswordOrder);
        modifyPasswordOrder.setGid(Ids.gid());
        ResultBase resultBase = passwordService.ModifyPassword(modifyPasswordOrder);
        resultBase.throwExceptionIfNotSuccess();
    }
}
