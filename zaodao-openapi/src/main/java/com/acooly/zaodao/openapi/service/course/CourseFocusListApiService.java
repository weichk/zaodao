package com.acooly.zaodao.openapi.service.course;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.course.CourseFocusListRequest;
import com.acooly.zaodao.openapi.message.course.CourseFocusListResponse;
import com.acooly.zaodao.platform.dto.CourseFocusDto;
import com.acooly.zaodao.platform.order.CourseFocusListOrder;
import com.acooly.zaodao.platform.service.manage.CourseFocusService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 课程关注列表
 *
 * @author xiaohong
 * @create 2017-10-31 15:23
 **/
@OpenApiService(name = "courseFocusList", desc = "课程关注列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_course", name = "课程")
public class CourseFocusListApiService  extends BaseApiService<CourseFocusListRequest, CourseFocusListResponse> {
    @Autowired
    private CourseFocusService courseFocusService;

    @Override
    protected void doService(CourseFocusListRequest request, CourseFocusListResponse response) {
        CourseFocusListOrder courseFocusListOrder = request.toOrder(CourseFocusListOrder.class);
        courseFocusListOrder.setGid(Ids.gid());
        PageResult<CourseFocusDto> courseFocusDtoPageResult = courseFocusService.getCourseFocusList(courseFocusListOrder);
        courseFocusDtoPageResult.throwExceptionIfNotSuccess();
        response.setPageResult(courseFocusDtoPageResult, (u,t)->{});
    }
}
