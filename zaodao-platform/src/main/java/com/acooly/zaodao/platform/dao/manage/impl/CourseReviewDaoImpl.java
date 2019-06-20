package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.facade.PageResult;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.CourseReviewCusDao;
import com.acooly.zaodao.platform.dto.CourseReviewDto;
import com.acooly.zaodao.platform.order.CourseReviewListOrder;

/**
 * 课程回复
 *
 * @author xiaohong
 * @create 2017-10-31 13:39
 **/
public class CourseReviewDaoImpl extends AbstractJdbcTemplateDao implements CourseReviewCusDao {
    /**
     * 获取课程列表
     * @param courseReviewListOrder
     * @return
     */
    @Override
    public PageInfo<CourseReviewDto> getPageCourseReviewList(CourseReviewListOrder courseReviewListOrder) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.id as courseReviewId,a.course_id as courseId, a.user_id AS userId,b.user_name AS userName,b.real_name AS realName,b.head_img AS headImg ");
        sql.append(",a.review_parent_id AS reviewParentId,a.content,a.create_time AS createTime ");
        sql.append(" FROM zd_course_review a JOIN zd_customer b ON(a.user_id=b.user_id) ");
        sql.append(String.format(" WHERE a.course_id='%s' ORDER BY a.create_time desc", courseReviewListOrder.getCourseId()));

        PageInfo<CourseReviewDto> pageInfo = query(courseReviewListOrder.getPageInfo(), sql.toString(), CourseReviewDto.class);

        return pageInfo;
    }
}
