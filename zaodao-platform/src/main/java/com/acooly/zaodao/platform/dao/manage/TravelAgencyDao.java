/*
* zhike@yiji.com Inc.
* Copyright (c) 2018 All Rights Reserved.
* create by zhike
* date:2018-05-04
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.dto.TravelAgencyDto;
import com.acooly.zaodao.platform.entity.TravelAgency;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 旅行社 JPA Dao
 *
 * Date: 2018-05-04 16:34:01
 * @author zhike
 *
 */
public interface TravelAgencyDao extends EntityJpaDao<TravelAgency, Long> {

    /**
     * 根据用户ID和旅行社名称查询旅行社
     */
    TravelAgency findByUserIdAndAgencyName(String userId, String agencyName);

    /**
     * 根据用户ID和旅行社许可证号查询旅行社
     */
    TravelAgency findByUserIdAndLicenseNo(String userId, String licenseNo);

    /**
     * 获取用户旅行社列表
     */
    @Query(value = "SELECT * FROM zd_travel_agency WHERE user_id=?1 ORDER BY create_time DESC LIMIT 10", nativeQuery = true)
    List<TravelAgency> getTravelAgencyList(String userId);
}
