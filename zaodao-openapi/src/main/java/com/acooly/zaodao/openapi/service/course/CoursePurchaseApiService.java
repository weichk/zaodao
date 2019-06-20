package com.acooly.zaodao.openapi.service.course;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.course.CoursePurchaseRequest;
import com.acooly.zaodao.openapi.message.course.CoursePurchaseResponse;
import com.acooly.zaodao.platform.order.CoursePurchaseOrder;
import com.acooly.zaodao.platform.result.CoursePurchaseResult;
import com.acooly.zaodao.platform.service.manage.CoursePurchaseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xiaohong on 2017/10/12.
 */
@OpenApiService(name = "coursePurchase", desc = "购买课程", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_course", name = "课程")
public class CoursePurchaseApiService extends BaseApiService<CoursePurchaseRequest, CoursePurchaseResponse> {
    @Autowired
    CoursePurchaseService coursePurchaseService;

    @Override
    protected void doService(CoursePurchaseRequest request, CoursePurchaseResponse response) {
        CoursePurchaseOrder order = request.toOrder(CoursePurchaseOrder.class);
        order.setGid(Ids.gid());
        CoursePurchaseResult result = coursePurchaseService.addCoursePurchase(order);
        result.throwExceptionIfNotSuccess();
        response.setCoursePurchaseId(result.getCoursePurchaseId());
    }
}
