/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-07
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.CustomerImg;

import java.util.List;

/**
 * 用户照片表 Service接口
 *
 * Date: 2017-06-07 11:37:17
 * 
 * @author zhike
 *
 */
public interface CustomerImgService extends EntityService<CustomerImg> {

	/**
	 * 获取用户默认照片列表
	 * 
	 * @param userId
	 * @return
	 */
	List<CustomerImg> getCusDefaultImgListByUserId(String userId);

	void removeByAlbumId(Long albumId);
}
