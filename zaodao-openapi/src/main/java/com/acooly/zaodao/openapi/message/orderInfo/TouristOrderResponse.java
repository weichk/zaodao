/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 14:08 创建
 *
 */
package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.zaodao.openapi.message.dto.TouristOrderDto;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "touristOrder", type = ApiMessageType.Response)
public class TouristOrderResponse extends ApiResponse{

    @OpenApiField(desc = "游客订单列表", constraint = "游客订单列表")
    private List<TouristOrderDto> touristOrderDtoList;
}
