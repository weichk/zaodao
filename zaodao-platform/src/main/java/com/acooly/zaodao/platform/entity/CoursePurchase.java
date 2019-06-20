/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-12
*/
package com.acooly.zaodao.platform.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import com.acooly.core.common.domain.AbstractEntity;
import java.util.Date;

/**
 * zd_course_purchase Entity
 *
 * @author zhike
 * Date: 2017-10-12 15:01:58
 */
@Entity
@Table(name = "zd_course_purchase")
public class CoursePurchase extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;




	/** course_id */
	@Size(max=255)
    private Long courseId;

	/** user_id */
	@Size(max=255)
    private String userId;

	




	public Long getCourseId(){
		return this.courseId;
	}
	
	public void setCourseId(Long courseId){
		this.courseId = courseId;
	}

	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

}
