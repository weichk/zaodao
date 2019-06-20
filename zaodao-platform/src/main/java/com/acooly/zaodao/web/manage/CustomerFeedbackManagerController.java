/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-12-01
*/
package com.acooly.zaodao.web.manage;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.service.manage.CustomerFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.zaodao.platform.entity.CustomerFeedback;

import com.google.common.collect.Maps;

/**
 * 用户反馈 管理控制器
 * 
 * @author zhike
 * Date: 2017-12-01 10:14:19
 */
@Controller
@RequestMapping(value = "/manage/customer/customerFeedback")
public class CustomerFeedbackManagerController extends AbstractJQueryEntityController<CustomerFeedback, CustomerFeedbackService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private CustomerFeedbackService customerFeedbackService;

	

}
