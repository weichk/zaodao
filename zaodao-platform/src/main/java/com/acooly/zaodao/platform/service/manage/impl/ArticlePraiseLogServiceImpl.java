/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-07-28
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.ArticlePraiseLogDao;
import com.acooly.zaodao.platform.entity.ArticlePraiseLog;
import com.acooly.zaodao.platform.service.manage.ArticlePraiseLogService;
import org.springframework.stereotype.Service;

/**
 * 帖子点赞记录表 Service实现
 *
 * Date: 2017-07-28 17:15:40
 *
 * @author zhike
 *
 */
@Service("articlePraiseLogService")
public class ArticlePraiseLogServiceImpl extends EntityServiceImpl<ArticlePraiseLog, ArticlePraiseLogDao> implements ArticlePraiseLogService {

    @Override
    public ArticlePraiseLog findByUserId(String userId) {
        return getEntityDao().findByUserId(userId);
    }

    @Override
    public ArticlePraiseLog findByUserIdAndArticleId(String userId, Long articleId) {
        return getEntityDao().findByUserIdAndArticleId(userId,articleId);
    }
}
