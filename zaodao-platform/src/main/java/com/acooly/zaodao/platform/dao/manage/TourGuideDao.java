/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-01
 */
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.GuideCountInfo;
import com.acooly.zaodao.platform.entity.TourGuide;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 导游信息表 JPA Dao
 *
 * <p>Date: 2017-06-01 17:44:06
 *
 * @author zhike
 */
public interface TourGuideDao extends EntityJpaDao<TourGuide, Long>, TourGuideCusDao {

  @Modifying
  @Query("delete from TourGuide where userId = ?1")
  void deleteEntityByUserId(String userId);

  @Query("from TourGuide where userId = ?1")
  TourGuide findEntityByUserId(String userId);

  @Query(value = "select * from zd_tour_guide where user_id = ?1 for update", nativeQuery = true)
  TourGuide findByUserIdWithLock(String userId);
}
