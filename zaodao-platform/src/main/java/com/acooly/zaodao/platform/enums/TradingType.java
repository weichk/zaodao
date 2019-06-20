/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-12
 *
 */
package com.acooly.zaodao.platform.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易记录 TradingType 枚举定义
 * 
 * @author zhike
 * Date: 2017-06-12 22:57:24
 */
public enum TradingType implements Messageable {

	travel("travel", "旅游"),

	deposit("deposit", "充值"),

	withdraw("withdraw", "提现"),

	catrade("catrade", "钙片交易"),

	close_order("close_order", "取消订单"),

	close_fee("close_fee", "取消订单服务费"),

	plat_service("plat_service", "平台服务费"),

	guide_fee("guide_fee", "约导收入"),

	guide_close_order("guide_close_order", "导游取消订单"),
	;

	private final String code;
	private final String message;

	private TradingType(String code, String message) {
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
		for (TradingType type : values()) {
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
	public static TradingType find(String code) {
		for (TradingType status : values()) {
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
	public static List<TradingType> getAll() {
		List<TradingType> list = new ArrayList<TradingType>();
		for (TradingType status : values()) {
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
		for (TradingType status : values()) {
			list.add(status.code());
		}
		return list;
	}

}
