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
 * zd_course_focus Entity
 *
 * @author zhike
 * Date: 2017-10-31 11:17:56
 */
@Data
@Entity
@Table(name = "zd_course_focus")
public class CourseFocus extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 发布用户ID */
	@Size(max=64)
    private String userId;

	/** 关注课程ID */
    private Long focusCourseId;

}
