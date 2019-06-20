/*
* zhike@yiji.com Inc.
* Copyright (c) 2018 All Rights Reserved.
* create by zhike
* date:2018-05-07
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.dto.GuideEvalTagDto;
import com.acooly.zaodao.platform.entity.OrderGuideTag;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * zd_order_guide_tag JPA Dao
 *
 * Date: 2018-05-07 21:17:18
 * @author zhike
 *
 */
public interface OrderGuideTagDao extends EntityJpaDao<OrderGuideTag, Long> {

    /**
     * 获取导游标签统计
     */
    @Query(value = "SELECT COUNT(1) AS tagCount,MAX(b.tag_content) AS tagContent FROM zd_order_guide_tag a JOIN zd_order_eval_tag b ON a.eval_tag_id=b.id WHERE a.tour_guide_id=?1 GROUP BY a.eval_tag_id", nativeQuery = true)
    List<Object[]> getGuideEvalTags(String guideUserId);
}
