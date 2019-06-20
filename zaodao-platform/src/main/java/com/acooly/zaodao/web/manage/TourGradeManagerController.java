/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-24
*/
package com.acooly.zaodao.web.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.TourGrade;
import com.acooly.zaodao.platform.service.manage.TourGradeService;

/**
 * 导游评论打分表 管理控制器
 * 
 * @author zhike
 * Date: 2017-05-24 22:43:35
 */
@Controller
@RequestMapping(value = "/manage/tourGrade")
public class TourGradeManagerController extends AbstractJQueryEntityController<TourGrade, TourGradeService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private TourGradeService tourGradeService;

	

}
