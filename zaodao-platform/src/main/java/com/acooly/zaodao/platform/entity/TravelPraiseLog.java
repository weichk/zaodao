/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-30
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
 * zd_travel_praise_log Entity
 *
 * @author zhike
 * Date: 2017-10-30 15:02:33
 */
@Data
@Entity
@Table(name = "zd_travel_praise_log")
public class TravelPraiseLog extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 旅声内容ID */
	@NotNull
    private Long travelVoiceId;

	/** 发布用户ID */
	@Size(max=64)
    private String userId;
}
