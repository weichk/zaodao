package com.acooly.zaodao.platform.dto;

import com.acooly.zaodao.common.utils.MaskStrUtils;
import com.acooly.zaodao.platform.entity.TourGuide;
import lombok.Data;

/**
 * Created by Administrator on 2017/5/29.
 */
@Data
public class TourGuideDto extends TourGuide {

	/**
	 * 账户名
	 */
	private String userName;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 登录密码
	 */
	private String loginPassword;

	/**
	 * 交易密码
	 */
	private String payPassword;

	/**
	 * 登录密码加密填充值
	 */
	private String loginSail;

	/**
	 * 交易密码加密填充值
	 */
	private String paySail;

	/**
	 * 用户头像
	 */
	private String headImg;

	/**
	 * 是否为导游
	 */
	private Integer isTourGuide = 0;

	/**
	 * 电子邮件
	 */
	private String email;

	/**
	 * 性别
	 */
	private Integer sex = 1;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 联系地址
	 */
	private String contactAddress;

	/**
	 * 手机号码
	 */
	private String mobileNo;

	private String maskMobileNo;

	public String getMaskMobileNo() {
		return MaskStrUtils.maskStrBychar(getMobileNo(), "*", 3, 7);
	}
}
