package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.zaodao.openapi.message.dto.TravelAgencyInfoDto;
import lombok.Data;

/**
 * 添加旅行社
 *
 * @author xiaohong
 * @create 2018-05-04 16:46
 **/
@Data
@OpenApiMessage(service = "travelAgencyAdd", type = ApiMessageType.Response)
public class TravelAgencyAddResponse extends ApiResponse {
    /**
     * 旅行社
     */
    @OpenApiField(desc = "旅行社", constraint = "旅行社")
    private TravelAgencyInfoDto travelAgencyInfo;
}
