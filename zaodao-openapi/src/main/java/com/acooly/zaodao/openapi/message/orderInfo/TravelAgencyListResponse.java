package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.TravelAgencyInfoDto;
import lombok.Data;

import java.util.List;

/**
 * 旅行社列表
 *
 * @author xiaohong
 * @create 2018-05-04 16:49
 **/
@Data
@OpenApiMessage(service = "travelAgencyList", type = ApiMessageType.Response)
public class TravelAgencyListResponse extends PageApiResponse<TravelAgencyInfoDto> {
}
