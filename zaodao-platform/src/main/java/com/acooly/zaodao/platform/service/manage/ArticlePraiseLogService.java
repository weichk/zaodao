/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-07-28
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.ArticlePraiseLog;

/**
 * 帖子点赞记录表 Service接口
 *
 * Date: 2017-07-28 17:15:40
 * @author zhike
 *
 */
public interface ArticlePraiseLogService extends EntityService<ArticlePraiseLog> {

    ArticlePraiseLog findByUserId(String userId);

    ArticlePraiseLog findByUserIdAndArticleId(String userId,Long articleId);
}
