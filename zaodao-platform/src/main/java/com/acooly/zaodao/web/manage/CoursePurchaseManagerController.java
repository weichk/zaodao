/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-12
*/
package com.acooly.zaodao.web.manage;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.acooly.zaodao.platform.service.manage.CoursePurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.CoursePurchase;

import com.google.common.collect.Maps;

/**
 * zd_course_purchase 管理控制器
 * 
 * @author zhike
 * Date: 2017-10-12 15:01:58
 */
@Controller
@RequestMapping(value = "/manage/customer/coursePurchase")
public class CoursePurchaseManagerController extends AbstractJQueryEntityController<CoursePurchase, CoursePurchaseService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private CoursePurchaseService coursePurchaseService;

	

}
