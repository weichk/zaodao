/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-29
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 用户视频表 Entity
 *
 * @author zhike
 * Date: 2017-06-29 12:16:54
 */
@Entity
@Table(name = "zd_customer_video")
public class CustomerVideo extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 用户ID */
	@Size(max=64)
    private String userId;

	/** 视频封面 */
	@Size(max=128)
    private String cover;

	/** 视频名称 */
	@Size(max=128)
    private String videoName;

	/** 用户视频 */
	@Size(max=128)
    private String cusVideo;



	


	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getCover(){
		return this.cover;
	}
	
	public void setCover(String cover){
		this.cover = cover;
	}

	public String getVideoName(){
		return this.videoName;
	}
	
	public void setVideoName(String videoName){
		this.videoName = videoName;
	}

	public String getCusVideo(){
		return this.cusVideo;
	}
	
	public void setCusVideo(String cusVideo){
		this.cusVideo = cusVideo;
	}



}
