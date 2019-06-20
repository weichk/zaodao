package com.acooly.zaodao.openapi.service.travel;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.travel.TravelVoiceAddRequest;
import com.acooly.zaodao.openapi.message.travel.TravelVoiceAddResponse;
import com.acooly.zaodao.platform.order.TravelVoiceAddOrder;
import com.acooly.zaodao.platform.service.manage.TravelVoiceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 旅声添加
 * Created by xiaohong on 2017/10/26.
 */
@OpenApiService(name = "travelVoiceAdd", desc = "旅声添加", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_travel", name = "旅声")
public class TravelVoiceAddApiService extends BaseApiService<TravelVoiceAddRequest, TravelVoiceAddResponse> {

    @Autowired
    private TravelVoiceService travelVoiceService;

    @Override
    protected void doService(TravelVoiceAddRequest request, TravelVoiceAddResponse response) {
        TravelVoiceAddOrder travelVoiceAddOrder = request.toOrder(TravelVoiceAddOrder.class);
        travelVoiceAddOrder.setGid(Ids.gid());
        ResultBase resultBase = travelVoiceService.addTravelVoice(travelVoiceAddOrder);
        resultBase.throwExceptionIfNotSuccess();
    }
}
