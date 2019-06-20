/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-05-24
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.TourGrade;

import java.util.List;

/**
 * 导游评论打分表 Service接口
 *
 * Date: 2017-05-24 22:43:35
 * @author zhike
 *
 */
public interface TourGradeService extends EntityService<TourGrade> {

    List<TourGrade> findByGuideUserId(String userId);
}
