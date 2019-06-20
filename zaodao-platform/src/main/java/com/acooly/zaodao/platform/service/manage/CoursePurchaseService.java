/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-12
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.CourseDto;
import com.acooly.zaodao.platform.entity.Course;
import com.acooly.zaodao.platform.entity.CoursePurchase;
import com.acooly.zaodao.platform.order.CoursePurchaseOrder;
import com.acooly.zaodao.platform.order.QueryCoursePurchaseOrder;
import com.acooly.zaodao.platform.result.CoursePurchaseResult;

import java.util.List;

/**
 * zd_course_purchase Service接口
 *
 * Date: 2017-10-12 15:01:58
 * @author zhike
 *
 */
public interface CoursePurchaseService extends EntityService<CoursePurchase> {

    /**
     * 添加购买课程记录
     * @param coursePurchaseOrder
     * @return
     */
    CoursePurchaseResult addCoursePurchase(CoursePurchaseOrder coursePurchaseOrder);

    /**
     * 通过用户ID和课程ID查询个数
     * @param userId
     * @param courseId
     * @return
     */
    int getCoursePurchaseCount(String userId, Long courseId);

    /**
     * 获取已购买课程列表
     * @param queryCoursePurchaseOrder
     * @return
     */
    PageResult<CourseDto> getPageCoursePurchaseList(QueryCoursePurchaseOrder queryCoursePurchaseOrder);
}
