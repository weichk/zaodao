package com.acooly.zaodao.openapi.service.course;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.course.CourseFocusAddRequest;
import com.acooly.zaodao.openapi.message.course.CourseFocusAddResponse;
import com.acooly.zaodao.platform.order.CourseFocusAddOrder;
import com.acooly.zaodao.platform.result.CourseFocusResult;
import com.acooly.zaodao.platform.service.manage.CourseFocusService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 课程关注
 *
 * @author xiaohong
 * @create 2017-10-31 15:00
 **/
@OpenApiService(name = "courseFocusAdd", desc = "添加课程关注", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_course", name = "课程")
public class CourseFocusAddApiService extends BaseApiService<CourseFocusAddRequest, CourseFocusAddResponse> {
    @Autowired
    private CourseFocusService courseFocusService;

    @Override
    protected void doService(CourseFocusAddRequest request, CourseFocusAddResponse response) {
        CourseFocusAddOrder courseFocusAddOrder = request.toOrder(CourseFocusAddOrder.class);
        courseFocusAddOrder.setGid(Ids.gid());
        CourseFocusResult courseFocusResult = courseFocusService.addCourseFocus(courseFocusAddOrder);
        courseFocusResult.throwExceptionIfNotSuccess();
        response.setFocusCount(courseFocusResult.getFocusCount());
        response.setFocusFlag(courseFocusResult.getFocusFlag());
    }
}
