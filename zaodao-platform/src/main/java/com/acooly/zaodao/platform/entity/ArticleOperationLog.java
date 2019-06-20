/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-07-07
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.zaodao.platform.enums.Action;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 帖子操作记录表 Entity
 *
 * @author zhike
 * Date: 2017-07-07 15:24:35
 */
@Entity
@Table(name = "zd_article_operation_log")
public class ArticleOperationLog extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 操作用户ID */
	@Size(max=32)
    private String optUserId;

	/** 操作用户名 */
	@Size(max=32)
    private String optUserName;

	/** 操作用户电话 */
	@Size(max=32)
    private String optMobileNo;

	/** 操作用户姓名 */
	@NotEmpty
	@Size(max=32)
    private String optRealName;

	/** 文章ID */
    private Long articleId;

	/** 文章标题 */
	@Size(max=64)
    private String title;

	/** 执行动作 */
    @Enumerated(EnumType.STRING)
    private Action action;

	/** 操作原因 */
	@Size(max=512)
    private String reason;



	


	public String getOptUserId(){
		return this.optUserId;
	}
	
	public void setOptUserId(String optUserId){
		this.optUserId = optUserId;
	}

	public String getOptUserName(){
		return this.optUserName;
	}
	
	public void setOptUserName(String optUserName){
		this.optUserName = optUserName;
	}

	public String getOptMobileNo(){
		return this.optMobileNo;
	}
	
	public void setOptMobileNo(String optMobileNo){
		this.optMobileNo = optMobileNo;
	}

	public String getOptRealName(){
		return this.optRealName;
	}
	
	public void setOptRealName(String optRealName){
		this.optRealName = optRealName;
	}

	public Long getArticleId(){
		return this.articleId;
	}
	
	public void setArticleId(Long articleId){
		this.articleId = articleId;
	}

	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}

	public Action getAction(){
		return this.action;
	}
	
	public void setAction(Action action){
		this.action = action;
	}

	public String getReason(){
		return this.reason;
	}
	
	public void setReason(String reason){
		this.reason = reason;
	}



}
