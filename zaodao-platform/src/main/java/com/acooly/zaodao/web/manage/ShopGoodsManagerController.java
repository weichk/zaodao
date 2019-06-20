/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-15
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.core.common.web.support.JsonListResult;
import com.acooly.module.ofile.OFileProperties;
import com.acooly.module.ofile.domain.OnlineFile;
import com.acooly.module.ofile.portal.OfilePortalController;
import com.acooly.zaodao.common.utils.Dates;
import com.acooly.zaodao.platform.entity.ShopGoods;
import com.acooly.zaodao.platform.entity.ShopGoodsType;
import com.acooly.zaodao.platform.service.manage.ShopGoodsService;
import com.acooly.zaodao.platform.service.manage.ShopGoodsTypeService;
import com.acooly.zaodao.platform.service.manage.ShopSupplierService;
import com.acooly.zaodao.web.manage.common.CreditConstants;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 商品信息 管理控制器
 * 
 * @author zhike
 * Date: 2017-06-15 15:23:24
 */
@Controller
@RequestMapping(value = "/manage/credit/shopGoods")
public class ShopGoodsManagerController extends AbstractJQueryEntityController<ShopGoods, ShopGoodsService> {

	@Autowired
	private OfilePortalController ofilePortalController;

	@Autowired
	private OFileProperties oFileProperties;

	@Autowired
	private ShopGoodsService shopGoodsService;

	@Autowired
	private ShopGoodsTypeService shopGoodsTypeService;

	@Autowired
	private ShopSupplierService shopSupplierService;

	private static Map<Integer, String> allDeliveryTypes = Maps.newLinkedHashMap();
	static {
		allDeliveryTypes.put(1, "实物");
		allDeliveryTypes.put(2, "虚拟商品");
	}
	private static Map<Integer, String> allStatuss = Maps.newLinkedHashMap();
	static {
		allStatuss.put(1, "上架");
		allStatuss.put(2, "下架");
	}

	private static Map<Integer, String> hotType = Maps.newLinkedHashMap();
	static {
		hotType.put(0, "普通商品");
		hotType.put(1, "热门商品");
	}

	{
		allowMapping = "*";
	}

	@Override
	protected void onCreate(HttpServletRequest request, HttpServletResponse response, Model model) {
		String typeId = request.getParameter("typeId");
		ShopGoodsType shopGoodsType = shopGoodsTypeService.get(Long.valueOf(typeId));
		model.addAttribute("typeId", typeId);
		model.addAttribute("typeName", shopGoodsType.getName());
		super.onCreate(request, response, model);
	}

	@Override
	protected void onEdit(HttpServletRequest request, HttpServletResponse response, Model model, ShopGoods entity) {
		model.addAttribute("typeId", entity.getShopGoodsType().getId());
		model.addAttribute("typeName", entity.getShopGoodsType().getName());
		super.onEdit(request, response, model, entity);
	}

	@Override
	protected ShopGoods onSave(HttpServletRequest request, HttpServletResponse response, Model model, ShopGoods entity,
							   boolean isCreate) throws Exception {
		if (isCreate) {
			entity.setCode(Dates.generateTransactionNo());
		}
		JsonListResult<OnlineFile> uploadResult = ofilePortalController.upload(request, response);
		for (OnlineFile onlineFile : uploadResult.getRows()) {
			entity.setLogo(onlineFile.getAccessUrl());
		}
		entity.setUpdateTime(new Date());
		return super.onSave(request, response, model, entity, isCreate);
	}

	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("goodsTypeId", request.getParameter("goodsTypeId"));
		model.put("allDeliveryTypes", CreditConstants.allDeliveryTypes);
		model.put("allStatuss", CreditConstants.allShopGoodsStatuss);
		model.put("allSuppliers", shopSupplierService.getAll());
		model.put("hotType", hotType);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				com.acooly.core.utils.Dates.CHINESE_DATE_FORMAT_LINE);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(
				Integer.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(
				Double.class, true));
	}


}
