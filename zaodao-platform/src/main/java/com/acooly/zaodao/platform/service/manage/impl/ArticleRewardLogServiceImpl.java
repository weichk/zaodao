/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-08-13
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.ArticleRewardLogDao;
import com.acooly.zaodao.platform.entity.ArticleRewardLog;
import com.acooly.zaodao.platform.service.manage.ArticleRewardLogService;
import org.springframework.stereotype.Service;

/**
 * 帖子打赏记录表 Service实现
 *
 * Date: 2017-08-13 15:33:36
 *
 * @author zhike
 *
 */
@Service("articleRewardLogService")
public class ArticleRewardLogServiceImpl extends EntityServiceImpl<ArticleRewardLog, ArticleRewardLogDao> implements ArticleRewardLogService {

    /**
     * 获取打赏数量
     * @param businessId
     * @return
     */
    @Override
    public Long getRewardCount(Long businessId) {
        return this.getEntityDao().getRewardCount(businessId);
    }
}
