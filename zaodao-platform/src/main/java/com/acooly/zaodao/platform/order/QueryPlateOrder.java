package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.OrderCallEnum;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import lombok.Data;

/**
 * Created by xiaohong on 2017/9/26.
 */
@Data
public class QueryPlateOrder extends PageOrder {
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识")
    private String userId;

    @OpenApiField(desc = "订单状态", constraint = "订单状态")
    private PlatOrderInfoOrderStatus orderStatus;

    @OpenApiField(desc = "订单查询类型", constraint = "订单查询类型")
    private OrderCallEnum orderCallEnum;
}
