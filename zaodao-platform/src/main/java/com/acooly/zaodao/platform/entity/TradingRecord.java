/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-12
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.zaodao.gateway.gsy.message.enums.TradeTypeEnum;
import com.acooly.zaodao.platform.enums.InOutType;
import com.acooly.zaodao.platform.enums.TradeBusiness;
import com.acooly.zaodao.platform.enums.TradingType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 交易记录 Entity
 *
 * @author zhike
 * Date: 2017-06-12 22:57:24
 */
@Entity
@Table(name = "zd_trading_record")
public class TradingRecord extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 用户ID */
	@Size(max=64)
    private String userid;

	/** 交易订单表ID(根据TradeBusiness来区分是订单表还是交易表) */
	@NotNull
    private Long orderId;

	/** 旅游地点 */
	@NotEmpty
	@Size(max=64)
    private String touristSite;

	/** 导游真实姓名 */
	@NotEmpty
	@Size(max=64)
    private String guideName;

	/** 交易金额 */
	@NotNull
    private Long tradingAmount = 0l;

	/** 出入金类型 */
    @Enumerated(EnumType.STRING)
    private InOutType inOutType;

	/** 余额 */
	@NotNull
    private Long balance = 0l;

	/** 交易类型 */
    @Enumerated(EnumType.STRING)
    private TradingType tradingType;

	/**
	 * 支付宝,微信,账户余额
	 */
	private String tradeMethod;
	/**
	 * 交易业务类型 约导订单 交易订单
	 */
	@Enumerated(EnumType.STRING)
	private TradeBusiness tradeBusiness;

	public TradeBusiness getTradeBusiness() {
		return tradeBusiness;
	}

	public void setTradeBusiness(TradeBusiness tradeBusiness) {
		this.tradeBusiness = tradeBusiness;
	}


	public String getTradeMethod() {
		return tradeMethod;
	}

	public void setTradeMethod(String tradeMethod) {
		this.tradeMethod = tradeMethod;
	}

	public String getUserid(){
		return this.userid;
	}
	
	public void setUserid(String userid){
		this.userid = userid;
	}

	public Long getOrderId(){
		return this.orderId;
	}
	
	public void setOrderId(Long orderId){
		this.orderId = orderId;
	}

	public String getTouristSite(){
		return this.touristSite;
	}
	
	public void setTouristSite(String touristSite){
		this.touristSite = touristSite;
	}

	public String getGuideName(){
		return this.guideName;
	}
	
	public void setGuideName(String guideName){
		this.guideName = guideName;
	}

	public Long getTradingAmount(){
		return this.tradingAmount;
	}
	
	public void setTradingAmount(Long tradingAmount){
		this.tradingAmount = tradingAmount;
	}

	public InOutType getInOutType(){
		return this.inOutType;
	}
	
	public void setInOutType(InOutType inOutType){
		this.inOutType = inOutType;
	}

	public Long getBalance(){
		return this.balance;
	}
	
	public void setBalance(Long balance){
		this.balance = balance;
	}

	public TradingType getTradingType(){
		return this.tradingType;
	}
	
	public void setTradingType(TradingType tradingType){
		this.tradingType = tradingType;
	}



}
