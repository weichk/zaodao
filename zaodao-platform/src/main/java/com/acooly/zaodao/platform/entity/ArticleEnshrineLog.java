/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-08-13
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 帖子收藏记录表 Entity
 *
 * @author zhike
 * Date: 2017-08-13 15:33:36
 */
@Entity
@Table(name = "zd_article_enshrine_log")
public class ArticleEnshrineLog extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 用户ID */
	@Size(max=64)
    private String userId;

	/** 帖子ID */
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
