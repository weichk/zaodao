/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-15
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.core.common.web.support.JsonEntityResult;
import com.acooly.module.security.domain.User;
import com.acooly.zaodao.platform.entity.ShopOrderInfo;
import com.acooly.zaodao.platform.entity.ShopSupplier;
import com.acooly.zaodao.platform.enums.GoodsDeliveryType;
import com.acooly.zaodao.platform.enums.ShopOrderStatus;
import com.acooly.zaodao.platform.service.manage.GoodsDeliveryService;
import com.acooly.zaodao.platform.service.manage.ShopOrderInfoService;
import com.acooly.zaodao.platform.service.manage.ShopSupplierService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * 商城订单信息 管理控制器
 * 
 * @author zhike
 * Date: 2017-06-15 15:23:25
 */
@Controller
@RequestMapping(value = "/manage/credit/shopOrderInfo")
public class ShopOrderInfoManagerController extends AbstractJQueryEntityController<ShopOrderInfo, ShopOrderInfoService> {

	private static Map<Integer, String> allStatuss = ShopOrderStatus.mapping();

	private static final int OPTUSER_TYPE = 20;

	@Autowired
	private ShopOrderInfoService shopOrderInfoService;
	@Autowired
	private ShopSupplierService shopSupplierService;
	@Autowired
	private GoodsDeliveryService goodsDeliveryService;

	@RequestMapping("deliver")
	@ResponseBody
	public JsonEntityResult<ShopOrderInfo> deliver(HttpServletRequest request, HttpServletResponse response) {
		JsonEntityResult<ShopOrderInfo> result = new JsonEntityResult<ShopOrderInfo>();
		try {
			ShopOrderInfo order = loadEntity(request);
			if (order.getStatus() >= ShopOrderStatus.Delivering.getCode()) {
				throw new RuntimeException("商品已发货或到货，不能重复发送货物");
			}
			order.setStatus(ShopOrderStatus.Delivering.getCode());
			getEntityService().save(order);
			result.setEntity(order);
			result.setMessage("设置已发货成功");
		} catch (Exception e) {
			handleException(result, "设置已发货", e);
		}
		return result;
	}

	@RequestMapping("receive")
	@ResponseBody
	public JsonEntityResult<ShopOrderInfo> receive(HttpServletRequest request, HttpServletResponse response) {
		JsonEntityResult<ShopOrderInfo> result = new JsonEntityResult<ShopOrderInfo>();
		try {
			ShopOrderInfo order = loadEntity(request);
			if (order.getDeliveryType() < GoodsDeliveryType.Virtual.getCode()) {
				String verifyCode = request.getParameter("verifyCode");
				goodsDeliveryService.verifyDelivery(order.getOrderNo(), verifyCode);
			}
			order.setStatus(ShopOrderStatus.Received.getCode());
			order.setUpdateTime(new Date());
			getEntityService().save(order);
			result.setEntity(order);
			result.setMessage("确认收货成功");
		} catch (Exception e) {
			handleException(result, "确认收货", e);
		}
		return result;
	}

	@Override
	protected Map<String, Object> getSearchParams(HttpServletRequest request) {
		Map<String, Object> map = super.getSearchParams(request);
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		if (user.getUserType() == OPTUSER_TYPE) {
			ShopSupplier supplier = shopSupplierService.getByOptUser(user.getUsername());
			if (supplier != null) {
				map.put("EQ_supplierId", supplier.getId());
			} else {
				// 如果是供应商操作员，没有设置对应的关系，则不允许查询数据
				map.put("EQ_supplierId", Integer.MAX_VALUE);
			}
		}
		return map;
	}

	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allSuppliers", shopSupplierService.getAll());
		model.put("allStatuss", allStatuss);
		model.put("allDeliveryTypes", GoodsDeliveryType.mapping());
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		model.put("userType", user.getUserType());
	}

}
