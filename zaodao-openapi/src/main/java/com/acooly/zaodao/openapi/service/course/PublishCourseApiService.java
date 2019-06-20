package com.acooly.zaodao.openapi.service.course;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.course.PublishCourseRequest;
import com.acooly.zaodao.openapi.message.course.PublishCourseResponse;
import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import com.acooly.zaodao.platform.service.manage.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xiyang on 2017/9/19.
 */
@OpenApiService(name = "publishCourse", desc = "发布课程", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_course", name = "课程")
public class PublishCourseApiService extends BaseApiService<PublishCourseRequest, PublishCourseResponse> {
    @Autowired
    private CourseService courseService;

    @Override
    protected void doService(PublishCourseRequest request, PublishCourseResponse response) {
        //客户端提出发布课程的请求需要后台审核通过，课程才变为已发布的状态
        ResultBase resultBase = courseService.changeCourseStatus(request.getCourseId(), CourseStatusEnum.review);
        resultBase.throwExceptionIfNotSuccess();
    }
}
