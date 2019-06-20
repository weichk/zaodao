package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.zaodao.gateway.gsy.message.enums.DeviceTypeEnum;
import com.acooly.zaodao.platform.enums.OrderPayTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * 订单支付请求
 *
 * @author xiaohong
 * @create 2017-11-22 14:41
 **/
@Data
@OpenApiMessage(service = "platOrderPay", type = ApiMessageType.Request)
public class PlatOrderPayRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户id", constraint = "用户id")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "订单号", constraint = "订单号")
    private String platOrderNo;

    @NotBlank
    @OpenApiField(desc = "用户ip", constraint = "用户ip")
    private String userIp;

    @NotNull
    @OpenApiField(desc = "设备类型", constraint = "设备类型")
    private DeviceTypeEnum deviceType;

    @NotNull
    @OpenApiField(desc = "支付方式", constraint = "支付方式")
    private OrderPayTypeEnum payType;
}
