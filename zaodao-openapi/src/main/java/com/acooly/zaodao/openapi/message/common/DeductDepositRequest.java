/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 14:55 创建
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
import com.acooly.zaodao.platform.enums.OrderPayTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "deductDeposit", type = ApiMessageType.Request)
public class DeductDepositRequest extends ApiRequest{
    @NotBlank
    @OpenApiField(desc = "用户id", constraint = "用户id")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "用户ip", constraint = "用户ip")
    private String userIp;

    @NotNull
    @OpenApiField(desc = "设备类型", constraint = "设备类型")
    private DeviceTypeEnum deviceType;

    @NotNull
    @OpenApiField(desc = "支付方式", constraint = "支付方式")
    private OrderPayTypeEnum payType;

    @NotNull
    @OpenApiField(desc = "充值金额", constraint = "充值金额")
    private Money amount;
}
