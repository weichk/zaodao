package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.facade.PageOrder;
import com.acooly.core.utils.Dates;
import com.acooly.core.utils.Strings;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.common.utils.DateUtil;
import com.acooly.zaodao.platform.dao.manage.CourseCusDao;
import com.acooly.zaodao.platform.dto.CourseInfoDto;
import com.acooly.zaodao.platform.dto.QueryBase;
import com.acooly.zaodao.platform.dto.QueryCourseDto;
import com.acooly.zaodao.platform.entity.Course;
import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import com.acooly.zaodao.platform.enums.CourseType;
import com.acooly.zaodao.platform.enums.LessThanCurrentEnum;
import com.acooly.zaodao.platform.enums.PublishStatusEnum;
import com.acooly.zaodao.platform.order.QueryCourseOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;

import java.util.Date;

/** Created by xiyang on 2017/9/26. */
@Slf4j
public class CourseDaoImpl extends AbstractJdbcTemplateDao implements CourseCusDao {

  @Override
  public PageInfo<CourseInfoDto> getPageCourseList(QueryCourseOrder order) {
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT a.*,b.real_name as realName,c.guide_cover_img as coverImg ");
    sql.append(" FROM zd_course a ");
    sql.append(" JOIN zd_customer b ON(a.user_id=b.user_id)");
    sql.append(" LEFT JOIN zd_tour_guide c ON(b.user_id=c.user_id)");
    sql.append(" WHERE 1=1 ");

    if (Strings.isNotBlank(order.getUserId())) {
      sql.append(String.format(" AND a.user_id='%s'", order.getUserId()));
    }
    if (Strings.isNotBlank(order.getCourseTitle())) {
      sql.append(String.format(" AND a.course_title like '%s%s%s'", "%", order.getCourseTitle(), "%"));
    }

    if (Strings.isNotBlank(order.getKeywords())) {
      sql.append(String.format(" AND (a.course_title like '%s%s%s'", "%", order.getKeywords(), "%"));
      sql.append(String.format(" OR b.real_name like '%s%s%s')", "%", order.getKeywords(), "%"));
    }
    if (Strings.isNotBlank(order.getCourseIntro())) {
      sql.append( String.format(" AND a.course_intro like '%s%s%s'", "%", order.getCourseIntro(), "%"));
    }

    if (null != order.getCourseStatus()) {
      //待审核或者草稿，都为待审核。
      if(order.getCourseStatus() == CourseStatusEnum.review || order.getCourseStatus() == CourseStatusEnum.draft){
        sql.append(String.format(" AND a.course_status IN('%s','%s')", CourseStatusEnum.review.getCode(), CourseStatusEnum.draft.getCode()));
      }else{
        sql.append(String.format(" AND a.course_status='%s'", order.getCourseStatus().getCode()));
      }
    } else {
      sql.append(" and a.course_status!='" + CourseStatusEnum.deleted.getCode() + "'");
    }

    if (order.getLessThanCurrent().equals(LessThanCurrentEnum.yes)) {
      sql.append(" and publish_time<='" + DateUtil.simpleFormat(new Date()) + "'");
    }
    sql.append(" order by a.course_type desc,a.update_time desc,a.create_time desc");

    PageInfo<CourseInfoDto> pageInfo = query(order.getPageInfo(), sql.toString(), CourseInfoDto.class);
    return pageInfo;
  }

  /**
   * 分页获取课程列表
   */
  @Override
  public PageInfo<Course> queryPageCourseList(QueryCourseDto query) {
    PageInfo<Course> pageInfo = new PageInfo<Course>();
    pageInfo.setCountOfCurrentPage(query.getRows());
    pageInfo.setCurrentPage(query.getPage());

    StringBuilder sbSql = new StringBuilder();

    sbSql.append("SELECT a.*,b.real_name FROM zd_course a LEFT JOIN zd_customer b ON a.user_id=b.user_id");
    sbSql.append(" WHERE 1=1 ");

    //报名课程发布后，course_type字段会修改为正式课程，所以使用source_course_type
    if(CourseType.enrolable.equals(query.getCourseType())){
      sbSql.append(String.format(" AND a.source_course_type='%s'", query.getCourseType().getCode()));
    }else{
      sbSql.append(String.format(" AND (a.source_course_type!='%s' ", CourseType.enrolable.getCode()));
      sbSql.append("  OR a.course_status IN('draft','published'))");
    }
    if(Strings.isNotBlank(query.getUserId())){
      sbSql.append(String.format(" AND a.user_id='%s'", query.getUserId()));
    }
    if(Strings.isNotBlank(query.getUserName())){
      sbSql.append(" AND b.real_name LIKE '%");
      sbSql.append(query.getUserName());
      sbSql.append("%'");
    }
    if(Strings.isNotBlank(query.getCourseTitle())){
      sbSql.append(" AND a.course_title LIKE '%");
      sbSql.append(query.getCourseTitle());
      sbSql.append("%'");
    }
    if(query.getCourseStatus() != null){
      sbSql.append(String.format(" AND a.course_status='%s'", query.getCourseStatus().getCode()));
    }
    if(query.getCreateTimeStart() != null && query.getCreateTimeEnd() != null){
      sbSql.append(String.format(" AND a.create_time BETWEEN '%s' AND '%s'", Dates.format(query.getCreateTimeStart()), Dates.format(query.getCreateTimeEnd())));
    }
    if(query.getUpdateTimeStart() != null && query.getUpdateTimeEnd() != null){
      sbSql.append(String.format(" AND a.update_time BETWEEN '%s' AND '%s'", Dates.format(query.getUpdateTimeStart()), Dates.format(query.getUpdateTimeEnd())));
    }
    if(query.getPublishTimeStart() != null && query.getPublishTimeEnd() != null){
      sbSql.append(String.format(" AND a.publish_time BETWEEN '%s' AND '%s'", Dates.format(query.getPublishTimeStart()), Dates.format(query.getPublishTimeEnd())));

    }
    sbSql.append(" ORDER BY a.update_time DESC");
    log.info("查询课程列表sql = {}", sbSql.toString());
    pageInfo = query(pageInfo, sbSql.toString(), Course.class);
    return pageInfo;
  }

  /**
   * 课程首页列表
   */
  @Override
  public PageInfo<CourseInfoDto> getHomeCourseList() {
    PageInfo<CourseInfoDto> pageInfo = new PageInfo<CourseInfoDto>();
    pageInfo.setCountOfCurrentPage(1);

    StringBuilder sql = new StringBuilder();
    sql.append("SELECT a.*,b.real_name as realName,c.guide_cover_img as coverImg ");
    sql.append(" FROM zd_course a ");
    sql.append(" JOIN zd_customer b ON(a.user_id=b.user_id)");
    sql.append(" LEFT JOIN zd_tour_guide c ON(b.user_id=c.user_id)");
    sql.append(" WHERE 1=1 ");
    //在线课程: 已发布状态
    //报名课程: 报名中状态
    sql.append(String.format(" AND ((a.course_type='%s' AND a.course_status='%s') ", CourseType.enrolable.getCode(), CourseStatusEnum.enroll.getCode()));
    sql.append(String.format(" OR ((a.course_type IS NULL OR a.course_type='%s') AND a.course_status='%s'))", CourseType.official.getCode(), CourseStatusEnum.published.getCode()));
    sql.append(" ORDER BY a.update_time DESC,a.create_time ");

    pageInfo = query(pageInfo, sql.toString(), CourseInfoDto.class);
    return pageInfo;
  }
}
