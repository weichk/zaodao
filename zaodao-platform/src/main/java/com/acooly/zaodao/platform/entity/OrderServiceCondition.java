/*
* zhike@yiji.com Inc.
* Copyright (c) 2018 All Rights Reserved.
* create by zhike
* date:2018-05-29
*/
package com.acooly.zaodao.platform.entity;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.acooly.zaodao.platform.enums.ServiceConditionName;
import com.acooly.zaodao.platform.enums.ServiceConditionSymbol;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import com.acooly.core.common.domain.AbstractEntity;
import java.util.Date;

/**
 * zd_order_service_condition Entity
 *
 * @author zhike
 * Date: 2018-05-29 11:22:47
 */
@Getter
@Setter
@Entity
@Table(name = "zd_order_service_condition")
public class OrderServiceCondition extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	/** 服务费ID */
	@NotNull
    private Long feeId;

	/**
	 * 条件名(订单状态)
	 */
	@Enumerated(EnumType.STRING)
	private ServiceConditionName conditionName;

	/**
	 * 条件符号(等于)
	 */
	@Enumerated(EnumType.STRING)
	private ServiceConditionSymbol conditionSymbol;

	/**
	 * 条件值(已支付)
	 */
	@Size(max=255)
	private String conditionValue;
}
