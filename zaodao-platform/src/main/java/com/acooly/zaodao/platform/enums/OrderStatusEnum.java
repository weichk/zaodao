/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-08-09
 *
 */
package com.acooly.zaodao.platform.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易订单表 OrderStatusEnum 枚举定义
 * 
 * @author zhike
 * Date: 2017-08-09 11:10:35
 */
public enum OrderStatusEnum implements Messageable {

	pay("pay", "已支付"),

	noPay("noPay", "未支付"),

	processing("processing", "支付中"),

	fail("fail", "支付失败"),

	close("close", "已关闭"),

	refund("refund", "退款"),

	delete("delete", "已删除"),

	;

	private final String code;
	private final String message;

	private OrderStatusEnum(String code, String message) {
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
		for (OrderStatusEnum type : values()) {
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
	public static OrderStatusEnum find(String code) {
		for (OrderStatusEnum status : values()) {
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
	public static List<OrderStatusEnum> getAll() {
		List<OrderStatusEnum> list = new ArrayList<OrderStatusEnum>();
		for (OrderStatusEnum status : values()) {
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
		for (OrderStatusEnum status : values()) {
			list.add(status.code());
		}
		return list;
	}

}
