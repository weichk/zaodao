/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-01
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.CustomerLanguage;
import com.acooly.zaodao.platform.service.manage.CustomerLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 导游语种表 管理控制器
 * 
 * @author zhike
 * Date: 2017-06-01 17:44:07
 */
@Controller
@RequestMapping(value = "/manage/customerLanguage")
public class CustomerLanguageManagerController extends AbstractJQueryEntityController<CustomerLanguage, CustomerLanguageService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private CustomerLanguageService customerLanguageService;

	

}
