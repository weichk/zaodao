/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-07-07
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.ArticleOperationLog;
import com.acooly.zaodao.platform.enums.Action;
import com.acooly.zaodao.platform.service.manage.ArticleOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 帖子操作记录表 管理控制器
 * 
 * @author zhike
 * Date: 2017-07-07 15:24:35
 */
@Controller
@RequestMapping(value = "/manage/article/articleOperationLog")
public class ArticleOperationLogManagerController extends AbstractJQueryEntityController<ArticleOperationLog, ArticleOperationLogService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private ArticleOperationLogService articleOperationLogService;

	
	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allActions", Action.mapping());
	}

}
