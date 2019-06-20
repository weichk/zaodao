package com.acooly.zaodao.openapi.service.guide;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.guide.GuideDateCheckRequest;
import com.acooly.zaodao.openapi.message.guide.GuideDateCheckResponse;
import com.acooly.zaodao.platform.order.GuideDateCheckOrder;
import com.acooly.zaodao.platform.service.manage.TourGuideService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 检查订单约导日期
 *
 * @author xiaohong
 * @create 2017-11-21 14:10
 **/
@OpenApiService(name = "guideDateCheck", desc = "检查约导日期", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_guide", name = "导游")
public class GuideDateCheckApiService extends BaseApiService<GuideDateCheckRequest, GuideDateCheckResponse> {
    @Autowired
    private TourGuideService tourGuideService;

    @Override
    protected void doService(GuideDateCheckRequest request, GuideDateCheckResponse response) {
        GuideDateCheckOrder guideDateCheckOrder = request.toOrder(GuideDateCheckOrder.class);
        guideDateCheckOrder.setGid(Ids.gid());
        ResultBase resultBase = tourGuideService.getGuideDays(guideDateCheckOrder);
        resultBase.throwExceptionIfNotSuccess();
    }
}
