/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-07-04
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.CustomerCard;
import com.acooly.zaodao.platform.service.manage.CustomerCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户绑卡信息表 管理控制器
 * 
 * @author zhike
 * Date: 2017-07-04 10:18:25
 */
@Controller
@RequestMapping(value = "/manage/customer/customerCard")
public class CustomerCardManagerController extends AbstractJQueryEntityController<CustomerCard, CustomerCardService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private CustomerCardService customerCardService;

	

}
