/*
* zhike@yiji.com Inc.
* Copyright (c) 2018 All Rights Reserved.
* create by zhike
* date:2018-05-07
*/
package com.acooly.zaodao.platform.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import com.acooly.core.common.domain.AbstractEntity;
import java.util.Date;

/**
 * zd_order_eval_tag Entity
 *
 * @author zhike
 * Date: 2018-05-07 20:38:48
 */
@Entity
@Table(name = "zd_order_eval_tag")
public class OrderEvalTag extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;




	/** 标签内容 */
	@Size(max=255)
    private String tagContent;

	




	public String getTagContent(){
		return this.tagContent;
	}
	
	public void setTagContent(String tagContent){
		this.tagContent = tagContent;
	}

}
