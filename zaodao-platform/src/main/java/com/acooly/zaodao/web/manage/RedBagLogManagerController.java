/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-07-18
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.RedBagLog;
import com.acooly.zaodao.platform.service.manage.RedBagLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 红包领取记录表 管理控制器
 * 
 * @author zhike
 * Date: 2017-07-18 09:42:32
 */
@Controller
@RequestMapping(value = "/manage/redbag/redBagLog")
public class RedBagLogManagerController extends AbstractJQueryEntityController<RedBagLog, RedBagLogService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private RedBagLogService redBagLogService;

	

}
