/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-15
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.ShopGoods;
import com.acooly.zaodao.platform.entity.ShopGoodsDetail;
import com.acooly.zaodao.platform.service.manage.ShopGoodsDetailService;
import com.acooly.zaodao.platform.service.manage.ShopGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品详情 管理控制器
 * 
 * @author zhike
 * Date: 2017-06-15 15:23:25
 */
@Controller
@RequestMapping(value = "/manage/credit/shopGoodsDetail")
public class ShopGoodsDetailManagerController extends AbstractJQueryEntityController<ShopGoodsDetail, ShopGoodsDetailService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private ShopGoodsDetailService shopGoodsDetailService;

	@Autowired
	private ShopGoodsService shopGoodsService;

	@RequestMapping("modify")
	public String create(HttpServletRequest request, HttpServletResponse response, Model model) {
		String goodsId = request.getParameter("goodsId");
		ShopGoods goods = shopGoodsService.get(Long.valueOf(goodsId));
		model.addAttribute("goods", goods);
		ShopGoodsDetail detail = getEntityService().getEntityByGoodsId(goods.getId());
		if (detail != null) {
			model.addAttribute("shopGoodsDetail", detail);
		}
		return getEditView();
	}

	@Override
	protected ShopGoodsDetail onSave(HttpServletRequest request, HttpServletResponse response, Model model,
									 ShopGoodsDetail entity, boolean isCreate) throws Exception {
		entity.setBody(HtmlUtils.htmlUnescape(entity.getBody()));
		if (entity.getId() == null) {
			String goodsId = request.getParameter("goodsId");
			ShopGoods goods = shopGoodsService.get(Long.valueOf(goodsId));
			entity.setShopGoods(goods);
		}
		return entity;
	}

}
