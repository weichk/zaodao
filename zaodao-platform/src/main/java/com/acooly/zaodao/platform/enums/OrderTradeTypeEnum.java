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
 * 交易订单表 OrderTradeTypeEnum 枚举定义
 * 
 * @author zhike
 * Date: 2017-08-09 11:10:35
 */
public enum OrderTradeTypeEnum implements Messageable {

	deposit("deposit", "充值"),

	withdraw("withdraw", "提现"),

	catrade("catrade", "钙片交易"),

	pay("pay", "支付订单")

	;

	private final String code;
	private final String message;

	private OrderTradeTypeEnum(String code, String message) {
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
		for (OrderTradeTypeEnum type : values()) {
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
	public static OrderTradeTypeEnum find(String code) {
		for (OrderTradeTypeEnum status : values()) {
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
	public static List<OrderTradeTypeEnum> getAll() {
		List<OrderTradeTypeEnum> list = new ArrayList<OrderTradeTypeEnum>();
		for (OrderTradeTypeEnum status : values()) {
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
		for (OrderTradeTypeEnum status : values()) {
			list.add(status.code());
		}
		return list;
	}

}
