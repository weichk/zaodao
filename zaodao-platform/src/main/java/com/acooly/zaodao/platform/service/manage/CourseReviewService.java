/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-31
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.CourseReviewDto;
import com.acooly.zaodao.platform.entity.CourseReview;
import com.acooly.zaodao.platform.order.CourseReviewAddOrder;
import com.acooly.zaodao.platform.order.CourseReviewListOrder;

/**
 * zd_course_review Service接口
 *
 * Date: 2017-10-31 11:09:25
 * @author zhike
 *
 */
public interface CourseReviewService extends EntityService<CourseReview> {
    /**
     * 添加课程评论
     * @param courseReviewAddOrder
     * @return
     */
    ResultBase addCourseReview(CourseReviewAddOrder courseReviewAddOrder);

    PageResult<CourseReviewDto> getCourseReviewList(CourseReviewListOrder courseReviewListOrder);
}
