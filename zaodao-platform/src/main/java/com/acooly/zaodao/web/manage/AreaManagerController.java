/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-07-03
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.Area;
import com.acooly.zaodao.platform.service.manage.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 地区表 管理控制器
 * 
 * @author zhike
 * Date: 2017-07-03 17:33:07
 */
@Controller
@RequestMapping(value = "/manage/area")
public class AreaManagerController extends AbstractJQueryEntityController<Area, AreaService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private AreaService areaService;

	

}
