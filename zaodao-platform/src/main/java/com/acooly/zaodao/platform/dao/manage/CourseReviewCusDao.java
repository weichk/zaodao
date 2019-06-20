package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.facade.PageResult;
import com.acooly.zaodao.platform.dto.CourseReviewDto;
import com.acooly.zaodao.platform.order.CourseReviewListOrder;

/**
 * 课程列表
 *
 * @author xiaohong
 * @create 2017-10-31 12:08
 **/
public interface CourseReviewCusDao {
    /**
     * 获取课程评论列表
     * @param courseReviewListOrder
     * @return
     */
    PageInfo<CourseReviewDto> getPageCourseReviewList(CourseReviewListOrder courseReviewListOrder);
}
