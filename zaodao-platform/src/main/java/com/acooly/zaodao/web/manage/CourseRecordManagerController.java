/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-09-22
*/
package com.acooly.zaodao.web.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.CourseRecord;
import com.acooly.zaodao.platform.service.manage.CourseRecordService;

/**
 * zd_course_record 管理控制器
 * 
 * @author zhike
 * Date: 2017-09-22 13:55:40
 */
@Controller
@RequestMapping(value = "/manage/customer/courseRecord")
public class CourseRecordManagerController extends AbstractJQueryEntityController<CourseRecord, CourseRecordService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private CourseRecordService courseRecordService;

	

}
