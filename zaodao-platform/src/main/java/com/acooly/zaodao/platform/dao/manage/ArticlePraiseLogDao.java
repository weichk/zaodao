/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-07-28
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.ArticlePraiseLog;

/**
 * 帖子点赞记录表 JPA Dao
 *
 * Date: 2017-07-28 17:15:40
 * @author zhike
 *
 */
public interface ArticlePraiseLogDao extends EntityJpaDao<ArticlePraiseLog, Long> {

    ArticlePraiseLog findByUserId(String userId);

    ArticlePraiseLog findByUserIdAndArticleId(String userId,Long articleId);
}
