package com.acooly.zaodao.openapi.service.course;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.course.CoursePurchaseListRequest;
import com.acooly.zaodao.openapi.message.course.CoursePurchaseListResponse;
import com.acooly.zaodao.platform.dto.CourseDto;
import com.acooly.zaodao.platform.entity.Course;
import com.acooly.zaodao.platform.order.QueryCoursePurchaseOrder;
import com.acooly.zaodao.platform.service.manage.CoursePurchaseService;
import com.acooly.zaodao.platform.service.manage.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.BiConsumer;

/**
 * Created by okobboko on 2017/10/12.
 */
@OpenApiService(name = "coursePurchaseList", desc = "已购买课程列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_course", name = "课程")
public class CoursePurchaseListApiService  extends BaseApiService<CoursePurchaseListRequest, CoursePurchaseListResponse> {
    @Autowired
    private CoursePurchaseService coursePurchaseService;
    @Autowired
    private CourseService courseService;

    @Override
    protected void doService(CoursePurchaseListRequest request, CoursePurchaseListResponse response) {
        QueryCoursePurchaseOrder order = request.toOrder(QueryCoursePurchaseOrder.class);
        PageResult<CourseDto> result = coursePurchaseService.getPageCoursePurchaseList(order);
        result.throwExceptionIfNotSuccess();
        response.setPageResult(result, (u, t) ->{
            t.setSumRecordTimeText(courseService.getCourseTimeText(u.getSumRecordTime()));
        });
    }
}
