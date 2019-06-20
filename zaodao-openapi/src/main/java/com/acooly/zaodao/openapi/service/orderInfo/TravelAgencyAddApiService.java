package com.acooly.zaodao.openapi.service.orderInfo;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.dto.TravelAgencyInfoDto;
import com.acooly.zaodao.openapi.message.orderInfo.TravelAgencyAddRequest;
import com.acooly.zaodao.openapi.message.orderInfo.TravelAgencyAddResponse;
import com.acooly.zaodao.platform.dto.TravelAgencyDto;
import com.acooly.zaodao.platform.order.TravelAgencyAddOrder;
import com.acooly.zaodao.platform.result.TravelAgencyAddResult;
import com.acooly.zaodao.platform.service.manage.TravelAgencyService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加旅行社
 *
 * @author xiaohong
 * @create 2018-05-04 16:43
 **/
@OpenApiService(name = "travelAgencyAdd", desc = "添加旅行社", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_travel_agency", name = "旅行社")
public class TravelAgencyAddApiService extends BaseApiService<TravelAgencyAddRequest, TravelAgencyAddResponse> {
    @Autowired
    private TravelAgencyService travelAgencyService;

    @Override
    protected void doService(TravelAgencyAddRequest request, TravelAgencyAddResponse response) {
        TravelAgencyAddOrder order = request.toOrder(TravelAgencyAddOrder.class);
        order.setGid(Ids.gid());
        TravelAgencyAddResult result = (TravelAgencyAddResult) travelAgencyService.addTravelAgency(order);
        result.throwExceptionIfNotSuccess();

        TravelAgencyInfoDto dto = new TravelAgencyInfoDto();
        dto.setId(result.getTravelAgencyDto().getId());
        dto.setAgencyName(result.getTravelAgencyDto().getAgencyName());

        response.setTravelAgencyInfo(dto);
    }
}
