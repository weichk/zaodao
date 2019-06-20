/*
 * 修订记录:
 * zhike@yiji.com 2017-05-24 10:07 创建
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
@OpenApiMessage(service = "zdRegister", type = ApiMessageType.Response)
@Data
public class ZdRegisterResponse extends ApiResponse {

    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识", demo = "O00117052610240611600000")
    private String userId;

    @OpenApiField(desc = "访问码", constraint = "客户的用户名,作为登录所有接口的签名accessKey")
    private String accessKey;

    @OpenApiField(desc = "安全码", constraint = "登录后所有接口的签名秘钥")
    private String secretKey;

}
