/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-09-22
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.zaodao.platform.enums.RecordStatusEnum;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * zd_course_record Entity
 *
 * @author zhike
 * Date: 2017-09-22 15:25:10
 */
@Entity
@Table(name = "zd_course_record")
public class CourseRecord extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 会员唯一标识 */
	@NotEmpty
	@Size(max=64)
    private String userId;

	/** course_id */
    private Long courseId;

	/** 音频状态: */
    @Enumerated(EnumType.STRING)
	@NotNull
    private RecordStatusEnum recordStatus;

	/** 音频标题 */
	@NotEmpty
	@Size(max=255)
    private String recordTitle;

	/** 音频地址 */
	@NotEmpty
	@Size(max=1024)
    private String recordUrl;

	/**
	 * 音频时长
	 */
	private Long recordTime;


	


	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public Long getCourseId(){
		return this.courseId;
	}
	
	public void setCourseId(Long courseId){
		this.courseId = courseId;
	}

	public RecordStatusEnum getRecordStatus(){
		return this.recordStatus;
	}
	
	public void setRecordStatus(RecordStatusEnum recordStatus){
		this.recordStatus = recordStatus;
	}

	public String getRecordTitle(){
		return this.recordTitle;
	}
	
	public void setRecordTitle(String recordTitle){
		this.recordTitle = recordTitle;
	}

	public String getRecordUrl(){
		return this.recordUrl;
	}
	
	public void setRecordUrl(String recordUrl){
		this.recordUrl = recordUrl;
	}

	public Long getRecordTime(){
		return this.recordTime;
	}

	public void setRecordTime(Long recordTime){
		this.recordTime = recordTime;
	}

}
