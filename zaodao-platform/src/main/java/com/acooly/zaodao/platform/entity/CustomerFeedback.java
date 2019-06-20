/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-12-01
*/
package com.acooly.zaodao.platform.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import com.acooly.core.common.domain.AbstractEntity;
import java.util.Date;

/**
 * 用户反馈 Entity
 *
 * @author zhike
 * Date: 2017-12-01 10:14:19
 */
@Entity
@Table(name = "zd_customer_feedback")
public class CustomerFeedback extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 发布用户ID */
	@Size(max=64)
    private String userId;

	/** 标题 */
	@Size(max=255)
    private String title;

	/** 反馈内容 */
	@Size(max=0)
    private String content;



	


	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}

	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}



}
