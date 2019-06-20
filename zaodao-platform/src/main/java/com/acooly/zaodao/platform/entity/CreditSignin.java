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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 积分签到记录 Entity
 *
 * @author zhike
 * Date: 2017-06-15 15:23:25
 */
@Entity
@Table(name = "zd_credit_signin")
public class CreditSignin extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 用户名 */
	@Size(max=32)
    private String username;

	/** 签到时间 */
	@NotNull
    private Date signTime;

	/** 连续签到天数 **/
	private Integer times;

	/**
	 * 当天是否签到
	 */
	@Transient
	private boolean todayCreditSignin = false;

	public String getUsername(){
		return this.username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}

	public Date getSignTime(){
		return this.signTime;
	}
	
	public void setSignTime(Date signTime){
		this.signTime = signTime;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public boolean isTodayCreditSignin() {
		return todayCreditSignin;
	}

	public void setTodayCreditSignin(boolean todayCreditSignin) {
		this.todayCreditSignin = todayCreditSignin;
	}
}
