/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-29
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.CustomerVideo;
import com.acooly.zaodao.platform.service.manage.CustomerVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户视频表 管理控制器
 * 
 * @author zhike
 * Date: 2017-06-29 12:16:54
 */
@Controller
@RequestMapping(value = "/manage/customerVideo")
public class CustomerVideoManagerController extends AbstractJQueryEntityController<CustomerVideo, CustomerVideoService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private CustomerVideoService customerVideoService;

	

}
