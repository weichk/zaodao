/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 14:47 创建
 *
 */
package com.acooly.zaodao.openapi.service.guide;

import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.guide.GuideInfoRequest;
import com.acooly.zaodao.openapi.message.guide.GuideInfoResponse;
import com.acooly.zaodao.platform.order.GuideInfoOrder;
import com.acooly.zaodao.platform.result.GuideInfoResult;
import com.acooly.zaodao.platform.service.manage.TourGuideService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiService(name = "guideInfo", desc = "导游详情", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_guide", name = "导游")
public class GuideInfoApiService extends BaseApiService<GuideInfoRequest, GuideInfoResponse> {
    @Autowired
    private TourGuideService tourGuideService;

    @Override
    protected void doService(GuideInfoRequest request, GuideInfoResponse response) {
        GuideInfoOrder order = request.toOrder(GuideInfoOrder.class);
        order.setGid(Ids.gid());
        GuideInfoResult result = tourGuideService.getGuideInfo(order);
        result.throwExceptionIfNotSuccess();
        BeanCopier.copy(result,response,"pricePerDay");
        response.setPricePerDay(Money.cent(result.getPricePerDay()));
    }
}
