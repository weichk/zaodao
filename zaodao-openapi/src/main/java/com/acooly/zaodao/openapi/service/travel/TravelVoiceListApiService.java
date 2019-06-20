package com.acooly.zaodao.openapi.service.travel;

import com.acooly.core.common.facade.PageResult;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.travel.TravelVoiceListRequest;
import com.acooly.zaodao.openapi.message.travel.TravelVoiceListResponse;
import com.acooly.zaodao.platform.dto.TravelVoiceDto;
import com.acooly.zaodao.platform.order.TravelVoiceListOrder;
import com.acooly.zaodao.platform.service.manage.TravelVoiceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 旅声列表
 *
 * @author xiaohong
 * @create 2017-10-27 17:22
 **/
@OpenApiService(name = "travelVoiceList", desc = "旅声列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_travel", name = "旅声")
public class TravelVoiceListApiService extends BaseApiService<TravelVoiceListRequest, TravelVoiceListResponse> {
    @Autowired
    private TravelVoiceService travelVoiceService;

    @Override
    protected void doService(TravelVoiceListRequest request, TravelVoiceListResponse response) {
        TravelVoiceListOrder travelVoiceListOrder = request.toOrder(TravelVoiceListOrder.class);
        PageResult<TravelVoiceDto> travelVoicePageResult = travelVoiceService.getTravelVoiceList(travelVoiceListOrder);
        travelVoicePageResult.throwExceptionIfNotSuccess();
        response.setPageResult(travelVoicePageResult, (u, t) ->{ });
    }
}
