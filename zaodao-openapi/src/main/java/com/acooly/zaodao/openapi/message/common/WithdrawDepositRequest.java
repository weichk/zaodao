/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 15:03 创建
 *
 */
package com.acooly.zaodao.openapi.message.common;

import com.acooly.core.utils.Money;
import com.acooly.core.utils.validate.jsr303.MoneyConstraint;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.zaodao.gateway.gsy.message.enums.DeviceTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "withdrawDeposit", type = ApiMessageType.Request)
public class WithdrawDepositRequest extends ApiRequest{

    @NotBlank
    @OpenApiField(desc = "用户id", constraint = "用户id")
    private String userId;

    @NotNull
    @OpenApiField(desc = "金额", constraint = "金额", demo = "10.33")
    private Money amount;

    @NotNull
    @OpenApiField(desc = "设备类型", constraint = "设备类型")
    private DeviceTypeEnum deviceType;

//    @NotBlank
//    @OpenApiField(desc = "短信验证码", constraint = "短信验证码")
//    private String captchaCode;
}
