/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-26
*/
package com.acooly.zaodao.web.manage;



import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.core.common.web.MappingMethod;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.zaodao.platform.service.manage.TravelPraiseLogService;
import com.acooly.zaodao.platform.service.manage.TravelVoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.zaodao.platform.entity.TravelVoice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;


/**
 * zd_travel_voice 管理控制器
 * 
 * @author zhike
 * Date: 2017-10-26 18:05:37
 */
@Slf4j
@Controller
@RequestMapping(value = "/manage/customer/travelVoice")
public class TravelVoiceManagerController extends AbstractJQueryEntityController<TravelVoice, TravelVoiceService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private TravelVoiceService travelVoiceService;

	@Autowired
	private TravelPraiseLogService travelPraiseLogService;

	@Override
	protected void onRemove(HttpServletRequest request, HttpServletResponse response, Model model, Serializable...
			ids) throws Exception {
		travelPraiseLogService.removeByTravelVoiceIds((Long[])ids);
	}

}
