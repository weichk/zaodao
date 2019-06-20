/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-18
*/
package com.acooly.zaodao.platform.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import com.acooly.core.common.domain.AbstractEntity;
import java.util.Date;

/**
 * 密保问题表 Entity
 *
 * @author zhike
 * Date: 2017-05-18 16:21:58
 */
@Entity
@Table(name = "zd_secret")
public class Secret extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 问题内容 */
	@Size(max=128)
    private String content;



	


	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}



}
