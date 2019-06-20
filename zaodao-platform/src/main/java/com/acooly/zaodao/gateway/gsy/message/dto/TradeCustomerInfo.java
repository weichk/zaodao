package com.acooly.zaodao.gateway.gsy.message.dto;

import com.acooly.core.utils.ToString;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 计费分润信息
 * 
 * @author cuifuqiang
 *
 */
@Getter
@Setter
public class TradeCustomerInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 姓名 */
	private String realName;

	/** 身份证 */
	private String identityCard;

	/** 手机号 */
	private String mobileNo;


	@Override
	public String toString() {
		return ToString.toString(this);
	}
}
