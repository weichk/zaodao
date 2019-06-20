package com.acooly.zaodao.platform.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import lombok.Data;

import java.util.Date;

/**
 * Created by xiaohong on 2017/9/26.
 */
@Data
public class PlateOrderResult  extends ResultBase {
    /**
     * 订单ID
     */
    private Long orderInfoId;

    /**
     * 游玩会员头像
     */
    private String headImg;

    /**
     * 游玩会员用户名
     */
    private String userName;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 游玩开始时间
     */
    private Date startPlayTime;

    /**
     * 游玩结束时间
     */
    private Date endPlayTime;

    /**
     * 成人数
     */
    private Integer adultCount;

    /**
     * 小孩数
     */
    private Integer childCount;

    /**
     * 订单金额
     */
    private Long orderAmount;

    /**
     * 订单状态
     */
    private PlatOrderInfoOrderStatus orderStatus;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 下单时间
     */
    private Date createTime;
}
