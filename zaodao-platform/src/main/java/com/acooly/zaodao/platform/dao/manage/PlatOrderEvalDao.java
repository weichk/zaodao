/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-12-11
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.dto.PlatOrderEvalDto;
import com.acooly.zaodao.platform.entity.PlatOrderEval;
import com.acooly.zaodao.platform.order.PlatOrderEvalListOrder;
import org.springframework.data.jpa.repository.Query;

/**
 * zd_plat_order_eval JPA Dao
 *
 * Date: 2017-12-11 17:03:29
 * @author zhike
 *
 */
public interface PlatOrderEvalDao extends EntityJpaDao<PlatOrderEval, Long>,PlatOrderEvalCusDao {

    /**
     * 获取用户订单评价
     * @param touristId
     * @param orderNo
     * @return
     */
    PlatOrderEval findByTouristIdAndOrderNo(String touristId, String orderNo);

    /**
     * 导游订单评价数量
     * @param guideUserId
     * @return
     */
    @Query(value = "SELECT COUNT(1) FROM zd_plat_order_eval WHERE tour_guide_id=?1", nativeQuery = true)
    Integer getCountByTourGuideId(String guideUserId);
}
