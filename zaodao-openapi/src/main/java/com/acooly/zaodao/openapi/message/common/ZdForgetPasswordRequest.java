/*
 * 修订记录:
 * zhike@yiji.com 2017-05-24 13:58 创建
 *
 */
package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiMessage(service = "zdForgetPassword", type = ApiMessageType.Request)
@Data
public class ZdForgetPasswordRequest extends ApiRequest{

    @OpenApiField(desc = "短信校验码", constraint = "发送短信的时候获取", demo = "O00117052610240611600000")
    private String verifyCode;

    @OpenApiField(desc = "新密码", constraint = "需要修改的新密码", demo = "12345678",security = true)
    private String newPassword;

    @OpenApiField(desc = "手机号", constraint = "手机号")
    private String mobileNo;

}
