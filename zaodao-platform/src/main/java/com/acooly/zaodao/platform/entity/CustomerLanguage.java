/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-01
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 导游语种表 Entity
 *
 * @author zhike
 * Date: 2017-06-01 23:58:46
 */
@Entity
@Table(name = "zd_customer_language")
public class CustomerLanguage extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 用户ID */
	@NotBlank
	@Size(max=64)
    private String userId;

	/** 语种名称 */
	@NotBlank
	@Size(max=32)
    private String languageName;

	/** 语种编码 */
	@Size(max=32)
	@NotBlank
    private String languageCode;



	


	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getLanguageName(){
		return this.languageName;
	}
	
	public void setLanguageName(String languageName){
		this.languageName = languageName;
	}

	public String getLanguageCode(){
		return this.languageCode;
	}
	
	public void setLanguageCode(String languageCode){
		this.languageCode = languageCode;
	}



}
