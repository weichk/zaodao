/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-15
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.core.common.web.support.JsonListResult;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.zaodao.platform.entity.ShopGoodsType;
import com.acooly.zaodao.platform.service.manage.ShopGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品分类 管理控制器
 * 
 * @author zhike
 * Date: 2017-06-15 15:23:25
 */
@Controller
@RequestMapping(value = "/manage/credit/shopGoodsType")
public class ShopGoodsTypeManagerController extends AbstractJQueryEntityController<ShopGoodsType, ShopGoodsTypeService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private ShopGoodsTypeService shopGoodsTypeService;

	@Override
	protected void onCreate(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("parentId", request.getParameter("parentId"));
		super.onCreate(request, response, model);
	}

	@Override
	protected void onEdit(HttpServletRequest request, HttpServletResponse response, Model model, ShopGoodsType entity) {
		model.addAttribute("parentId", entity.getParentId());
		super.onEdit(request, response, model, entity);
	}

	@Override
	protected ShopGoodsType doSave(HttpServletRequest request, HttpServletResponse response, Model model,
								   boolean isCreate) throws Exception {
		ShopGoodsType entity = loadEntity(request);
		if (entity == null) {
			entity = getEntityClass().newInstance();
		}
		doDataBinding(request, entity);
		if (isCreate) {
			entity = getEntityService().create(entity.getParentId(), entity.getName(), entity.getComments());
		} else {
			getEntityService().save(entity);
		}
		return entity;
	}

	@RequestMapping("loadTree")
	@ResponseBody
	public JsonListResult<ShopGoodsType> loadTree(HttpServletRequest request, HttpServletResponse response, Model model) {
		JsonListResult<ShopGoodsType> result = new JsonListResult<ShopGoodsType>();
		try {
			result.setRows(shopGoodsTypeService.loadTree(null));
			result.setTotal((long) result.getRows().size());
		} catch (Exception e) {
			handleException(result, "loadTree", e);
		}
		return result;
	}

	@RequestMapping("move")
	@ResponseBody
	public JsonResult move(HttpServletRequest request, HttpServletResponse response, Model model) {
		JsonResult result = new JsonResult();
		String moveType = request.getParameter("moveType");
		String sourceId = request.getParameter("sourceId");
		String targetId = request.getParameter("targetId");
		try {
			ShopGoodsType source = shopGoodsTypeService.get(Long.valueOf(sourceId));
			ShopGoodsType target = shopGoodsTypeService.get(Long.valueOf(targetId));
			if ("inner".equals(moveType)) {
				source.setParentId(target.getId());
			} else if ("prev".equals(moveType)) {
				source.setSortTime(target.getSortTime() + 1);
				// 不同级
				if (source.getParentId() != null && target.getParentId() != null
						&& !source.getParentId().equals(target.getParentId())) {
					source.setParentId(target.getParentId());
				}
			} else if ("next".equals(moveType)) {
				source.setSortTime(target.getSortTime() - 1);
				// 不同级
				if (source.getParentId() != null && target.getParentId() != null
						&& !source.getParentId().equals(target.getParentId())) {
					source.setParentId(target.getParentId());
				}
			}
			getEntityService().save(source);
		} catch (Exception e) {
			handleException(result, "移动[" + moveType + "]", e);
		}
		return result;
	}
	

}
