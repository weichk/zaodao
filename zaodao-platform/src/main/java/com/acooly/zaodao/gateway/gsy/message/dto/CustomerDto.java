package com.acooly.zaodao.gateway.gsy.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.gateway.gsy.message.enums.CustomerStatusEnum;
import com.acooly.zaodao.gateway.gsy.message.enums.CustomerTypeEnum;
import com.acooly.zaodao.gateway.gsy.message.enums.RealNameAuthEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户信息
 */
@Getter
@Setter
public class CustomerDto implements Serializable{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 686033050670769031L;

	@OpenApiField(desc = "会员userId", constraint = "会员userId")
	private String merchUserId;
	
	@OpenApiField(desc = "企业:简称,个人/个体户:姓名", constraint = "企业:简称,个人/个体户:姓名")
	private String showName;
	
	@OpenApiField(desc = "手机号", constraint = "手机号")
	private String mobileNo;
	
	@OpenApiField(desc = "email", constraint = "email")
	private String email;
	
	@OpenApiField(desc = "用户类型", constraint = "用户类型")
	private CustomerTypeEnum type;
	
	@OpenApiField(desc = "用户状态", constraint = "用户状态")
	private CustomerStatusEnum status;
	
	@OpenApiField(desc = "实名状态", constraint = "实名状态")
	private RealNameAuthEnum realNameAuth;
}
