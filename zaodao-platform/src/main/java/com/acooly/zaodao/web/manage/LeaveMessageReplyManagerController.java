/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-21
*/
package com.acooly.zaodao.web.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.LeaveMessageReply;
import com.acooly.zaodao.platform.service.manage.LeaveMessageReplyService;

/**
 * 留言回复表 管理控制器
 * 
 * @author zhike
 * Date: 2017-06-21 16:42:50
 */
@Controller
@RequestMapping(value = "/manage/leaveMessageReply")
public class LeaveMessageReplyManagerController extends AbstractJQueryEntityController<LeaveMessageReply, LeaveMessageReplyService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private LeaveMessageReplyService leaveMessageReplyService;

	

}
