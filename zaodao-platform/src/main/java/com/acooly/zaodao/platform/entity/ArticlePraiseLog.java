/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-07-28
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 帖子点赞记录表 Entity
 *
 * @author zhike
 * Date: 2017-07-28 17:15:40
 */
@Entity
@Table(name = "zd_article_praise_log")
public class ArticlePraiseLog extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 用户ID */
	@NotEmpty
	@Size(max=64)
    private String userId;

	/** 帖子ID */
	@NotNull
    private Long articleId;



	


	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public Long getArticleId(){
		return this.articleId;
	}
	
	public void setArticleId(Long articleId){
		this.articleId = articleId;
	}



}
