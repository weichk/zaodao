/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-05-24
 */
package com.acooly.zaodao.platform.service.manage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.TourGradeDao;
import com.acooly.zaodao.platform.entity.TourGrade;
import com.acooly.zaodao.platform.service.manage.TourGradeService;

import java.util.List;

/**
 * 导游评论打分表 Service实现
 *
 * Date: 2017-05-24 22:43:35
 *
 * @author zhike
 *
 */
@Service("tourGradeService")
public class TourGradeServiceImpl extends EntityServiceImpl<TourGrade, TourGradeDao> implements TourGradeService {

	@Autowired
	private TourGradeDao tourGradeDao;

	@Override
	public List<TourGrade> findByGuideUserId(String userId) {
		return tourGradeDao.findByGuideUserId(userId);
	}
}
