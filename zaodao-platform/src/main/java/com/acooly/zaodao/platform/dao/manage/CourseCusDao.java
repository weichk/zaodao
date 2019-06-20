package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dto.CourseInfoDto;
import com.acooly.zaodao.platform.dto.QueryCourseDto;
import com.acooly.zaodao.platform.entity.Course;
import com.acooly.zaodao.platform.order.QueryCourseOrder;

/** Created by xiyang on 2017/9/26. */
public interface CourseCusDao {

  /**
   * 分页获取课程数据
   */
  PageInfo<CourseInfoDto> getPageCourseList(QueryCourseOrder order);

  /**
   * 分页查询课程列表
   */
  PageInfo<Course> queryPageCourseList(QueryCourseDto queryCourseDto);

  /**
   * 获取课程首页列表
   * @return
   */
  PageInfo<CourseInfoDto> getHomeCourseList();
}
