package com.acooly.zaodao.account.enums;

import java.util.Map;

import com.acooly.core.utils.enums.Messageable;
import com.google.common.collect.Maps;

/**
 * 账户状态枚举
 */
public enum AccountStatusEnum implements Messageable{
	
	ENABLE("ENABLE", "有效"),
	LOCKED("LOCKED", "锁定"),
	DISABLE("DISABLE", "无效");
	
	/** 枚举值 */
	private final String code;
	
	/** 枚举描述 */
	private final String message;
	
	/**
	 *
	 * @param code 枚举值
	 * @param message 枚举描述
	 */
	private AccountStatusEnum(String code, String message) {
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
	public static AccountStatusEnum getByCode(String code) {
		for (AccountStatusEnum _enum : values()) {
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
	public static java.util.List<AccountStatusEnum> getAllEnum() {
		java.util.List<AccountStatusEnum> list = new java.util.ArrayList<AccountStatusEnum>(values().length);
		for (AccountStatusEnum _enum : values()) {
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
		for (AccountStatusEnum _enum : values()) {
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
		AccountStatusEnum _enum = getByCode(code);
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
	public static String getCode(AccountStatusEnum _enum) {
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
		for (AccountStatusEnum type : getAllEnum()) {
			map.put(type.getCode(), type.getMessage());
		}
		return map;
	}
}

    