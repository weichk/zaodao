/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-12-11
*/
package com.acooly.zaodao.platform.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import com.acooly.core.common.domain.AbstractEntity;
import java.util.Date;

/**
 * zd_plat_order_eval Entity
 *
 * @author zhike
 * Date: 2017-12-11 17:03:29
 */
@Entity
@Table(name = "zd_plat_order_eval")
public class PlatOrderEval extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 游客ID */
	@NotEmpty
	@Size(max=64)
    private String touristId;

	/** 导游ID */
	@NotEmpty
	@Size(max=64)
    private String tourGuideId;

	/** 订单号 */
	@NotEmpty
	@Size(max=64)
    private String orderNo;

	/** 评分 */
    private Integer score;

	/** 评价内容 */
	@Size(max=0)
    private String content;



	


	public String getTouristId(){
		return this.touristId;
	}
	
	public void setTouristId(String touristId){
		this.touristId = touristId;
	}

	public String getTourGuideId(){
		return this.tourGuideId;
	}
	
	public void setTourGuideId(String tourGuideId){
		this.tourGuideId = tourGuideId;
	}

	public String getOrderNo(){
		return this.orderNo;
	}
	
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}

	public Integer getScore(){
		return this.score;
	}
	
	public void setScore(Integer score){
		this.score = score;
	}

	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}



}
