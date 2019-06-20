/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-07-17
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.RedBagDao;
import com.acooly.zaodao.platform.entity.RedBag;
import com.acooly.zaodao.platform.service.manage.RedBagService;
import org.springframework.stereotype.Service;

/**
 * 红包表 Service实现
 * <p>
 * Date: 2017-07-17 22:06:48
 *
 * @author zhike
 *
 */
@Service("redBagService")
public class RedBagServiceImpl extends EntityServiceImpl<RedBag, RedBagDao> implements RedBagService {

    @Override
    public RedBag getEntityByUserIdAndRefId(String userId, Long refId) {
        return getEntityDao().getEntityByUserIdAndRefId(userId, refId);
    }
}
