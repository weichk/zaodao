package com.acooly.zaodao.openapi.service.guide;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.guide.GuideLevelOneRequest;
import com.acooly.zaodao.openapi.message.guide.GuideLevelOneResponse;
import com.acooly.zaodao.platform.order.GuideLevelOneOrder;
import com.acooly.zaodao.platform.service.manage.TourGuideService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 导游提交一级审核
 *
 * @author xiaohong
 * @create 2018-05-22 11:43
 **/
@OpenApiService(name = "guideLevelOne", desc = "导游一级审核", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_guide", name = "导游")
public class GuideLevelOneApiService extends BaseApiService<GuideLevelOneRequest, GuideLevelOneResponse> {
    @Autowired
    private TourGuideService tourGuideService;

    @Override
    protected void doService(GuideLevelOneRequest request, GuideLevelOneResponse response) {
        GuideLevelOneOrder order = request.toOrder(GuideLevelOneOrder.class);
        order.setGid(Ids.gid());
        ResultBase result = tourGuideService.addGuideLevelOne(order);
        result.throwExceptionIfNotSuccess();
    }
}
