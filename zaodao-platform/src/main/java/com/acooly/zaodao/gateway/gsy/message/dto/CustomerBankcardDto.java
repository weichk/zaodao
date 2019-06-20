package com.acooly.zaodao.gateway.gsy.message.dto;

import com.acooly.core.utils.Money;
import com.acooly.zaodao.gateway.gsy.message.enums.CardTypeEnum;
import com.acooly.zaodao.gateway.gsy.message.enums.PublicTagEnum;
import com.acooly.zaodao.gateway.gsy.message.enums.Purpose;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 会员绑卡信息
 */
@Getter
@Setter
public class CustomerBankcardDto implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -770050453515306335L;

	/**
	 *会员userId
	 */
	private String userId;

	/**
	 *银行绑定手机号
	 */
	private String mobileNo;

	/**
	 *真实姓名
	 */
	private String realName;

	/**
	 *银行code
	 */
	private String bankCode;

	/**
	 *银行名称
	 */
	private String bankName;

	/**
	 *银行卡号
	 */
	private String bankCardNo;

	/**
	 *银行图标
	 */
	private String bankLogo;

	/**
	 *卡种
	 */
	private CardTypeEnum bankCardType;

	/**
	 *卡类型
	 */
	private PublicTagEnum publicTag;

	/**
	 *开户省
	 */
	private String province;

	/**
	 *开户市d
	 */
	private String city;

	/**
	 *绑卡ID
	 */
	private String bindId;

	/**
	 *绑卡用途
	 */
	private Purpose purpose;

	/**
	 *提现单笔限额
	 */
	private Money quota;

	/**
	 *提现日限额
	 */
	private Money dayQuota;
}
