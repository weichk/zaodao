package com.acooly.zaodao.openapi.service.orderInfo;

import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.dto.TravelAgencyInfoDto;
import com.acooly.zaodao.openapi.message.orderInfo.TravelAgencyListRequest;
import com.acooly.zaodao.openapi.message.orderInfo.TravelAgencyListResponse;
import com.acooly.zaodao.platform.dto.TravelAgencyDto;
import com.acooly.zaodao.platform.order.TravelAgencyListOrder;
import com.acooly.zaodao.platform.result.TravelAgencyListResult;
import com.acooly.zaodao.platform.service.manage.TravelAgencyService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 旅行社列表
 *
 * @author xiaohong
 * @create 2018-05-04 16:48
 **/
@OpenApiService(name = "travelAgencyList", desc = "旅行社列表", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_travel_agency", name = "旅行社")
public class TravelAgencyListApiService extends BaseApiService<TravelAgencyListRequest, TravelAgencyListResponse> {
    @Autowired
    private TravelAgencyService travelAgencyService;

    @Override
    protected void doService(TravelAgencyListRequest request, TravelAgencyListResponse response) {
        TravelAgencyListOrder order = request.toOrder(TravelAgencyListOrder.class);
        order.setGid(Ids.gid());
        TravelAgencyListResult result = travelAgencyService.getTravelAgencyInfos(order);
        result.throwExceptionIfNotSuccess();

        List<TravelAgencyInfoDto> list = Lists.newArrayList();
        if(result.getDto() != null) {
            result.getDto().forEach(p -> {
                TravelAgencyInfoDto dto = new TravelAgencyInfoDto();
                BeanCopier.copy(p, dto);
                list.add(dto);
            });
        }
        response.setRows(list);
    }
}
