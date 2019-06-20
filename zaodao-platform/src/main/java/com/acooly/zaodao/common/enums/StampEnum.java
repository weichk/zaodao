/*
 * zhike.cn Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-02-23
 *
 */
package com.acooly.zaodao.common.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 图章定义
 *
 * @author zhike Date: 2017-02-23 21:19:12
 */
public enum StampEnum implements Messageable {
	original("original", "原创"),

	hot("hot", "热帖"),

	recent("recent", "最新"),

	anwei("anwei", "安慰"),

	biaoyang("biaoyang", "表扬"),

	chongbai("chongbai", "崇拜"),

	daqi("daqi", "大气"),

	haowan("haowan", "好玩"),

	jiayou("jiayou", "加油"),

	jianqiang("jianqiang", "坚强"),

	meitu("meitu", "美图"),

	nanshen("nanshen", "男神"),

	nvshen("nvshen", "女神"),

	wusi("wusi", "无私"),

	youxiu("youxiu", "优秀"),

	youcai("youcai", "有才"),

	zhenku("zhenku", "真酷"),

	niuqi("niuqi", "牛气"),;

	private final String code;
	private final String message;

	StampEnum(String code, String message) {
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
		for (StampEnum type : values()) {
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
	public static StampEnum find(String code) {
		for (StampEnum status : values()) {
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
	public static List<StampEnum> getAll() {
		List<StampEnum> list = new ArrayList<StampEnum>();
		for (StampEnum status : values()) {
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
		for (StampEnum status : values()) {
			list.add(status.code());
		}
		return list;
	}
}
