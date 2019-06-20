/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-18
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 密保答案表 Entity
 *
 * @author zhike
 * Date: 2017-05-18 16:21:58
 */
@Entity
@Table(name = "zd_secret_answer")
@Data
public class SecretAnswer extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 会员ID */
    private String userId;

	/** 密保ID */
    private Long secretId;

	/** 问题内容 */
	@Size(max=128)
    private String content;
}
