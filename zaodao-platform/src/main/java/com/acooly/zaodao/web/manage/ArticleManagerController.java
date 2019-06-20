/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-07-07
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.core.common.web.support.JsonListResult;
import com.acooly.core.utils.Collections3;
import com.acooly.module.ofile.domain.OnlineFile;
import com.acooly.module.ofile.portal.OfilePortalController;
import com.acooly.zaodao.common.enums.ArticleCodeTypeEnum;
import com.acooly.zaodao.common.enums.ArticleTypeEnum;
import com.acooly.zaodao.common.enums.StampEnum;
import com.acooly.zaodao.platform.entity.Article;
import com.acooly.zaodao.platform.enums.ArticleHotType;
import com.acooly.zaodao.platform.enums.ArticleStatusEnum;
import com.acooly.zaodao.platform.service.manage.ArticleService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 文章表 管理控制器
 * 
 * @author zhike Date: 2017-07-07 23:34:21
 */
@Slf4j
@Controller
@RequestMapping(value = "/manage/article/article")
public class ArticleManagerController extends AbstractJQueryEntityController<Article, ArticleService> {

	private static Map<Integer, String> allEssenceStatuss = Maps.newLinkedHashMap();
	static {
		allEssenceStatuss.put(1, "是");
		allEssenceStatuss.put(0, "否");
	}
	private static Map<Integer, String> allUpStatuss = Maps.newLinkedHashMap();
	static {
		allUpStatuss.put(1, "是");
		allUpStatuss.put(0, "否");
	}
	private static Map<Integer, String> allFirstPageStatuss = Maps.newLinkedHashMap();
	static {
		allFirstPageStatuss.put(1, "是");
		allFirstPageStatuss.put(0, "否");
	}

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private ArticleService articleService;

	@Autowired
	private OfilePortalController ofilePortalController;

	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allArticleTypes", ArticleTypeEnum.mapping());
		model.put("allEssenceStatuss", allEssenceStatuss);
		model.put("allUpStatuss", allUpStatuss);
		model.put("allFirstPageStatuss", allFirstPageStatuss);
		model.put("allArticleHotTypes", ArticleHotType.mapping());
		model.put("allArticleStatuss", ArticleStatusEnum.mapping());
		model.put("allArticleLabels", ArticleCodeTypeEnum.mapping());
		model.put("allStampEnums", StampEnum.mapping());
	}

	@Override
	protected Article onSave(HttpServletRequest request, HttpServletResponse response, Model model, Article entity,
	        boolean isCreate) throws Exception {
		entity.setContent(HtmlUtils.htmlUnescape(entity.getContent()));
		entity.setTitle(HtmlUtils.htmlUnescape(entity.getTitle()));
		JsonListResult<OnlineFile> onlineFiles = ofilePortalController.upload(request, response);
		OnlineFile onlineFile = Collections3.getFirst(onlineFiles.getRows());
		if (onlineFile != null) {
			entity.setCover("/ofile/image/" + onlineFile.getId());
			entity.setCoverThumb("/ofile/thumb/" + onlineFile.getId());
			log.info("重新设置封面图成功");
		}
		return super.onSave(request, response, model, entity, isCreate);
	}

}
