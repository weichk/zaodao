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
public enum CourseStatusEnum implements Messageable {

	draft("draft", "草稿"),

	review("review", "待审核"),

	published("published", "已发布"),

	deleted("deleted", "已删除"),

	enroll("enroll", "报名中"),

	endline("endline", "报名截止"),

	finish("finish", "已完成")

	;

	private final String code;
	private final String message;

	private CourseStatusEnum(String code, String message) {
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
		for (CourseStatusEnum type : values()) {
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
	public static CourseStatusEnum find(String code) {
		for (CourseStatusEnum status : values()) {
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
	public static List<CourseStatusEnum> getAll() {
		List<CourseStatusEnum> list = new ArrayList<CourseStatusEnum>();
		for (CourseStatusEnum status : values()) {
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
		for (CourseStatusEnum status : values()) {
			list.add(status.code());
		}
		return list;
	}

}
