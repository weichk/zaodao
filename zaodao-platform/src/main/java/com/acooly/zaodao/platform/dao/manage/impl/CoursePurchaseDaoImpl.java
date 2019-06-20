package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.utils.Strings;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.CoursePurchaseCusDao;
import com.acooly.zaodao.platform.dto.CourseDto;
import com.acooly.zaodao.platform.entity.Course;
import com.acooly.zaodao.platform.entity.CoursePurchase;
import com.acooly.zaodao.platform.enums.CourseType;
import com.acooly.zaodao.platform.order.CoursePurchaseOrder;
import com.acooly.zaodao.platform.order.QueryCoursePurchaseOrder;

/**
 * Created by xiaohong on 2017/10/12.
 */
public class CoursePurchaseDaoImpl extends AbstractJdbcTemplateDao implements CoursePurchaseCusDao {
    /**
     * 获取课程列表
     * @param order
     * @return
     */
    @Override
    public PageInfo<CourseDto> getPageCoursePurchaseList(QueryCoursePurchaseOrder order) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT a.course_id AS courseId,b.*,d.real_name AS realName,c.guide_cover_img AS coverImg,a.id AS coursePurchaseId");
        sql.append(" FROM zd_course_purchase a ");
        sql.append(" JOIN zd_course b ON(a.course_id=b.id) ");
        sql.append(" JOIN zd_tour_guide c ON(b.user_id=c.user_id) ");
        sql.append(" JOIN zd_customer d ON(b.user_id=d.user_id) ");
        sql.append(" WHERE 1=1");

        if(Strings.isNotBlank(order.getUserId())){
            sql.append(String.format(" AND a.user_id='%s'", order.getUserId()));
        }

        if(Strings.isNotBlank(order.getCourseTitle())){
            sql.append(String.format(" AND b.course_title like '%s%s%s'", "%", order.getCourseTitle(), "%"));
        }

        if(CourseType.enrolable.equals(order.getCourseType())){
            sql.append(" AND b.course_type='enrolable'");
        } else if(CourseType.official.equals(order.getCourseType())){
            sql.append(" AND b.course_type!='enrolable'");
        }

        if(Strings.isNotBlank(order.getCourseIntro())){
            sql.append(String.format(" AND b.course_intro like '%s%s%s'", "%", order.getCourseIntro(), "%"));
        }

        if(null != order.getCourseStatus()){
            sql.append(String.format(" AND b.course_status='%s'", order.getCourseStatus().getCode()));
        }

        sql.append(" ORDER BY publish_time DESC, update_time DESC");
        PageInfo<CourseDto> pageInfo = query(order.getPageInfo(), sql.toString(), CourseDto.class);

        return pageInfo;
    }
}
