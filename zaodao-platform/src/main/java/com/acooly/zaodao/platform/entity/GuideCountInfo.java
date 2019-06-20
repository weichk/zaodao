/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-30
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 导游订单统计表 Entity
 *
 * @author zhike
 * Date: 2017-05-30 11:07:30
 */
@Entity
@Table(name = "zd_guide_count_info")
public class GuideCountInfo extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 用户id */
	@Size(max=64)
    private String userId;

	/** 累积订单个数 */
	@NotNull
    private Long orderCount;

	/** 累积订单金额 */
	@NotNull
    private Long amountCount;


	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public Long getOrderCount(){
		return this.orderCount;
	}
	
	public void setOrderCount(Long orderCount){
		this.orderCount = orderCount;
	}

	public Long getAmountCount(){
		return this.amountCount;
	}
	
	public void setAmountCount(Long amountCount){
		this.amountCount = amountCount;
	}

}
