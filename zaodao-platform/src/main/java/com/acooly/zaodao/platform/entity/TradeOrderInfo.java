/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-08-09
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.zaodao.platform.enums.OrderStatusEnum;
import com.acooly.zaodao.platform.enums.OrderSubTradeTypeEnum;
import com.acooly.zaodao.platform.enums.OrderTradeTypeEnum;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 交易订单表 Entity
 *
 * @author zhike
 * Date: 2017-08-09 11:10:35
 */
@Entity
@Table(name = "zd_trade_order_info")
public class TradeOrderInfo extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 会员ID */
	@NotEmpty
	@Size(max=64)
    private String userId;

	/** 银行会员ID */
	@Size(max=64)
    private String outUserId;

	/** 订单号 */
	@NotEmpty
	@Size(max=64)
    private String orderNo;

	/** 交易描述 */
	@Size(max=64)
    private String tradeText;

	/** 交易名称 */
	@Size(max=64)
    private String tradeName;

	/** 交易时间(支付时间) */
	@NotNull
    private Date tradeTime;

	/** 交易完成时间(订单结束时间) */
    private Date finishTime;

	/** 订单金额 */
	@NotNull
    private Long amount;

	/** 订单交易类型 */
    @Enumerated(EnumType.STRING)
	@NotNull
    private OrderTradeTypeEnum orderTradeType;

	/** 订单子交易类型 */
    @Enumerated(EnumType.STRING)
	@NotNull
    private OrderSubTradeTypeEnum orderSubTradeType;

	/** 订单状态 */
    @Enumerated(EnumType.STRING)
	@NotNull
    private OrderStatusEnum orderStatus;

	/**
	 * 订单交易流水号
	 */
	private String orderTradeNo;

	/**
	 * 支付宝二维码
	 */
	@Transient
	private String aliScanUrl;

	/**
	 * 微信二维码
	 */
	@Transient
	private String weiScanUrl;

	public String getTradeText() {
		return tradeText;
	}

	public void setTradeText(String tradeText) {
		this.tradeText = tradeText;
	}

	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getOutUserId(){
		return this.outUserId;
	}
	
	public void setOutUserId(String outUserId){
		this.outUserId = outUserId;
	}

	public String getOrderNo(){
		return this.orderNo;
	}
	
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}

	public String getTradeName(){
		return this.tradeName;
	}
	
	public void setTradeName(String tradeName){
		this.tradeName = tradeName;
	}

	public Date getTradeTime(){
		return this.tradeTime;
	}
	
	public void setTradeTime(Date tradeTime){
		this.tradeTime = tradeTime;
	}

	public Date getFinishTime(){
		return this.finishTime;
	}
	
	public void setFinishTime(Date finishTime){
		this.finishTime = finishTime;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public OrderTradeTypeEnum getOrderTradeType(){
		return this.orderTradeType;
	}
	
	public void setOrderTradeType(OrderTradeTypeEnum orderTradeType){
		this.orderTradeType = orderTradeType;
	}

	public OrderSubTradeTypeEnum getOrderSubTradeType(){
		return this.orderSubTradeType;
	}
	
	public void setOrderSubTradeType(OrderSubTradeTypeEnum orderSubTradeType){
		this.orderSubTradeType = orderSubTradeType;
	}

	public OrderStatusEnum getOrderStatus(){
		return this.orderStatus;
	}
	
	public void setOrderStatus(OrderStatusEnum orderStatus){
		this.orderStatus = orderStatus;
	}

	public String getAliScanUrl() {
		return aliScanUrl;
	}

	public void setAliScanUrl(String aliScanUrl) {
		this.aliScanUrl = aliScanUrl;
	}

	public String getWeiScanUrl() {
		return weiScanUrl;
	}

	public void setWeiScanUrl(String weiScanUrl) {
		this.weiScanUrl = weiScanUrl;
	}

	public String getOrderTradeNo(){
		return orderTradeNo;
	}

	public void setOrderTradeNo(String orderTradeNo){
		this.orderTradeNo = orderTradeNo;
	}
}
