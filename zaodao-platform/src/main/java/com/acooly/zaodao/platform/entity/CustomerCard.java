/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-31
*/
package com.acooly.zaodao.platform.entity;


import com.acooly.core.common.domain.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 * 用户绑卡信息表 Entity
 *
 * @author zhike
 * Date: 2017-05-31 23:19:27
 */
@Getter
@Setter
@Entity
@Table(name = "zd_customer_card")
public class CustomerCard extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 用户ID */
	@Size(max=64)
    private String userId;

	/** 用户手机号码 */
	@Size(max=32)
    private String mobileNo;

	/** 银行卡号 */
	@Size(max=128)
    private String cardNo;

	/**
	 * 绑卡id(签约流水号)
	 */
	@Size(max=64)
    private String bindId;

	/** 绑卡中文名称 */
	@Size(max=128)
    private String cardName;

	/**
	 * 银行Code
	 */
	@Size(max=64)
	private String bankCode;

	@Transient
	private String showCardNo;
}
