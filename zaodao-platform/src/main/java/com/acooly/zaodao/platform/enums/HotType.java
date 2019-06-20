package com.acooly.zaodao.platform.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 订单状态
 * 
 * @author zhangpu
 *
 */
public enum HotType {

	Ordered(0, "普通商品"),

	Paying(5, "热门商品");

	private int code;
	private String message;

	private HotType(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static Map<Integer, String> mapping() {
		Map<Integer, String> map = Maps.newLinkedHashMap();
		for (HotType type : values()) {
			map.put(type.getCode(), type.getMessage());
		}
		return map;
	}

}
