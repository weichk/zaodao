package com.acooly.zaodao.openapi.service.travel;

import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.dto.TravelResourceInfoDto;
import com.acooly.zaodao.openapi.message.travel.TravelVoiceDetailRequest;
import com.acooly.zaodao.openapi.message.travel.TravelVoiceDetailResponse;
import com.acooly.zaodao.platform.dto.TravelResourceDto;
import com.acooly.zaodao.platform.order.TravelVoiceDetailOrder;
import com.acooly.zaodao.platform.result.TravelVoiceDetailResult;
import com.acooly.zaodao.platform.service.manage.TravelVoiceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 旅声详情(内容,资源
 *
 * @author xiaohong
 * @create 2017-10-30 9:30
 **/
@OpenApiService(name = "travelVoiceDetail", desc = "旅声详情", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_travel", name = "旅声")
public class TravelVoiceDetailApiService extends BaseApiService<TravelVoiceDetailRequest, TravelVoiceDetailResponse> {
    @Autowired
    private TravelVoiceService travelVoiceService;

    @Override
    protected void doService(TravelVoiceDetailRequest request, TravelVoiceDetailResponse response) {
        TravelVoiceDetailOrder travelVoiceDetailOrder = request.toOrder(TravelVoiceDetailOrder.class);
        travelVoiceDetailOrder.setGid(Ids.gid());
        TravelVoiceDetailResult travelVoiceDetailResult = travelVoiceService.getTravelVoiceDetail(travelVoiceDetailOrder);
        travelVoiceDetailResult.throwExceptionIfNotSuccess();

        for (TravelResourceDto travelResourceDto : travelVoiceDetailResult.getTravelResourceDtoList()){
            TravelResourceInfoDto travelResourceInfoDto = new TravelResourceInfoDto();
            BeanCopier.copy(travelResourceDto, travelResourceInfoDto);
            response.getTravelResourceDtoList().add(travelResourceInfoDto);
        }
    }
}
