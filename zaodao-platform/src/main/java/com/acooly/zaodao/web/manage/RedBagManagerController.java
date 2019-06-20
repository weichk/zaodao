/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-07-17
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.RedBag;
import com.acooly.zaodao.platform.service.manage.RedBagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 红包表 管理控制器
 * 
 * @author zhike
 * Date: 2017-07-17 22:06:47
 */
@Controller
@RequestMapping(value = "/manage/redbag/redBag")
public class RedBagManagerController extends AbstractJQueryEntityController<RedBag, RedBagService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private RedBagService redBagService;

	

}
