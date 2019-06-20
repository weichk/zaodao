package com.acooly.zaodao.openapi.service.guide;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.guide.GuideModifyRequest;
import com.acooly.zaodao.openapi.message.guide.GuideModifyResponse;
import com.acooly.zaodao.platform.order.GuideModifyOrder;
import com.acooly.zaodao.platform.service.manage.TourGuideService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修改导游信息
 *
 * @author xiaohong
 * @create 2018-05-25 16:59
 **/
@OpenApiService(name = "guideModify", desc = "导游信息修改", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_guide", name = "导游")
public class GuideModifyApiService extends BaseApiService<GuideModifyRequest, GuideModifyResponse> {
    @Autowired
    private TourGuideService tourGuideService;

    @Override
    protected void doService(GuideModifyRequest request, GuideModifyResponse response) {
        GuideModifyOrder order = request.toOrder(GuideModifyOrder.class);
        order.setGid(Ids.gid());
        ResultBase result = tourGuideService.modifyGuideInfo(order);
        result.throwExceptionIfNotSuccess();
    }
}
