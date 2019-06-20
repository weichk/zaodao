/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 14:07 创建
 *
 */
package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.zaodao.openapi.message.enums.OrderStatusEnum;
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
@OpenApiMessage(service = "touristOrder", type = ApiMessageType.Request)
public class TouristOrderRequest extends ApiRequest{

    @NotBlank
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识", demo = "O00117052610240611600000")
    private String userId;

    @OpenApiField(desc = "订单状态", constraint = "订单状态，默认为全部", demo = "all")
    private OrderStatusEnum orderStatus = OrderStatusEnum.all;
}
