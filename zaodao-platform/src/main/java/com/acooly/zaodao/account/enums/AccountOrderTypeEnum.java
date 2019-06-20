package com.acooly.zaodao.account.enums;

import java.util.Map;

import com.acooly.core.utils.enums.Messageable;
import com.google.common.collect.Maps;

/**
 * 订单操作类型枚举
 */
public enum AccountOrderTypeEnum implements Messageable{
	
	CREATE("CREATE", "开户"),
	CANCEL("CANCEL", "销户"),
	FREEZE("FREEZE", "冻结"),
	UNFREEZE("UNFREEZE", "解冻"),
	
	DEPOSIT("DEPOSIT", "充值"),
	WITHDRAW("WITHDRAW", "提现"),
	TRANSFER("TRANSFER", "转账"),
	
	DEPOSIT_BACK("DEPOSIT_BACK", "充退"),
	TRANSFER_BACK("TRANSFER_BACK", "转账撤销"),
	WITHDRAW_BACK("WITHDRAW_BACK", "提现撤销"),
	
	BATCH_MIX("BATCH_MIX", "混合处理"),;
	
	/** 枚举值 */
	private final String code;
	
	/** 枚举描述 */
	private final String message;
	
	/**
	 *
	 * @param code 枚举值
	 * @param message 枚举描述
	 */
	private AccountOrderTypeEnum(String code, String message) {
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
	public static AccountOrderTypeEnum getByCode(String code) {
		for (AccountOrderTypeEnum _enum : values()) {
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
	public static java.util.List<AccountOrderTypeEnum> getAllEnum() {
		java.util.List<AccountOrderTypeEnum> list = new java.util.ArrayList<AccountOrderTypeEnum>(values().length);
		for (AccountOrderTypeEnum _enum : values()) {
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
		for (AccountOrderTypeEnum _enum : values()) {
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
		AccountOrderTypeEnum _enum = getByCode(code);
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
	public static String getCode(AccountOrderTypeEnum _enum) {
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
		for (AccountOrderTypeEnum type : getAllEnum()) {
			map.put(type.getCode(), type.getMessage());
		}
		return map;
	}
}

    