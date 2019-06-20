/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-07-18
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.RedBagLogDao;
import com.acooly.zaodao.platform.entity.RedBagLog;
import com.acooly.zaodao.platform.service.manage.RedBagLogService;
import org.springframework.stereotype.Service;

/**
 * 红包领取记录表 Service实现
 *
 * Date: 2017-07-18 09:42:32
 *
 * @author zhike
 *
 */
@Service("redBagLogService")
public class RedBagLogServiceImpl extends EntityServiceImpl<RedBagLog, RedBagLogDao> implements RedBagLogService {

    @Override
    public RedBagLog getEntityByUserIdAndRefId(String userId, Long refId) {
        return getEntityDao().getEntityByUserIdAndRefId(userId,refId);
    }
}
