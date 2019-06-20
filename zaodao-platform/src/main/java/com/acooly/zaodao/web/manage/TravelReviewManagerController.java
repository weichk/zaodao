/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-26
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.service.manage.TravelReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.zaodao.platform.entity.TravelReview;


/**
 * zd_travel_review 管理控制器
 * 
 * @author zhike
 * Date: 2017-10-26 19:17:16
 */
@Controller
@RequestMapping(value = "/manage/customer/travelReview")
public class TravelReviewManagerController extends AbstractJQueryEntityController<TravelReview, TravelReviewService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private TravelReviewService travelReviewService;

	

}
