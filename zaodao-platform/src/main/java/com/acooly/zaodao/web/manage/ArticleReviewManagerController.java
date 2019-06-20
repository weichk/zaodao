/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-01
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.ArticleReview;
import com.acooly.zaodao.platform.service.manage.ArticleReviewService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 评论回复表 管理控制器
 * 
 * @author zhike
 * Date: 2017-06-01 15:14:52
 */
@Controller
@RequestMapping(value = "/manage/articleReview")
public class ArticleReviewManagerController extends AbstractJQueryEntityController<ArticleReview, ArticleReviewService> {
	
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
	private ArticleReviewService articleReviewService;

	
	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allReadStatuss", allReadStatuss);
	}

}
