/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 14:17 创建
 *
 */
package com.acooly.zaodao.openapi.message.dto;

import com.acooly.core.utils.Money;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import java.io.Serializable;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class TouristOrderDto implements Serializable {

    @OpenApiField(desc = "订单ID", constraint = "订单ID", demo = "88")
    private Long orderId;

    @OpenApiField(desc = "订单号", constraint = "订单号", demo = "O00117052610240611600000")
    private String orderNo;

    @OpenApiField(desc = "导游真实姓名", constraint = "导游真实姓名", demo = "张三")
    private String guideRealName;

    @OpenApiField(desc = "订单状态", constraint = "订单状态", demo = "pay")
    private PlatOrderInfoOrderStatus orderStatus;

    @OpenApiField(desc = "游玩开始时间", constraint = "游玩开始时间", demo = "2017-05-30")
    private String startPlayTime;

    @OpenApiField(desc = "游玩结束时间", constraint = "游玩结束时间", demo = "2017-05-30")
    private String entPlayTime;

    @OpenApiField(desc = "成人数", constraint = "成人数", demo = "3")
    private int adultCount;

    @OpenApiField(desc = "小孩数", constraint = "小孩数", demo = "2")
    private int childCount;

    @OpenApiField(desc = "订单金额", constraint = "订单金额", demo = "6.66")
    private Money amount;
}
