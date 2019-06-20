/*
 * 修订记录:
 * zhike@yiji.com 2017-05-24 13:51 创建
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
@OpenApiMessage(service = "zdVerifySmsCode", type = ApiMessageType.Request)
@Data
public class ZdVerifySmsCodeRequest extends ApiRequest{

    @OpenApiField(desc = "短信校验码", constraint = "发送短信的时候获取", demo = "O00117052610240611600000")
    private String verifyCode;

    @OpenApiField(desc = "验证码", constraint = "短信验证码,用户填入(有效时间1分钟)", demo = "021312")
    private String smsCode;
}
