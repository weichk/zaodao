/*
 * 修订记录:
 * zhike@yiji.com 2017-05-24 10:47 创建
 *
 */
package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiMessage(service = "zdSendSms", type = ApiMessageType.Response)
@Data
public class ZdSendSmsResponse extends ApiResponse {

    @OpenApiField(desc = "短信校验码", constraint = "短息校验码用于验证短息和其它需要短息校验业务的时候传入，这样就不需要传入手机号码", demo = "O00117052610240611600000")
    private String verifyCode;
}
