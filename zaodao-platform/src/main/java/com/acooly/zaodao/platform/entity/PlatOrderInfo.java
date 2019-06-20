/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-24
*/
package com.acooly.zaodao.platform.entity;

import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.core.utils.Dates;
import com.acooly.zaodao.platform.enums.CloseReasonType;
import com.acooly.zaodao.platform.enums.OrderCallEnum;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import com.acooly.zaodao.platform.enums.ReservationType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 订单表 Entity
 *
 * @author zhike Date: 2017-05-24 23:14:32
 */
@Data
@Entity
@Table(name = "zd_plat_order_info")
public class PlatOrderInfo extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 游客ID */
	private String touristId;

	/** 导游ID */
	private String tourGuideId;

	/** 订单号(唯一索引) */
	@Size(max = 64)
	private String orderNo;

	/** 游玩天数 */
	private Integer dayCount;

	/** 成人数 */
	private Integer adultCount;

	/** 小孩数 */
	private Integer childCount;

	/** 订单金额 */
	private Long orderAmount;

	/** 订单状态 */
	@Enumerated(EnumType.STRING)
	private PlatOrderInfoOrderStatus orderStatus;

	/** 游玩开始时间 */
	private Date startPlayTime;

	/** 游玩结束时间 */
	private Date endPlayTime;

	/** 联系人姓名 */
	private String contactName;

	/** 联系人手机 */
	private String contactPhone;

	/** 联系人备注 */
	private String contactMemo;

	/**
	 * 导游每日金额
	 */
	private Long pricePerDay;

	/**
	 *旅行社ID(预约类型=旅行社预约)
	 */
	private Long travelAgencyId;

	/**
	 * 预约类型
	 */
	@Enumerated(EnumType.STRING)
	private ReservationType reservationType;

	/**
	 * 取消原因
	 */
	@Enumerated(EnumType.STRING)
	private CloseReasonType closeReasonType;

	/**
	 * 取消原因描述
	 */
	private String closeReasonDesc;

	/**
	 * 导游确认完成时间
	 */
	private Date guideConfirmTime;

	/**
	 * (取消订单分类)
	 */
	@Enumerated(EnumType.STRING)
	private OrderCallEnum closeOrderCall;

	/**
	 * 导游是否已读：0-未读; 1-已读
	 */
	private Integer readStatusGuide = 0;

	/**
	 * 游客是否已读：0-未读; 1-已读
	 */
	private Integer readStatusTourist = 0;

	@Transient
	private String startTime;

	@Transient
	private String endTime;

	@Transient
	private String orderTime;

//	/**
//	 * 业务属性
//	 */
//	@Transient
//	private int amount = 0;

	/**
	 * 支付宝二维码
	 */
	@Transient
	private String scanUrl;

//	public int getAmount() {
//		this.amount = Money.cent(orderAmount).getAmount().intValue();
//		return amount;
//	}

	public String getOrderTime() {
		this.orderTime = Dates.format(getCreateTime());
		return orderTime;
	}

	public String getStartTime() {
		this.startTime = Dates.format(getStartPlayTime(), Dates.CHINESE_DATE_FORMAT_LINE);
		return startTime;
	}

	public String getEndTime() {
		this.endTime = Dates.format(getEndPlayTime(), Dates.CHINESE_DATE_FORMAT_LINE);
		return endTime;
	}
}
