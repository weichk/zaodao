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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 用户相册表 Entity
 *
 * @author zhike Date: 2017-06-07 11:37:17
 */
@Data
@Entity
@Table(name = "zd_customer_album")
public class CustomerAlbum extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 用户ID */
	@Size(max = 64)
	private String userId;

	/** 相册名称 */
	@Size(max = 0)
	private String albumName;

	/** 相册类型 */
	@NotNull
	private Integer type = 1;

	/** 相册封面图 */
	private String coverImg;
}
