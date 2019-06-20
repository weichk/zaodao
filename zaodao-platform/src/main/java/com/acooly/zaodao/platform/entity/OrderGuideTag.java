/*
* zhike@yiji.com Inc.
* Copyright (c) 2018 All Rights Reserved.
* create by zhike
* date:2018-05-07
*/
package com.acooly.zaodao.platform.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import com.acooly.core.common.domain.AbstractEntity;
import java.util.Date;

/**
 * zd_order_guide_tag Entity
 *
 * @author zhike
 * Date: 2018-05-07 21:17:18
 */
@Entity
@Table(name = "zd_order_guide_tag")
public class OrderGuideTag extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;




	/** order_no */
	@Size(max=64)
    private String orderNo;

	/** 用户ID */
	@Size(max=64)
    private String userId;

	/** 导游ID */
	@Size(max=64)
    private String tourGuideId;

	/** 标签ID */
    private Long evalTagId;

	




	public String getOrderNo(){
		return this.orderNo;
	}
	
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}

	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getTourGuideId(){
		return this.tourGuideId;
	}
	
	public void setTourGuideId(String tourGuideId){
		this.tourGuideId = tourGuideId;
	}

	public Long getEvalTagId(){
		return this.evalTagId;
	}
	
	public void setEvalTagId(Long evalTagId){
		this.evalTagId = evalTagId;
	}

}
