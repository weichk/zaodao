/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-30
*/
package com.acooly.zaodao.platform.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import com.acooly.core.common.domain.AbstractEntity;
import java.util.Date;

/**
 * zd_customer_focus Entity
 *
 * @author zhike
 * Date: 2017-10-30 15:56:37
 */
@Entity
@Table(name = "zd_customer_focus")
public class CustomerFocus extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 发布用户ID */
	@Size(max=64)
    private String userId;

	/** 关注用户ID */
	@Size(max=64)
    private String focusUserId;



	


	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getFocusUserId(){
		return this.focusUserId;
	}
	
	public void setFocusUserId(String focusUserId){
		this.focusUserId = focusUserId;
	}



}
