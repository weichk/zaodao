package com.acooly.zaodao.openapi.service.course;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.course.CourseReviewAddRequest;
import com.acooly.zaodao.openapi.message.course.CourseReviewAddResponse;
import com.acooly.zaodao.platform.order.CourseReviewAddOrder;
import com.acooly.zaodao.platform.service.manage.CourseReviewService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加课程评论
 *
 * @author xiaohong
 * @create 2017-10-31 11:22
 **/
@OpenApiService(name = "courseReviewAdd", desc = "添加课程评论", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_course", name = "课程")
public class CourseReviewAddApiService extends BaseApiService<CourseReviewAddRequest, CourseReviewAddResponse> {
    @Autowired
    private CourseReviewService courseReviewService;

    @Override
    protected void doService(CourseReviewAddRequest request, CourseReviewAddResponse response) {
        CourseReviewAddOrder courseReviewAddOrder = request.toOrder(CourseReviewAddOrder.class);
        courseReviewAddOrder.setGid(Ids.gid());
        ResultBase resultBase = courseReviewService.addCourseReview(courseReviewAddOrder);
        resultBase.throwExceptionIfNotSuccess();
    }
}
