/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-07
*/
package com.acooly.zaodao.web.manage;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.zaodao.platform.entity.CustomerAlbum;
import com.acooly.zaodao.platform.service.manage.CustomerAlbumService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户相册表 管理控制器
 * 
 * @author zhike
 * Date: 2017-06-07 11:37:17
 */
@Controller
@RequestMapping(value = "/manage/customerAlbum")
public class CustomerAlbumManagerController extends AbstractJQueryEntityController<CustomerAlbum, CustomerAlbumService> {
	
	private static Map<Integer, String> allTypes = Maps.newLinkedHashMap();
	static {
		allTypes.put(1, "默认相册");
		allTypes.put(0, "上传相册");
	}

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private CustomerAlbumService customerAlbumService;

	
	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allTypes", allTypes);
	}

}
