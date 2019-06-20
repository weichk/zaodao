/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-26
*/
package com.acooly.zaodao.platform.entity;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.acooly.zaodao.platform.enums.OfileType;
import lombok.Data;

import javax.validation.constraints.*;

import com.acooly.core.common.domain.AbstractEntity;

/**
 * zd_travel_resource Entity
 *
 * @author zhike
 * Date: 2017-10-26 19:16:51
 */
@Entity
@Data
@Table(name = "zd_travel_resource")
public class TravelResource extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 旅声内容ID */
	@NotNull
	private Long travelVoiceId;

	/** 资源名称 */
	@NotNull
	private Long ofileId;

	/**
	 * 资源类型 0-其他 1-图片 2-视频
	 */
	@Enumerated(EnumType.STRING)
	private OfileType ofileType;

	/** 封面 */
	@Size(max=255)
	private String coverUrl;

	/** resource_url */
	@Size(max=255)
	private String resourceUrl;

	/** 发布用户ID */
	@Size(max=64)
	private String userId;
}
