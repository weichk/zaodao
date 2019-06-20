/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-12
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.TradingRecord;
import com.acooly.zaodao.platform.enums.InOutType;
import com.acooly.zaodao.platform.enums.TradingType;
import com.acooly.zaodao.platform.service.manage.TradingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 交易记录 管理控制器
 * 
 * @author zhike
 * Date: 2017-06-12 22:57:24
 */
@Controller
@RequestMapping(value = "/manage/tradingRecord")
public class TradingRecordManagerController extends AbstractJQueryEntityController<TradingRecord, TradingRecordService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private TradingRecordService tradingRecordService;

	
	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allInOutTypes", InOutType.mapping());
		model.put("allTradingTypes", TradingType.mapping());
	}

}
