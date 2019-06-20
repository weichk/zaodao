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
import com.acooly.zaodao.platform.entity.OrderEvalTag;
import com.acooly.zaodao.platform.service.manage.OrderEvalTagService;

/**
 * zd_order_eval_tag 管理控制器
 * 
 * @author zhike
 * Date: 2018-05-07 20:38:48
 */
@Controller
@RequestMapping(value = "/manage/customer/orderEvalTag")
public class OrderEvalTagManagerController extends AbstractJQueryEntityController<OrderEvalTag, OrderEvalTagService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private OrderEvalTagService orderEvalTagService;

	

}
