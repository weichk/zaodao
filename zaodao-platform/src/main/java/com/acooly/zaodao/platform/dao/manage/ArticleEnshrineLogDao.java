/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-08-13
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.ArticleEnshrineLog;

/**
 * 帖子收藏记录表 JPA Dao
 *
 * Date: 2017-08-13 15:33:36
 * @author zhike
 *
 */
public interface ArticleEnshrineLogDao extends EntityJpaDao<ArticleEnshrineLog, Long> {

    ArticleEnshrineLog findByUserIdAndArticleId(String userId, Long articleId);
}
