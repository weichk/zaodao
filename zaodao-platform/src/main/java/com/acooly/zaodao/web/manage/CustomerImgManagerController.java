/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-07
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.CustomerImg;
import com.acooly.zaodao.platform.service.manage.CustomerImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户照片表 管理控制器
 * 
 * @author zhike
 * Date: 2017-06-07 11:37:17
 */
@Controller
@RequestMapping(value = "/manage/customerImg")
public class CustomerImgManagerController extends AbstractJQueryEntityController<CustomerImg, CustomerImgService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private CustomerImgService customerImgService;

	

}
