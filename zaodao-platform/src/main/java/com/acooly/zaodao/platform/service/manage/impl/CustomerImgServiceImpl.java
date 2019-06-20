/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-07
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.CustomerImgDao;
import com.acooly.zaodao.platform.entity.CustomerImg;
import com.acooly.zaodao.platform.service.manage.CustomerImgService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户照片表 Service实现
 *
 * Date: 2017-06-07 11:37:17
 *
 * @author zhike
 *
 */
@Service("customerImgService")
public class CustomerImgServiceImpl extends EntityServiceImpl<CustomerImg, CustomerImgDao>
        implements CustomerImgService {

	@Override
	public List<CustomerImg> getCusDefaultImgListByUserId(String userId) {
		return getEntityDao().getCusDefaultImgListByUserId(userId);
	}

	@Override
	public void removeByAlbumId(Long albumId) {
		List<CustomerImg> list = getEntityDao().findByAlbumId(albumId);
		getEntityDao().deleteInBatch(list);
	}
}
