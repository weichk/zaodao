/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-09
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 地区表 Entity
 *
 * @author zhike
 * Date: 2017-06-09 15:43:25
 */
@Entity
@Table(name = "zd_area")
public class Area extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 地区名称 */
	@Size(max=32)
    private String name;

	/** 地区编码 */
	@NotEmpty
	@Size(max=20)
    private String code;



	


	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getCode(){
		return this.code;
	}
	
	public void setCode(String code){
		this.code = code;
	}



}
