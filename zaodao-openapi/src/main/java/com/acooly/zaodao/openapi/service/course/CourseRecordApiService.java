package com.acooly.zaodao.openapi.service.course;

import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.course.CourseRecordRequest;
import com.acooly.zaodao.openapi.message.course.CourseRecordResponse;
import com.acooly.zaodao.platform.order.CourseRecordIntroOrder;
import com.acooly.zaodao.platform.result.CourseRecordIntroResult;
import com.acooly.zaodao.platform.service.manage.CourseRecordService;
import org.springframework.beans.factory.annotation.Autowired;

/** Created by xiyang on 2017/9/19. */
@OpenApiService(name = "courseRecord", desc = "添加课程音频", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_course", name = "课程")
public class CourseRecordApiService
    extends BaseApiService<CourseRecordRequest, CourseRecordResponse> {
  @Autowired
  private CourseRecordService courseRecordService;
  @Override
  protected void doService(CourseRecordRequest request, CourseRecordResponse response) {
    CourseRecordIntroOrder order = request.toOrder(CourseRecordIntroOrder.class);
    CourseRecordIntroResult recordIntroResult = courseRecordService.addCourseRecord(order);
    recordIntroResult.throwExceptionIfNotSuccess();
    response.setRecordId(recordIntroResult.getRecordId());
    response.setCourseId(recordIntroResult.getCourseId());
  }
}
