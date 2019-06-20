/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-08-13
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.ArticleRewardLog;

/**
 * 帖子打赏记录表 Service接口
 *
 * Date: 2017-08-13 15:33:36
 * @author zhike
 *
 */
public interface ArticleRewardLogService extends EntityService<ArticleRewardLog> {
    /**
     * 获取打赏数量
     * @param businessId
     * @return
     */
    Long getRewardCount(Long businessId);
}
