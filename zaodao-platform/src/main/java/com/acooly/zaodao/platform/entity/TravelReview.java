/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-26
*/
package com.acooly.zaodao.platform.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import com.acooly.core.common.domain.AbstractEntity;
import java.util.Date;

/**
 * zd_travel_review Entity
 *
 * @author zhike
 * Date: 2017-10-26 19:17:16
 */
@Entity
@Data
@Table(name = "zd_travel_review")
public class TravelReview extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 旅声内容ID */
	@NotNull
    private Long travelVoiceId;

	/** 发布用户ID */
	@Size(max=64)
    private String userId;

	/** 评论父级ID */
    private Integer reviewParentId;

	/** 评论内容 */
	@Size(max=0)
    private String content;

	/** 备注 */
	@Size(max=1000)
    private String remark;

}
