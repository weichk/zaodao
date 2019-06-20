/*
 * 修订记录:
 * zhike@yiji.com 2017-05-26 18:15 创建
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
@OpenApiMessage(service = "modifyMobile", type = ApiMessageType.Request)
public class ModifyMobileRequest extends ApiRequest{

    @NotBlank
    @OpenApiField(desc = "原手机号码", constraint = "原手机号码", demo = "18675825117")
    private String oldMobileNo;

    @NotBlank
    @OpenApiField(desc = "新手机号码", constraint = "新手机号码", demo = "18675825117")
    private String newMobileNo;

    @NotBlank
    @OpenApiField(desc = "短信校验码", constraint = "发送短信的时候获取", demo = "O00117052610240611600000")
    private String verifyCode;


    @NotBlank
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识", demo = "O00117052610240611600000")
    private String userId;

}
