package com.acooly.zaodao.account.enums;

import java.util.Map;

import com.acooly.core.utils.enums.Messageable;
import com.google.common.collect.Maps;

/**
 * 订单状态枚举
 */
public enum AccountOrderStatusEnum implements Messageable{
	
	INIT("INIT", "初始"),
	SUCCESS("SUCCESS", "成功"),
	FAIL("FAIL", "失败"),
	CANCEL("CANCEL", "撤销");
	
	/** 枚举值 */
	private final String code;
	
	/** 枚举描述 */
	private final String message;
	
	/**
	 *
	 * @param code 枚举值
	 * @param message 枚举描述
	 */
	private AccountOrderStatusEnum(String code, String message) {
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
	public static AccountOrderStatusEnum getByCode(String code) {
		for (AccountOrderStatusEnum _enum : values()) {
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
	public static java.util.List<AccountOrderStatusEnum> getAllEnum() {
		java.util.List<AccountOrderStatusEnum> list = new java.util.ArrayList<AccountOrderStatusEnum>(values().length);
		for (AccountOrderStatusEnum _enum : values()) {
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
		for (AccountOrderStatusEnum _enum : values()) {
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
		AccountOrderStatusEnum _enum = getByCode(code);
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
	public static String getCode(AccountOrderStatusEnum _enum) {
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
		for (AccountOrderStatusEnum type : getAllEnum()) {
			map.put(type.getCode(), type.getMessage());
		}
		return map;
	}
}

    