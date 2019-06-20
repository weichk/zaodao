package com.acooly.zaodao.openapi.service.course;

import com.acooly.core.common.facade.PageResult;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.course.CourseHomeListRequest;
import com.acooly.zaodao.openapi.message.course.CourseHomeListResponse;
import com.acooly.zaodao.platform.dto.CourseInfoDto;
import com.acooly.zaodao.platform.service.manage.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 课程首页列表
 *
 * @author xiaohong
 * @create 2018-05-31 17:37
 **/
@OpenApiService(name = "courseHomeList", desc = "课程首页列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_course", name = "课程")
public class CourseHomeListApiService extends BaseApiService<CourseHomeListRequest, CourseHomeListResponse> {
    @Autowired
    private CourseService courseService;

    @Override
    protected void doService(CourseHomeListRequest request, CourseHomeListResponse response) {

        PageResult<CourseInfoDto> coursePageResult = courseService.getHomeCourseList(request.getAppUserId());
        coursePageResult.throwExceptionIfNotSuccess();
        response.setPageResult(coursePageResult, (course, courseDto) ->{
            courseDto.setCourseId(course.getId());
            courseDto.setSumRecordTimeText(courseService.getCourseTimeText(course.getSumRecordTime()));
        });
    }
}
