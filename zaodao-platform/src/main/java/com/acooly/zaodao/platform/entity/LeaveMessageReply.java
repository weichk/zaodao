/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-21
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 留言回复表 Entity
 *
 * @author zhike
 * Date: 2017-06-21 16:42:50
 */
@Entity
@Table(name = "zd_leave_message_reply")
public class LeaveMessageReply extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 留言id */
	@NotNull
    private Long messageId;

	/** 回复内容 */
	@Size(max=0)
    private String replyContent;

	/** 回复用户id */
	@NotEmpty
	@Size(max=64)
    private String replyUserId;



	


	public Long getMessageId(){
		return this.messageId;
	}
	
	public void setMessageId(Long messageId){
		this.messageId = messageId;
	}

	public String getReplyContent(){
		return this.replyContent;
	}
	
	public void setReplyContent(String replyContent){
		this.replyContent = replyContent;
	}

	public String getReplyUserId(){
		return this.replyUserId;
	}
	
	public void setReplyUserId(String replyUserId){
		this.replyUserId = replyUserId;
	}



}
