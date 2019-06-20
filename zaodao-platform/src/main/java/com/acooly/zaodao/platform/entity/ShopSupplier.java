/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-15
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 商城供货商 Entity
 *
 * @author zhike
 * Date: 2017-06-15 16:02:42
 */
@Entity
@Table(name = "zd_shop_supplier")
public class ShopSupplier extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 名称 */
	@Size(max=64)
    private String name;

	/** 手机号码 */
	@Size(max=21)
    private String mobileNo;

	/** 地址 */
	@Size(max=128)
    private String address;

	/** 操作员 */
	@Size(max=21)
    private String optUser;

	/** 状态 */
    private Integer status;

	/** 备注 */
	@Size(max=128)
    private String comments;



	


	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getMobileNo(){
		return this.mobileNo;
	}
	
	public void setMobileNo(String mobileNo){
		this.mobileNo = mobileNo;
	}

	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}

	public String getOptUser(){
		return this.optUser;
	}
	
	public void setOptUser(String optUser){
		this.optUser = optUser;
	}

	public Integer getStatus(){
		return this.status;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}

	public String getComments(){
		return this.comments;
	}
	
	public void setComments(String comments){
		this.comments = comments;
	}



}
