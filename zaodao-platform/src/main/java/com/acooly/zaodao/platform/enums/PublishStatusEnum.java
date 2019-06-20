/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-01
 *
 */
package com.acooly.zaodao.platform.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户消息表 MessageType 枚举定义
 * 
 * @author zhike Date: 2017-06-01 15:49:31
 */
public enum PublishStatusEnum implements Messageable {

	unPublished("unPublished", "未发布"),

	published("published", "已发布"),

	all("all", "全部"),

	;

	private final String code;
	private final String message;

	private PublishStatusEnum(String code, String message) {
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
		for (PublishStatusEnum type : values()) {
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
	public static PublishStatusEnum find(String code) {
		for (PublishStatusEnum status : values()) {
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
	public static List<PublishStatusEnum> getAll() {
		List<PublishStatusEnum> list = new ArrayList<PublishStatusEnum>();
		for (PublishStatusEnum status : values()) {
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
		for (PublishStatusEnum status : values()) {
			list.add(status.code());
		}
		return list;
	}

}
