/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-24
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.TourGrade;

import java.util.List;

/**
 * 导游评论打分表 JPA Dao
 *
 * Date: 2017-05-24 22:43:35
 * @author zhike
 *
 */
public interface TourGradeDao extends EntityJpaDao<TourGrade, Long> {

    List<TourGrade> findByGuideUserId(String userId);
}
