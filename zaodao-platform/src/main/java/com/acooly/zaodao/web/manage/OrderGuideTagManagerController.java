/*
* zhike@yiji.com Inc.
* Copyright (c) 2018 All Rights Reserved.
* create by zhike
* date:2018-05-07
*/
package com.acooly.zaodao.web.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.OrderGuideTag;
import com.acooly.zaodao.platform.service.manage.OrderGuideTagService;

/**
 * zd_order_guide_tag 管理控制器
 * 
 * @author zhike
 * Date: 2018-05-07 20:39:17
 */
@Controller
@RequestMapping(value = "/manage/customer/orderGuideTag")
public class OrderGuideTagManagerController extends AbstractJQueryEntityController<OrderGuideTag, OrderGuideTagService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private OrderGuideTagService orderGuideTagService;

	

}
