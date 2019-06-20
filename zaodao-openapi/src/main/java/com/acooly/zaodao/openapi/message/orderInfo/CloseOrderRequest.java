/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 14:31 创建
 *
 */
package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.zaodao.platform.enums.CloseReasonType;
import com.acooly.zaodao.platform.enums.OrderCallEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "closeOrder", type = ApiMessageType.Request)
public class CloseOrderRequest extends ApiRequest{

    @NotBlank
    @OpenApiField(desc = "订单号", constraint = "订单号", demo = "O00117052610240611600000")
    private String platOrderNo;

    @OpenApiField(desc = "取消原因", constraint = "取消原因", demo = "与其他行程冲突")
    private CloseReasonType closeReasonType;

    @OpenApiField(desc = "取消原因描述", constraint = "取消原因描述", demo = "改签其他航班了")
    private String closeReasonDesc;

    @OpenApiField(desc = "订单分类", constraint = "订单分类(取消订单使用)")
    private OrderCallEnum closeOrderCall;
}
