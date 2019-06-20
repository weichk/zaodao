package com.acooly.zaodao.account.enums;

import java.util.Map;

import com.acooly.core.utils.enums.Messageable;
import com.google.common.collect.Maps;

/**
 * 账务错误码
 */
public enum AccountResultCodeEnum implements Messageable{
	
	ACCOUNT_NOT_EXIST("ACCOUNT_NOT_EXIST", "账户不存在"),
	ACCOUNT_INFO_ERROR("ACCOUNT_INFO_ERROR", "账户信息不符"),
	ACCOUNT_BALANCE_NOT_ENOUGH("ACCOUNT_BALANCE_NOT_ENOUGH", "账户可用余额不足"),
	ACCOUNT_FREEZE_BALANCE_NOT_ENOUGH("ACCOUNT_FREEZE_BALANCE_NOT_ENOUGH", "账户冻结余额不足"),
	ORDERNO_NOT_EXISTS("ORDERNO_NOT_EXISTS", "订单不存在"),
	;
	
	/** 枚举值 */
	private final String code;
	
	/** 枚举描述 */
	private final String message;
	
	/**
	 *
	 * @param code 枚举值
	 * @param message 枚举描述
	 */
	private AccountResultCodeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @return Returns the code.
	 */
	public String code() {
		return code;
	}
	
	/**
	 * @return Returns the message.
	 */
	public String message() {
		return message;
	}
	
	/**
	 * 通过枚举<code>code</code>获得枚举
	 *
	 * @param code
	 * @return AuthGradeEnum
	 */
	public static AccountResultCodeEnum getByCode(String code) {
		for (AccountResultCodeEnum _enum : values()) {
			if (_enum.getCode().equals(code)) {
				return _enum;
			}
		}
		return null;
	}
	
	/**
	 * 获取全部枚举
	 *
	 * @return List<AuthGradeEnum>
	 */
	public static java.util.List<AccountResultCodeEnum> getAllEnum() {
		java.util.List<AccountResultCodeEnum> list = new java.util.ArrayList<AccountResultCodeEnum>(values().length);
		for (AccountResultCodeEnum _enum : values()) {
			list.add(_enum);
		}
		return list;
	}
	
	/**
	 * 获取全部枚举值
	 *
	 * @return List<String>
	 */
	public static java.util.List<String> getAllEnumCode() {
		java.util.List<String> list = new java.util.ArrayList<String>(values().length);
		for (AccountResultCodeEnum _enum : values()) {
			list.add(_enum.code());
		}
		return list;
	}
	
	/**
	 * 通过code获取msg
	 * @param code 枚举值
	 * @return
	 */
	public static String getMsgByCode(String code) {
		if (code == null) {
			return null;
		}
		AccountResultCodeEnum _enum = getByCode(code);
		if (_enum == null) {
			return null;
		}
		return _enum.getMessage();
	}
	
	/**
	 * 获取枚举code
	 * @param _enum
	 * @return
	 */
	public static String getCode(AccountResultCodeEnum _enum) {
		if (_enum == null) {
			return null;
		}
		return _enum.getCode();
	}
	
	/**
	 * 实名登记map
	 * @return
	 */
	public static Map<String, String> maps() {
		Map<String, String> map = Maps.newLinkedHashMap();
		for (AccountResultCodeEnum type : getAllEnum()) {
			map.put(type.getCode(), type.getMessage());
		}
		return map;
	}
}

    