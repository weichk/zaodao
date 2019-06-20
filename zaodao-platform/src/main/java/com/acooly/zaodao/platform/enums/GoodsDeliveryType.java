package com.acooly.zaodao.platform.enums;

import com.acooly.zaodao.web.manage.common.CreditConstants;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 商品类型
 * 
 * @author zhangpu
 * 
 */
public enum GoodsDeliveryType {

	Physical(CreditConstants.Physical, "实物"),

	Virtual(CreditConstants.Virtual, "虚拟商品"),
	;

	private int code;
	private String message;

	private GoodsDeliveryType(int code, String message) {
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
		for (GoodsDeliveryType type : values()) {
			map.put(type.getCode(), type.getMessage());
		}
		return map;
	}

}
