/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 10:16 创建
 *
 */
package com.acooly.zaodao.openapi.service.common;

import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.common.ModifyMobileRequest;
import com.acooly.zaodao.openapi.message.common.ModifyMobileResponse;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiService(name = "modifyMobile", desc = "修改电话号码", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class ModifyMobileApiService extends BaseApiService<ModifyMobileRequest,ModifyMobileResponse> {

    @Override
    protected void doService(ModifyMobileRequest request, ModifyMobileResponse response) {

    }
}
