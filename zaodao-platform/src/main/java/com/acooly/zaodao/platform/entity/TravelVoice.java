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

import java.math.BigDecimal;
import java.util.Date;

/**
 * zd_travel_voice Entity
 *
 * @author zhike
 * Date: 2017-10-26 18:05:37
 */
@Data
@Entity
@Table(name = "zd_travel_voice")
public class TravelVoice extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 发布用户ID */
	@Size(max=64)
    private String userId;

	/** 标题 */
	@Size(max=255)
    private String title;

	/** 文章内容 */
    private String content;

	/** 点赞数 */
    private Long praiseCount = 0l;

	/** 评论数 */
    private Long reviewCount = 0l;

	/** 备注 */
	@Size(max=1000)
    private String remark;

	/** 发布位置名称 */
	@Size(max=255)
    private String positionName;

	/** 发布位置纬度 */
	@Size(max=64)
	private String positionLat;

	/** 发布位置经度 */
	@Size(max=64)
	private String positionLng;

}
