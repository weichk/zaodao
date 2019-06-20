/*
* zhike@yiji.com Inc.
* Copyright (c) 2018 All Rights Reserved.
* create by zhike
* date:2018-05-04
*/
package com.acooly.zaodao.web.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.TravelAgency;
import com.acooly.zaodao.platform.service.manage.TravelAgencyService;

/**
 * 旅行社 管理控制器
 * 
 * @author zhike
 * Date: 2018-05-04 16:34:01
 */
@Controller
@RequestMapping(value = "/manage/customer/travelAgency")
public class TravelAgencyManagerController extends AbstractJQueryEntityController<TravelAgency, TravelAgencyService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private TravelAgencyService travelAgencyService;

	

}
