/*
 * 修订记录:
 * zhike@yiji.com 2017-05-24 10:48 创建
 *
 */
package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.common.ZdSendSmsRequest;
import com.acooly.zaodao.openapi.message.common.ZdSendSmsResponse;
import com.acooly.zaodao.platform.order.ZdSendSmsOrder;
import com.acooly.zaodao.platform.service.ZdSmsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiService(name = "zdSendSms", desc = "发送短信", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class ZdSendSmsApiService extends BaseApiService<ZdSendSmsRequest, ZdSendSmsResponse> {

    @Autowired
    private ZdSmsService zdSmsService;

    @Override
    protected void doService(ZdSendSmsRequest request, ZdSendSmsResponse response) {
        ZdSendSmsOrder zdSendSmsOrder = request.toOrder(ZdSendSmsOrder.class);
        zdSendSmsOrder.setGid(Ids.gid());
        ResultBase result =  zdSmsService.zdSendSms(zdSendSmsOrder);
        result.throwExceptionIfNotSuccess();
    }
}
