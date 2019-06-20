/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-15
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.CreditSignin;
import com.acooly.zaodao.platform.service.manage.CreditSigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 积分签到记录 管理控制器
 * 
 * @author zhike
 * Date: 2017-06-15 15:23:25
 */
@Controller
@RequestMapping(value = "/manage/credit/creditSignin")
public class CreditSigninManagerController extends AbstractJQueryEntityController<CreditSignin, CreditSigninService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private CreditSigninService creditSigninService;

	

}
