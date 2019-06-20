/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-08-13
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.ArticleEnshrineLogDao;
import com.acooly.zaodao.platform.entity.ArticleEnshrineLog;
import com.acooly.zaodao.platform.service.manage.ArticleEnshrineLogService;
import org.springframework.stereotype.Service;

/**
 * 帖子收藏记录表 Service实现
 *
 * Date: 2017-08-13 15:33:36
 *
 * @author zhike
 *
 */
@Service("articleEnshrineLogService")
public class ArticleEnshrineLogServiceImpl extends EntityServiceImpl<ArticleEnshrineLog, ArticleEnshrineLogDao> implements ArticleEnshrineLogService {
    @Override
    public ArticleEnshrineLog findByUserIdAndArticleId(String userId, Long articleId) {
        return getEntityDao().findByUserIdAndArticleId(userId,articleId);
    }


}
