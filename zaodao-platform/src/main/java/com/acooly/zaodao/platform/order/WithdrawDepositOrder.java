package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.gateway.gsy.message.enums.DeviceTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 用户提现
 * @author xiaohong
 * @create 2017-11-22 20:16
 **/
@Data
public class WithdrawDepositOrder extends OrderBase{
    @NotBlank
    @OpenApiField(desc = "用户id", constraint = "用户id")
    private String userId;

    @NotNull
    @OpenApiField(desc = "金额", constraint = "金额")
    private Money amount;

    @NotNull
    @OpenApiField(desc = "设备类型", constraint = "设备类型")
    private DeviceTypeEnum deviceType;

//    @NotBlank
//    @OpenApiField(desc = "短信验证码", constraint = "短信验证码")
//    private String captchaCode;
}
