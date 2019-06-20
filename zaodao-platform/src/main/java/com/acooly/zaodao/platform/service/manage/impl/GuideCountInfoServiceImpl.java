/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-05-30
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.GuideCountInfoDao;
import com.acooly.zaodao.platform.entity.GuideCountInfo;
import com.acooly.zaodao.platform.service.manage.GuideCountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 导游订单统计表 Service实现
 *
 * Date: 2017-05-30 11:07:30
 *
 * @author zhike
 *
 */
@Service("guideCountInfoService")
public class GuideCountInfoServiceImpl extends EntityServiceImpl<GuideCountInfo, GuideCountInfoDao> implements GuideCountInfoService {

    @Autowired
    private GuideCountInfoDao guideCountInfoDao;

    @Override
    public GuideCountInfo findByUserId(String userId) {
        return guideCountInfoDao.findByUserId(userId);
    }
}
