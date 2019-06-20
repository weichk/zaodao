/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-26
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.TravelResourceDto;
import com.acooly.zaodao.platform.entity.TravelResource;

import java.util.List;

/**
 * zd_travel_resource Service接口
 *
 * Date: 2017-10-26 19:16:50
 * @author zhike
 *
 */
public interface TravelResourceService extends EntityService<TravelResource> {

    /**
     * 获取旅声资源
     * @param travelVoiceId
     * @return
     */
    List<TravelResource> getListByTravelVoiceId(Long travelVoiceId);
}
