/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-07-04
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.TourGuide;
import com.acooly.zaodao.platform.enums.*;
import com.acooly.zaodao.platform.service.manage.TourGuideService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 导游信息表 管理控制器
 * 
 * @author zhike
 * Date: 2017-07-04 10:18:25
 */
@Controller
@RequestMapping(value = "/manage/customer/tourGuide")
public class TourGuideManagerController extends AbstractJQueryEntityController<TourGuide, TourGuideService> {
	

	{
		allowMapping = "*";
	}

	private static Map<Integer, String> allIsHotStatus = Maps.newLinkedHashMap();
	static {
		allIsHotStatus.put(1, "是");
		allIsHotStatus.put(0, "否");
	}

	private static Map<Integer, String> starLevel = Maps.newLinkedHashMap();
	static {
		starLevel.put(0, "零颗星");
		starLevel.put(1, "一颗星");
		starLevel.put(2, "两颗星");
		starLevel.put(3, "三颗星");
		starLevel.put(4, "四颗星");
		starLevel.put(5, "五颗星");
	}

	@SuppressWarnings("unused")
	@Autowired
	private TourGuideService tourGuideService;

	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allTourRanks", TourRank.mapping());
		model.put("allIsHotStatus", allIsHotStatus);
		model.put("starLevel", starLevel);
		model.put("allGuideLevels", GuideLevel.mapping());
		model.put("allGovReceptCounts", GovReceptCountType.mapping());
		model.put("allBusReceptCounts", BusReceptCountType.mapping());
	}

}
