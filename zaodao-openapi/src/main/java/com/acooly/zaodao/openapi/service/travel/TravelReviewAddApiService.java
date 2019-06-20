package com.acooly.zaodao.openapi.service.travel;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.travel.TravelReviewAddRequest;
import com.acooly.zaodao.openapi.message.travel.TravelReviewAddResponse;
import com.acooly.zaodao.platform.order.TravelReviewAddOrder;
import com.acooly.zaodao.platform.result.TravelReviewAddResult;
import com.acooly.zaodao.platform.service.manage.TravelReviewService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 旅声评论
 * Created by xiaohong on 2017/10/27.
 */
@OpenApiService(name = "travelReviewAdd", desc = "旅声评论", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_travel", name = "旅声")
public class TravelReviewAddApiService extends BaseApiService<TravelReviewAddRequest, TravelReviewAddResponse> {
    @Autowired
    private TravelReviewService travelReviewService;

    @Override
    protected void doService(TravelReviewAddRequest request, TravelReviewAddResponse response) {
        TravelReviewAddOrder order = request.toOrder(TravelReviewAddOrder.class);
        order.setGid(Ids.gid());
        TravelReviewAddResult result = travelReviewService.addTravelReivew(order);
        result.throwExceptionIfNotSuccess();
        BeanCopier.copy(result, response);
    }
}
