package com.acooly.zaodao.gateway.gsy.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * 用户信息
 */
@Getter
@Setter
public class CustomerExtendDto implements Serializable{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -5327425336320065646L;
	
	@OpenApiField(desc = "平台名称", constraint = "平台名称")
	private String platName;
	
	@OpenApiField(desc = "平台URL", constraint = "平台URL")
	private String platUrl;
	
	@OpenApiField(desc = "平台IP", constraint = "平台IP")
	private String platIp;
	
}
