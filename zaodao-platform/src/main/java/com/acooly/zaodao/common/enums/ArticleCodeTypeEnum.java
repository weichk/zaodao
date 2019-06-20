/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-05-24
 *
 */
package com.acooly.zaodao.common.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单表 PlatOrderInfoOrderStatus 枚举定义
 *
 * @author zhike Date: 2017-05-24 23:14:32
 */
public enum ArticleCodeTypeEnum implements Messageable {

	guideWord("guideWord", "guideSecret", "导游词"),

	skillsTour("skillsTour", "guideSecret", "带团技巧"),

	guideTraining("guideTraining", "guideSecret", "导游培训"),

	domesticRoutes("domesticRoutes", "experienceTour", "国内线路"),

	exitRoutes("exitRoutes", "experienceTour", "出境线路"),

	customRoute("customRoute", "experienceTour", "定制线路"),

	guideTreeHole("guideTreeHole", "guideTreeHole", "导游树洞"),

	travelStrategy("travelStrategy", "travelStrategy", "旅游攻略"),

	caseTour("caseTour", "caseAnalysis", "带团案例"),

	complaintCase("complaintCase", "caseAnalysis", "投诉案例"),

	problemSolving("problemSolving", "caseAnalysis", "问题处理"),;

	private final String code;
	private final String type;
	private final String message;

	private ArticleCodeTypeEnum(String code, String type, String message) {
		this.code = code;
		this.type = type;
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

	public String getType() {
		return type;
	}

	public static Map<String, String> mapping() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (ArticleCodeTypeEnum type : values()) {
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
	public static ArticleCodeTypeEnum find(String code) {
		for (ArticleCodeTypeEnum status : values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}

	/**
	 * 通过枚举值码查找枚举值。
	 *
	 * @param type
	 *            查找枚举值的枚举值码。
	 * @return 枚举值码对应的枚举值。
	 * @throws IllegalArgumentException
	 *             如果 code 没有对应的 Status 。
	 */
	public static ArticleCodeTypeEnum findByType(String type) {
		for (ArticleCodeTypeEnum status : values()) {
			if (status.getType().equals(type)) {
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
	public static List<ArticleCodeTypeEnum> getAll() {
		List<ArticleCodeTypeEnum> list = new ArrayList<ArticleCodeTypeEnum>();
		for (ArticleCodeTypeEnum status : values()) {
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
		for (ArticleCodeTypeEnum status : values()) {
			list.add(status.code());
		}
		return list;
	}

}
