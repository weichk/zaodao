package com.acooly.zaodao.platform.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 订单状态
 * 
 * @author zhangpu
 *
 */
public enum ShopOrderStatus {

	Ordered(1, "已下订"),

	Paying(5, "兑换中"),

	Paid(10, "已兑换"),

	Delivering(15, "已发货"),

	Received(20, "已收货"),

	Fail(0,"失败");

	private int code;
	private String message;

	private ShopOrderStatus(int code, String message) {
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
		for (ShopOrderStatus type : values()) {
			map.put(type.getCode(), type.getMessage());
		}
		return map;
	}

}
