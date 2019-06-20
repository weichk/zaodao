/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-31
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.facade.PageResult;
import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.dto.CourseReviewDto;
import com.acooly.zaodao.platform.entity.CourseReview;
import com.acooly.zaodao.platform.order.CourseReviewListOrder;

/**
 * zd_course_review JPA Dao
 *
 * Date: 2017-10-31 11:09:26
 * @author zhike
 *
 */
public interface CourseReviewDao extends EntityJpaDao<CourseReview, Long>, CourseReviewCusDao {

}
