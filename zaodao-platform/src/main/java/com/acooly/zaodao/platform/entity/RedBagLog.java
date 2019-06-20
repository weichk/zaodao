/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-07-18
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 红包领取记录表 Entity
 *
 * @author zhike
 * Date: 2017-07-18 09:42:32
 */
@Entity
@Table(name = "zd_red_bag_log")
public class RedBagLog extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 用户ID */
	@NotEmpty
	@Size(max=64)
    private String userId;

	/** 业务订单号 */
	@NotEmpty
	@Size(max=64)
    private String orderNo;

	/** 关联id */
	@NotNull
    private Long refId;

	/** 红包类型 */
	@NotEmpty
	@Size(max=32)
    private String redBagType;

	/** 领取金额 */
	@NotNull
    private Long totalAmount;



	


	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getOrderNo(){
		return this.orderNo;
	}
	
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}

	public Long getRefId(){
		return this.refId;
	}
	
	public void setRefId(Long refId){
		this.refId = refId;
	}

	public String getRedBagType(){
		return this.redBagType;
	}
	
	public void setRedBagType(String redBagType){
		this.redBagType = redBagType;
	}

	public Long getTotalAmount(){
		return this.totalAmount;
	}
	
	public void setTotalAmount(Long totalAmount){
		this.totalAmount = totalAmount;
	}



}
