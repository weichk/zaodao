/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-08-13
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.ArticleEnshrineLog;
import com.acooly.zaodao.platform.service.manage.ArticleEnshrineLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 帖子收藏记录表 管理控制器
 * 
 * @author zhike
 * Date: 2017-08-13 15:33:36
 */
@Controller
@RequestMapping(value = "/manage/article/articleEnshrineLog")
public class ArticleEnshrineLogManagerController extends AbstractJQueryEntityController<ArticleEnshrineLog, ArticleEnshrineLogService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private ArticleEnshrineLogService articleEnshrineLogService;

	

}
