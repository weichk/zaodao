package com.acooly.zaodao.openapi.service.course;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.course.CourseReviewListRequest;
import com.acooly.zaodao.openapi.message.course.CourseReviewListResponse;
import com.acooly.zaodao.platform.dto.CourseReviewDto;
import com.acooly.zaodao.platform.order.CourseReviewListOrder;
import com.acooly.zaodao.platform.service.manage.CourseReviewService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 课程评论列表
 *
 * @author xiaohong
 * @create 2017-10-31 11:32
 **/
@OpenApiService(name = "courseReviewList", desc = "课程评论列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_course", name = "课程")
public class CourseReviewListApiService extends BaseApiService<CourseReviewListRequest, CourseReviewListResponse> {
    @Autowired
    private CourseReviewService courseReviewService;

    @Override
    protected void doService(CourseReviewListRequest request, CourseReviewListResponse response) {
        CourseReviewListOrder courseReviewListOrder = request.toOrder(CourseReviewListOrder.class);
        courseReviewListOrder.setGid(Ids.gid());
        PageResult<CourseReviewDto> courseReviewDtoPageResult = courseReviewService.getCourseReviewList(courseReviewListOrder);
        courseReviewDtoPageResult.throwExceptionIfNotSuccess();
        response.setPageResult(courseReviewDtoPageResult, (u, t) ->{ });
    }
}
