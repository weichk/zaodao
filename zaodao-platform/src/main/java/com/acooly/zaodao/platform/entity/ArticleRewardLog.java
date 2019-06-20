/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-08-13
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.zaodao.platform.enums.RewardTypeEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 帖子打赏记录表 Entity
 *
 * @author zhike
 * Date: 2017-08-13 16:14:17
 */
@Entity
@Table(name = "zd_article_reward_log")
public class ArticleRewardLog extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 打赏用户ID */
	@Size(max=64)
    private String userId;

	/** 业务关联ID */
    private Long businessId;

	/** 打赏金额 */
    private Long pointAmount = 0l;

	/** 打赏类型 */
    @Enumerated(EnumType.STRING)
    private RewardTypeEnum rewardType;



	


	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public Long getBusinessId(){
		return this.businessId;
	}
	
	public void setBusinessId(Long businessId){
		this.businessId = businessId;
	}

	public Long getPointAmount(){
		return this.pointAmount;
	}
	
	public void setPointAmount(Long pointAmount){
		this.pointAmount = pointAmount;
	}

	public RewardTypeEnum getRewardType(){
		return this.rewardType;
	}
	
	public void setRewardType(RewardTypeEnum rewardType){
		this.rewardType = rewardType;
	}



}
