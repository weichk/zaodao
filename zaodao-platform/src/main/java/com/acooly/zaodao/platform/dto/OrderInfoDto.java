/*
 * 修订记录:
 * zhike@yiji.com 2017-06-11 23:38 创建
 *
 */
package com.acooly.zaodao.platform.dto;

import com.acooly.zaodao.platform.enums.CloseReasonType;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import com.acooly.zaodao.platform.enums.ReservationType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class OrderInfoDto implements Serializable {

    /**
     * 订单ID
     */
    private Long orderInfoId;

    /**
     * 用户头像(约我的订单返回导游头像)
     */
    private String headImg;

    /**
     * 游用户名(约我的订单返回导游用户名)
     */
    private String userName;
    /**
     * 姓名(约我的订单返回导游姓名)
     */
    private String realName;
    /**
     * 导游每日价格(我约的订单有效)
     */
    private Long pricePerday;

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
    private String platOrderNo;

    /**
     * 下单时间
     */
    private Date createTime;

    /**
     * 约导天数
     */
    private Integer dayCount;
    /**
     * 联系人备注
     */
    private String contactMemo;
    /**
     * 联系人名称
     */
    private String contactName;
    /**
     * 联系人电话
     */
    private String contactPhone;
    /**
     * 出行用户id
     */
    private String userId;
    /**
     * 详情使用
     */
    private String guideUserId;
    /**
     * 导游名称
     */
    private String guideName;

    /**
     * 预约类型
     */
    private ReservationType reservationType;

    /**
     * 旅行社ID
     */
    private Long travelAgencyId;

    /**
     * 取消原因
     */
    private CloseReasonType closeReasonType;

    /**
     * 取消原因描述
     */
    private String closeReasonDesc;

    /**
     * 旅行社许可证号
     */
    private String licenseNo;

    /**
     * 导游电话号码
     */
    private String guideMobileNo;
}
