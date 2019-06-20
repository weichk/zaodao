/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-07-07
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.ArticleOperationLogDao;
import com.acooly.zaodao.platform.entity.ArticleOperationLog;
import com.acooly.zaodao.platform.service.manage.ArticleOperationLogService;
import org.springframework.stereotype.Service;

/**
 * 帖子操作记录表 Service实现
 *
 * Date: 2017-07-07 15:24:35
 *
 * @author zhike
 *
 */
@Service("articleOperationLogService")
public class ArticleOperationLogServiceImpl extends EntityServiceImpl<ArticleOperationLog, ArticleOperationLogDao> implements ArticleOperationLogService {

}
