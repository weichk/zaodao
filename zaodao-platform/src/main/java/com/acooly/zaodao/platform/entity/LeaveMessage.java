/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-01
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 留言表 Entity
 *
 * @author zhike
 * Date: 2017-06-01 15:14:52
 */
@Entity
@Table(name = "zd_leave_message")
public class LeaveMessage extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 导游ID */
	@Size(max=64)
    private String userId;

	/** 留言游客ID */
	@Size(max=64)
    private String leaveId;

	/** 留言内容 */
	@Size(max=0)
    private String content;

	/** 查看状态 */
	@NotNull
    private Integer readStatus = 0;



	


	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getLeaveId(){
		return this.leaveId;
	}
	
	public void setLeaveId(String leaveId){
		this.leaveId = leaveId;
	}

	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}

	public Integer getReadStatus(){
		return this.readStatus;
	}
	
	public void setReadStatus(Integer readStatus){
		this.readStatus = readStatus;
	}



}
