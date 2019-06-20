/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2018 All Rights Reserved.
 * create by zhike
 * date:2018-04-10
 *
 */
package com.acooly.zaodao.platform.enums;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.acooly.core.utils.enums.Messageable;

/**
 * act_account_change ChangeTypeEnum 枚举定义
 * 
 * @author zhike
 * Date: 2018-04-10 11:25:49
 */
public enum ChangeTypeEnum implements Messageable {

	UP("UP", "上账"),

	DOWN("DOWN", "下载"),

	TRANSFER("TRANSFER", "转账"),

	KEEP("KEEP", "不变"),

	;

	private final String code;
	private final String message;

	private ChangeTypeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String code() {
		return code;
	}

	public String message() {
		return message;
	}

	public static Map<String, String> mapping() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (ChangeTypeEnum type : values()) {
			map.put(type.getCode(), type.getMessage());
		}
		return map;
	}

	/**
	 * 通过枚举值码查找枚举值。
	 * 
	 * @param code
	 *            查找枚举值的枚举值码。
	 * @return 枚举值码对应的枚举值。
	 * @throws IllegalArgumentException
	 *             如果 code 没有对应的 Status 。
	 */
	public static ChangeTypeEnum find(String code) {
		for (ChangeTypeEnum status : values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}

	/**
	 * 获取全部枚举值。
	 * 
	 * @return 全部枚举值。
	 */
	public static List<ChangeTypeEnum> getAll() {
		List<ChangeTypeEnum> list = new ArrayList<ChangeTypeEnum>();
		for (ChangeTypeEnum status : values()) {
			list.add(status);
		}
		return list;
	}

	/**
	 * 获取全部枚举值码。
	 * 
	 * @return 全部枚举值码。
	 */
	public static List<String> getAllCode() {
		List<String> list = new ArrayList<String>();
		for (ChangeTypeEnum status : values()) {
			list.add(status.code());
		}
		return list;
	}

}
