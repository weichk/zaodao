package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.CloseReasonType;
import com.acooly.zaodao.platform.enums.OrderCallEnum;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * 订单状态变更
 *
 * @author xiaohong
 * @create 2018-05-08 16:55
 **/
@Data
public class PlatOrderStatusChangeOrder extends OrderBase {
    @OpenApiField(desc = "订单号", constraint = "订单号")
    private String platOrderNo;

    @OpenApiField(desc = "订单状态", constraint = "订单状态")
    private PlatOrderInfoOrderStatus status;

    @OpenApiField(desc = "关闭订单原因", constraint = "关闭订单原因(取消订单使用)")
    private CloseReasonType closeReasonType;

    @OpenApiField(desc = "关闭原因描述", constraint = "关闭原因描述(取消订单使用)")
    private String closeReasonDesc;

    @OpenApiField(desc = "订单分类", constraint = "订单分类(取消订单使用)")
    private OrderCallEnum closeOrderCall;
}
