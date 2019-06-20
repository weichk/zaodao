/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-26
*/
package com.acooly.zaodao.web.manage;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.service.manage.TravelResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.zaodao.platform.entity.TravelResource;

import com.google.common.collect.Maps;

/**
 * zd_travel_resource 管理控制器
 * 
 * @author zhike
 * Date: 2017-10-26 19:16:50
 */
@Controller
@RequestMapping(value = "/manage/customer/travelResource")
public class TravelResourceManagerController extends AbstractJQueryEntityController<TravelResource, TravelResourceService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private TravelResourceService travelResourceService;

	

}
