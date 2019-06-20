/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-31
*/
package com.acooly.zaodao.platform.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import com.acooly.core.common.domain.AbstractEntity;
import java.util.Date;

/**
 * zd_course_review Entity
 *
 * @author zhike
 * Date: 2017-10-31 11:09:26
 */
@Entity
@Table(name = "zd_course_review")
public class CourseReview extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 课程ID */
	@NotNull
    private Long courseId;

	/** 发布用户ID */
	@Size(max=64)
    private String userId;

	/** 评论父级ID */
    private Long reviewParentId;

	/** 评论内容 */
	@Size(max=0)
    private String content;

	/** 备注 */
	@Size(max=1000)
    private String remark;



	


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

	public Long getReviewParentId(){
		return this.reviewParentId;
	}
	
	public void setReviewParentId(Long reviewParentId){
		this.reviewParentId = reviewParentId;
	}

	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}

	public String getRemark(){
		return this.remark;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}



}
