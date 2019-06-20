package com.acooly.zaodao.openapi.service.course;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.course.CourseListRequest;
import com.acooly.zaodao.openapi.message.course.CourseListResponse;
import com.acooly.zaodao.openapi.message.dto.CourseDto;
import com.acooly.zaodao.platform.dto.CourseInfoDto;
import com.acooly.zaodao.platform.entity.Course;
import com.acooly.zaodao.platform.order.QueryCourseOrder;
import com.acooly.zaodao.platform.service.manage.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.BiConsumer;

/** Created by xiyang on 2017/9/19. */
@OpenApiService(name = "courseList", desc = "课程列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_course", name = "课程")
public class CourseListApiService extends BaseApiService<CourseListRequest, CourseListResponse> {
  @Autowired private CourseService courseService;

  @Override
  protected void doService(CourseListRequest request, CourseListResponse response) {
    QueryCourseOrder order = request.toOrder(QueryCourseOrder.class);
    order.setGid(Ids.gid());
    PageResult<CourseInfoDto> coursePageResult = courseService.getPageCourseList(order);
    coursePageResult.throwExceptionIfNotSuccess();
    response.setPageResult(coursePageResult, (course, courseDto) ->{
      courseDto.setCourseId(course.getId());
      courseDto.setSumRecordTimeText(courseService.getCourseTimeText(course.getSumRecordTime()));
    });
  }
}
