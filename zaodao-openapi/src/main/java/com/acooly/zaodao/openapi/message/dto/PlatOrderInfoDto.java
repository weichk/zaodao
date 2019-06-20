package com.acooly.zaodao.openapi.message.dto;

import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xiaohong on 2017/9/26.
 */
@Data
public class PlatOrderInfoDto implements Serializable {

    @OpenApiField(desc = "订单ID", constraint = "订单ID")
    private Long orderInfoId;

    @OpenApiField(desc = "用户头像(我约的订单返回导游头像)", constraint = "用户头像")
    private String headImg;

    @OpenApiField(desc = "用户名(我约的订单返回导游用户名)", constraint = "用户名")
    private String userName;

    @OpenApiField(desc = "姓名(我约的订单返回导游姓名)", constraint = "姓名")
    private String realName;

    @OpenApiField(desc = "导游每日价格(我约的订单有效)", constraint = "导游每日价格")
    private Money pricePerday;

    @OpenApiField(desc = "游玩开始时间", constraint = "游玩开始时间")
    private Date startPlayTime;

    @OpenApiField(desc = "游玩结束时间", constraint = "游玩结束时间")
    private Date endPlayTime;

    @OpenApiField(desc = "成人数", constraint = "成人数")
    private Integer adultCount;

    @OpenApiField(desc = "小孩数", constraint = "小孩数")
    private Integer childCount;

    @OpenApiField(desc = "订单金额", constraint = "订单金额")
    private Money orderAmount;

    @OpenApiField(desc = "订单状态", constraint = "订单状态")
    private PlatOrderInfoOrderStatus orderStatus;

    @OpenApiField(desc = "订单号", constraint = "订单号")
    private String platOrderNo;

    @OpenApiField(desc = "下单时间", constraint = "下单时间")
    private Date createTime;

    @OpenApiField(desc = "约导天数", constraint = "约导天数")
    private Integer dayCount;

    @OpenApiField(desc = "订单状态描述文本", constraint = "订单状态描述文本")
    private String orderStatusText;

    @OpenApiField(desc = "是否可以重新支付", constraint = "true：是，false：否")
    private boolean payAgain;

    @OpenApiField(desc = "是否可以取消订单", constraint = "true：是，false：否")
    private boolean cancel;
}

