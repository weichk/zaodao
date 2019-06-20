/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-08-13
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.ArticleRewardLog;
import com.acooly.zaodao.platform.enums.RewardTypeEnum;
import com.acooly.zaodao.platform.service.manage.ArticleRewardLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 帖子打赏记录表 管理控制器
 * 
 * @author zhike
 * Date: 2017-08-13 16:14:17
 */
@Controller
@RequestMapping(value = "/manage/article/articleRewardLog")
public class ArticleRewardLogManagerController extends AbstractJQueryEntityController<ArticleRewardLog, ArticleRewardLogService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private ArticleRewardLogService articleRewardLogService;

	
	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allRewardTypes", RewardTypeEnum.mapping());
	}

}
