/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-24
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 导游评论打分表 Entity
 *
 * @author zhike
 * Date: 2017-05-24 22:43:35
 */
@Entity
@Table(name = "zd_tour_grade")
public class TourGrade extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 用户ID */
    private String userId;

	/** 被评论导游ID */
	private String guideUserId;

	/** 电话号码 */
	@Size(max=32)
    private String mobileNo;

	/** 评价分数 */
	@NotNull
    private Integer starCount = 0;

	/** 评价内容 */
	@Size(max=0)
    private String content;

}
