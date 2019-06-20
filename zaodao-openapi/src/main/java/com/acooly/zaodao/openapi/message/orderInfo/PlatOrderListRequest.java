package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import com.acooly.zaodao.platform.enums.OrderCallEnum;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import lombok.Data;

/**
 * Created by xiaohong on 2017/9/26.
 */
@Data
@OpenApiMessage(service = "platOrderList", type = ApiMessageType.Request)
public class PlatOrderListRequest extends PageApiRequest {
    @OpenApiField(desc = "用户id", constraint = "用户id")
    private String userId;

    @OpenApiField(desc = "订单状态", constraint = "订单状态")
    private PlatOrderInfoOrderStatus orderStatus;

    @OpenApiField(desc = "订单查询类型", constraint = "订单查询类型")
    private OrderCallEnum orderCallEnum;
}

