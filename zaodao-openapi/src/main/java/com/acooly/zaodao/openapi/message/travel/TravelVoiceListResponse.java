package com.acooly.zaodao.openapi.message.travel;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.TravelVoiceDto;
import lombok.Data;

/**
 * 旅声列表响应
 *
 * @author xiaohong
 * @create 2017-10-27 17:23
 **/
@Data
@OpenApiMessage(service = "travelVoiceList", type = ApiMessageType.Response)
public class TravelVoiceListResponse extends PageApiResponse<TravelVoiceDto> {
}
