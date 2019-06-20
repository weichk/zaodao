/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-02
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.zaodao.platform.enums.CustomerMessageType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 用户系统消息表 Entity
 *
 * @author zhike
 * Date: 2017-06-02 14:31:00
 */
@Getter
@Setter
@Entity
@Table(name = "zd_customer_message")
public class CustomerMessage extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 用户ID */
	@NotEmpty
	@Size(max=64)
    private String userId;

	/** 消息标题 */
	@NotEmpty
	@Size(max=64)
    private String messageTitle;

	/** 消息内容 */
	@Size(max=512)
    private String message;

	/** 查看状态 */
	@NotNull
    private Integer readStatus = 0;

	/**
	 * 消息类型
	 */
	@Enumerated(EnumType.STRING)
	private CustomerMessageType messageType;

	/**
	 * 订单号
	 */
	@Size(max=64)
	private String orderNo;
}
