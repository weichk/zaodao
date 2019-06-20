/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-26
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.TravelResourceDao;
import com.acooly.zaodao.platform.service.manage.TravelResourceService;
import org.springframework.stereotype.Service;

import com.acooly.zaodao.platform.entity.TravelResource;

import java.util.List;

/**
 * zd_travel_resource Service实现
 *
 * Date: 2017-10-26 19:16:51
 *
 * @author zhike
 *
 */
@Service("travelResourceService")
public class TravelResourceServiceImpl extends EntityServiceImpl<TravelResource, TravelResourceDao> implements TravelResourceService {

    /**
     * 获取旅声资源
     * @param travelVoiceId
     * @return
     */
    @Override
    public List<TravelResource> getListByTravelVoiceId(Long travelVoiceId) {
        return this.getEntityDao().getListByTravelVoiceId(travelVoiceId);
    }
}
