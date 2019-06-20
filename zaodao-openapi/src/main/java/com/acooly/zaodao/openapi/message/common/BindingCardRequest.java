/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 10:20 创建
 *
 */
package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "bindingCard", type = ApiMessageType.Request)
public class BindingCardRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识", demo = "O00117052610240611600000")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "真实姓名", constraint = "真实姓名", demo = "张三")
    private String realName;

    @NotBlank
    @OpenApiField(desc = "银行code", constraint = "银行code", demo = "ICBC")
    private String bankCode;

    @NotBlank
    @OpenApiField(desc = "银行卡卡号", constraint = "银行卡卡号", demo = "O00117052610240611600000")
    private String bankCardNo;

    @NotBlank
    @OpenApiField(desc = "银行预留手机号", constraint = "银行预留手机号", demo = "18696725337")
    private String mobileNo;

    @NotBlank
    @OpenApiField(desc = "身份证号", constraint = "身份证号", demo = "500221198709190011")
    private String idNumber;
}
