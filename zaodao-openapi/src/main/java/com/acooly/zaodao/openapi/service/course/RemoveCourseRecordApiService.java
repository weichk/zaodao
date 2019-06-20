package com.acooly.zaodao.openapi.service.course;

import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.course.RemoveCourseRecordResponse;
import com.acooly.zaodao.openapi.message.course.RemoveCourseRecordRequest;
import com.acooly.zaodao.platform.enums.RecordStatusEnum;
import com.acooly.zaodao.platform.order.CourseRecordOrder;
import com.acooly.zaodao.platform.result.CourseRecordResult;
import com.acooly.zaodao.platform.service.manage.CourseRecordService;
import org.springframework.beans.factory.annotation.Autowired;

/** 音频删除服务 Created by xiaohong on 2017/9/22. */
@OpenApiService(name = "removeCourseRecord", desc = "删除音频", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_course", name = "课程")
public class RemoveCourseRecordApiService
    extends BaseApiService<RemoveCourseRecordRequest, RemoveCourseRecordResponse> {
  @Autowired private CourseRecordService courseRecordService;

  @Override
  protected void doService(RemoveCourseRecordRequest request, RemoveCourseRecordResponse response) {
    CourseRecordOrder courseRecordOrder = new CourseRecordOrder();
    BeanCopier.copy(request, courseRecordOrder);
    courseRecordOrder.setRecordStatus(RecordStatusEnum.deleted);
    courseRecordOrder.setGid(Ids.gid());
    CourseRecordResult courseRecordResult =
        courseRecordService.changeRecordStatus(courseRecordOrder);
    courseRecordResult.throwExceptionIfNotSuccess();
  }
}
