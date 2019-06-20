/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-18
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.SecretAnswer;
import com.acooly.zaodao.platform.service.manage.SecretAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 密保答案表 管理控制器
 * 
 * @author zhike
 * Date: 2017-05-18 16:21:58
 */
@Controller
@RequestMapping(value = "/manage/secretAnswer")
public class SecretAnswerManagerController extends AbstractJQueryEntityController<SecretAnswer, SecretAnswerService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private SecretAnswerService secretAnswerService;

	

}
