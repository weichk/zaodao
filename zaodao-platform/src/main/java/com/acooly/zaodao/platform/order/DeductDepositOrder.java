package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.gateway.gsy.message.enums.DeviceTypeEnum;
import com.acooly.zaodao.platform.enums.OrderPayTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 充值
 *
 * @author xiaohong
 * @create 2017-11-27 15:55
 **/
@Data
public class DeductDepositOrder extends OrderBase {
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
