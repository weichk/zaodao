package com.acooly.zaodao.openapi.service.travel;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.openapi.framework.domain.ApiService;
import com.acooly.zaodao.openapi.message.travel.TravelVoicePraiseRequest;
import com.acooly.zaodao.openapi.message.travel.TravelVoicePraiseResponse;
import com.acooly.zaodao.platform.order.TravelVoicePraiseOrder;
import com.acooly.zaodao.platform.result.TravelVoicePraiseResult;
import com.acooly.zaodao.platform.service.manage.TravelVoiceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 旅声点赞
 *
 * @author xiaohong
 * @create 2017-10-30 14:27
 **/
@OpenApiService(name = "travelVoicePraise", desc = "旅声点赞", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_travel", name = "旅声")
public class TravelVoicePraiseApiService extends BaseApiService<TravelVoicePraiseRequest, TravelVoicePraiseResponse> {
    @Autowired
    private TravelVoiceService travelVoiceService;

    @Override
    protected void doService(TravelVoicePraiseRequest request, TravelVoicePraiseResponse response) {
        TravelVoicePraiseOrder travelVoicePraiseOrder = request.toOrder(TravelVoicePraiseOrder.class);
        travelVoicePraiseOrder.setGid(Ids.gid());
        TravelVoicePraiseResult travelVoicePraiseResult = travelVoiceService.praiseTravelVoice(travelVoicePraiseOrder);
        travelVoicePraiseResult.throwExceptionIfNotSuccess();
        response.setPraiseCount(travelVoicePraiseResult.getPraiseCount());
        response.setPraiseFlag(travelVoicePraiseResult.getPraiseFlag());
    }
}
