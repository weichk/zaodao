package com.acooly.zaodao.openapi.service.course;

import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.common.utils.Strings;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.course.CourseDetailRequest;
import com.acooly.zaodao.openapi.message.course.CourseDetailResponse;
import com.acooly.zaodao.openapi.message.dto.CourseRecordDto;
import com.acooly.zaodao.platform.dto.CourseRecordInfoDto;
import com.acooly.zaodao.platform.entity.Course;
import com.acooly.zaodao.platform.order.CourseDetailOrder;
import com.acooly.zaodao.platform.result.CourseResult;
import com.acooly.zaodao.platform.service.manage.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/** 课程信息和音频集合信息 Created by xiyang on 2017/9/23. */
@OpenApiService(name = "courseDetail", desc = "课程详情", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_course", name = "课程")
public class CourseDetailApiService
    extends BaseApiService<CourseDetailRequest, CourseDetailResponse> {
  @Autowired private CourseService courseService;

  @Override
  protected void doService(CourseDetailRequest request, CourseDetailResponse response) {
    CourseDetailOrder order = request.toOrder(CourseDetailOrder.class);
    order.setGid(Ids.gid());
    CourseResult courseResult = courseService.getCourseDetail(order);
    courseResult.throwExceptionIfNotSuccess();

    // 获取系统返回的课程数据
    BeanCopier.copy(courseResult, response, "recordList");

    response.setSumRecordTimeText(courseService.getCourseTimeText(response.getSumRecordTime()));

    // 如果入参的userId等于课程用户id则是否购买标识设置为1
    if (Strings.isNotBlank(request.getUserId()) && request.getUserId().equals(courseResult.getUserId())) {
      response.setPurchaseFlag(1);
    }
    // 获取系统返回的音频数据
    List<CourseRecordInfoDto> courseRecordInfoDtoList = courseResult.getRecordList();

    // 将系统音频数据复制到response
    for (CourseRecordInfoDto crd : courseRecordInfoDtoList) {
      CourseRecordDto c = new CourseRecordDto();
      BeanCopier.copy(crd, c);
      response.getRecordList().add(c);
    }
  }
}
