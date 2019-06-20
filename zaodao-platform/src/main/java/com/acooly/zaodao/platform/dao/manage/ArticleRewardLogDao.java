/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-08-13
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.ArticleRewardLog;
import org.springframework.data.jpa.repository.Query;

/**
 * 帖子打赏记录表 JPA Dao
 *
 * Date: 2017-08-13 15:33:36
 * @author zhike
 *
 */
public interface ArticleRewardLogDao extends EntityJpaDao<ArticleRewardLog, Long> {
    /**
     * 获取打赏数量
     * @param businessId
     * @return
     */
    @Query(value = "SELECT SUM(point_amount) FROM zd_article_reward_log WHERE business_id = ?1", nativeQuery = true)
    Long getRewardCount(Long businessId);
}
