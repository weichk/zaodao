package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.CourseFocusCusDao;
import com.acooly.zaodao.platform.dto.CourseFocusDto;
import com.acooly.zaodao.platform.order.CourseFocusListOrder;

/**
 * 课程关注列表
 *
 * @author xiaohong
 * @create 2017-10-31 15:40
 **/
public class CourseFocusDaoImpl extends AbstractJdbcTemplateDao implements CourseFocusCusDao {
    @Override
    public PageInfo<CourseFocusDto> getPageCourseFocusList(CourseFocusListOrder courseFocusListOrder) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.user_id AS userId,b.user_name AS userName,b.real_name AS realName,");
        sql.append(" b.head_img AS headImg,c.id AS courseId,c.course_intro AS courseIntro,");
        sql.append(" c.course_img AS courseImg,c.course_title AS courseTitle,c.create_time AS createTime");
        sql.append(" FROM zd_course_focus a JOIN zd_customer b ON(a.user_id=b.user_id)");
        sql.append(" JOIN zd_course c ON(a.focus_course_id=c.id)");
        sql.append(String.format(" WHERE a.user_id='%s'", courseFocusListOrder.getUserId()));
        sql.append(" ORDER BY a.create_time desc");

        PageInfo<CourseFocusDto> pageInfo = query(courseFocusListOrder.getPageInfo(), sql.toString(), CourseFocusDto.class);

        return pageInfo;
    }
}
