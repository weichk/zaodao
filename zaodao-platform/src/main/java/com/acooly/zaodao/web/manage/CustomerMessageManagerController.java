/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-02
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.CustomerMessage;
import com.acooly.zaodao.platform.service.manage.CustomerMessageService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户系统消息表 管理控制器
 * 
 * @author zhike
 * Date: 2017-06-02 14:31:00
 */
@Controller
@RequestMapping(value = "/manage/customerMessage")
public class CustomerMessageManagerController extends AbstractJQueryEntityController<CustomerMessage, CustomerMessageService> {
	
	private static Map<Integer, String> allReadStatuss = Maps.newLinkedHashMap();
	static {
		allReadStatuss.put(1, "已查看");
		allReadStatuss.put(0, "未查看");
	}

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private CustomerMessageService customerMessageService;

	
	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allReadStatuss", allReadStatuss);
	}

}
