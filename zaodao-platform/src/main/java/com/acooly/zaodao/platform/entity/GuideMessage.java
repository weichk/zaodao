/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-31
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
 * zd_guide_message Entity
 *
 * @author zhike
 * Date: 2017-10-31 16:45:32
 */
@Data
@Entity
@Table(name = "zd_guide_message")
public class GuideMessage extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 发布用户ID */
	@Size(max=64)
	private String userId;
	/**
	 * 0-未读; 1-已读
	 */
	private Integer readStatus;
	/** 消息类型 */
	@Size(max=255)
	private String messageType;

	/** 消息类型名称 */
	@Size(max=255)
	private String messageName;

	/** 标题 */
	@Size(max=0)
	private String title;

	/** 消息内容ID */
	@Size(max=255)
	private String contentBomId;

	/** 内容作者ID(旅声,课程作者) */
	@Size(max=64)
	private String contentUserId;

	/** 消息内容 */
	@Size(max=0)
	private String content;
}
