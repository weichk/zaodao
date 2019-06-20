/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-26
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.dto.TravelReviewDto;
import com.acooly.zaodao.platform.entity.TravelReview;
import com.acooly.zaodao.platform.order.TravelReviewListOrder;

/**
 * zd_travel_review JPA Dao
 *
 * Date: 2017-10-26 19:17:16
 * @author zhike
 *
 */
public interface TravelReviewDao extends EntityJpaDao<TravelReview, Long>, TravelReviewCusDao {

}

