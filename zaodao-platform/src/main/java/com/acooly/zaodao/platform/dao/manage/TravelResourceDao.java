/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-26
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.TravelResource;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * zd_travel_resource JPA Dao
 *
 * Date: 2017-10-26 19:16:51
 * @author zhike
 *
 */
public interface TravelResourceDao extends EntityJpaDao<TravelResource, Long> {
    /**
     * 根据旅声ID获取资源列表
     * @param travelVoiceId
     * @return
     */
    List<TravelResource> getListByTravelVoiceId(Long travelVoiceId);
}
