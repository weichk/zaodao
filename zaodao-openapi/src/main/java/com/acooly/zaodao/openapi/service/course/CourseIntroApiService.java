package com.acooly.zaodao.openapi.service.course;

import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.course.CourseIntroRequest;
import com.acooly.zaodao.openapi.message.course.CourseIntroResponse;
import com.acooly.zaodao.platform.order.CourseIntroOrder;
import com.acooly.zaodao.platform.result.CourseIntroResult;
import com.acooly.zaodao.platform.service.manage.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

/** Created by xiyang on 2017/9/19. */
@OpenApiService(name = "courseIntro", desc = "编辑课程简介", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_course", name = "课程")
public class CourseIntroApiService extends BaseApiService<CourseIntroRequest, CourseIntroResponse> {

  @Autowired private CourseService courseService;

  @Override
  protected void doService(CourseIntroRequest request, CourseIntroResponse response) {
    CourseIntroOrder order = request.toOrder(CourseIntroOrder.class);
    order.setGid(Ids.gid());
    CourseIntroResult result = courseService.addOrEditCourseIntro(order);
    result.throwExceptionIfNotSuccess();
    response.setCourseId(result.getCourseId());
  }
}
