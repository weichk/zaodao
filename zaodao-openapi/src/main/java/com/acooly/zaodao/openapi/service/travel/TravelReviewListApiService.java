package com.acooly.zaodao.openapi.service.travel;

import com.acooly.core.utils.Ids;
import com.acooly.core.common.facade.PageResult;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.travel.TravelReviewListRequest;
import com.acooly.zaodao.openapi.message.travel.TravelReviewListResponse;
import com.acooly.zaodao.platform.dto.TravelReviewDto;
import com.acooly.zaodao.platform.order.TravelReviewListOrder;
import com.acooly.zaodao.platform.service.manage.TravelReviewService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 旅声评论列表
 *
 * @author xiaohong
 * @create 2017-10-27 14:48
 **/
@OpenApiService(name = "travelReviewList", desc = "旅声评论列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_travel", name = "旅声")
public class TravelReviewListApiService extends BaseApiService<TravelReviewListRequest, TravelReviewListResponse> {
    @Autowired
    private TravelReviewService travelReviewService;

    @Override
    protected void doService(TravelReviewListRequest request, TravelReviewListResponse response) {
        TravelReviewListOrder travelReviewListOrder = request.toOrder(TravelReviewListOrder.class);
        travelReviewListOrder.setGid(Ids.gid());
        PageResult<TravelReviewDto> travelReviewListResult = travelReviewService.getTravelReviewList(travelReviewListOrder);
        travelReviewListResult.throwExceptionIfNotSuccess();
        response.setPageResult(travelReviewListResult, (u, t) ->{ });
    }
}
