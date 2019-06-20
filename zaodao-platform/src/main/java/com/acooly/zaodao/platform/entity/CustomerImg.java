/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-07
*/
package com.acooly.zaodao.platform.entity;

import com.acooly.core.common.domain.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 用户照片表 Entity
 *
 * @author zhike Date: 2017-06-07 11:37:17
 */
@Data
@Entity
@Table(name = "zd_customer_img")
public class CustomerImg extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 相册ID */
	private Long albumId;

	/** 用户照片 */
	@Size(max = 0)
	private String cusPicture;

	/** 缩略图 */
	private String thumbPic;

}
