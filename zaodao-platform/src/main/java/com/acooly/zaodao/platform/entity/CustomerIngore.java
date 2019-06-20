/*
* zhike@yiji.com Inc.
* Copyright (c) 2018 All Rights Reserved.
* create by zhike
* date:2018-01-10
*/
package com.acooly.zaodao.platform.entity;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.acooly.zaodao.platform.enums.IngoreType;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import com.acooly.core.common.domain.AbstractEntity;
import java.util.Date;

/**
 * 用户屏蔽表 Entity
 *
 * @author zhike
 * Date: 2018-01-10 10:10:27
 */
@Entity
@Table(name = "zd_customer_ingore")
public class CustomerIngore extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 用户ID */
	@NotEmpty
	@Size(max=64)
    private String userId;

	/** 屏蔽人员ID */
	@Size(max=64)
    private String ingoreUserId;

	/** 屏蔽类型 */
	@Size(max=64)
	@Enumerated(EnumType.STRING)
    private IngoreType ingoreType;



	


	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getIngoreUserId(){
		return this.ingoreUserId;
	}
	
	public void setIngoreUserId(String ingoreUserId){
		this.ingoreUserId = ingoreUserId;
	}

	public IngoreType getIngoreType(){
		return this.ingoreType;
	}
	
	public void setIngoreType(IngoreType ingoreType){
		this.ingoreType = ingoreType;
	}



}
