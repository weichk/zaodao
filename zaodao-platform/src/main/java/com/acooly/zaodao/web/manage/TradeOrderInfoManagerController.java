/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-08-09
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.TradeOrderInfo;
import com.acooly.zaodao.platform.enums.OrderStatusEnum;
import com.acooly.zaodao.platform.enums.OrderSubTradeTypeEnum;
import com.acooly.zaodao.platform.enums.OrderTradeTypeEnum;
import com.acooly.zaodao.platform.service.manage.TradeOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 交易订单表 管理控制器
 * 
 * @author zhike
 * Date: 2017-08-09 11:10:35
 */
@Controller
@RequestMapping(value = "/manage/customer/tradeOrderInfo")
public class TradeOrderInfoManagerController extends AbstractJQueryEntityController<TradeOrderInfo, TradeOrderInfoService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private TradeOrderInfoService tradeOrderInfoService;

	
	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allOrderTradeTypes", OrderTradeTypeEnum.mapping());
		model.put("allOrderSubTradeTypes", OrderSubTradeTypeEnum.mapping());
		model.put("allOrderStatuss", OrderStatusEnum.mapping());
	}

}
