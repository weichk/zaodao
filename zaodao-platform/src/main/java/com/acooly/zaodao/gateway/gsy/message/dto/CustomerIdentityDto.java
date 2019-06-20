package com.acooly.zaodao.gateway.gsy.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.gateway.gsy.message.enums.CardType;
import com.acooly.zaodao.gateway.gsy.message.enums.Gender;
import com.acooly.zaodao.gateway.gsy.message.enums.RealNameStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 会员个人实名信息
 */
@Getter
@Setter
public class CustomerIdentityDto implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -3719220943918125328L;

	@OpenApiField(desc = "真实姓名", constraint = "真实姓名")
	private String realName;
	
	@OpenApiField(desc = "性别", constraint = "性别")
	private Gender gender;
	
	@OpenApiField(desc = "民族", constraint = "民族")
	private String nation;
	
	@OpenApiField(desc = "证件类型", constraint = "证件类型")
	private CardType cardType = CardType.IDCARD;
	
	@OpenApiField(desc = "证件号", constraint = "证件号")
	private String cardNo;
	
	@OpenApiField(desc = "省", constraint = "省")
	private String province;
	
	@OpenApiField(desc = "市", constraint = "市")
	private String city;
	
	@OpenApiField(desc = "县/区", constraint = "县/区")
	private String county;
	
	@OpenApiField(desc = "证件正面图片", constraint = "证件正面图片")
	private String cardFront;
	
	@OpenApiField(desc = "证件背面图片", constraint = "证件背面图片")
	private String cardBack;
	
	@OpenApiField(desc = "状态", constraint = "状态")
	private RealNameStatus status;
	
	@OpenApiField(desc = "出生年", constraint = "出生年")
	private Integer birthYear;
	
	@OpenApiField(desc = "出生月", constraint = "出生月")
	private Integer birthMonth;
	
	@OpenApiField(desc = "出生日", constraint = "出生日")
	private Integer birthDay;
	
	@OpenApiField(desc = "地址", constraint = "地址")
	private String address;
}
