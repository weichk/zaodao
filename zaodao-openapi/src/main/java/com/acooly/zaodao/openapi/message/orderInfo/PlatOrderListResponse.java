package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.PlatOrderInfoDto;
import com.acooly.zaodao.platform.dto.OrderInfoDto;
import lombok.Data;

/**
 * Created by okobboko on 2017/9/26.
 */
@Data
@OpenApiMessage(service = "platOrderList", type = ApiMessageType.Response)
public class PlatOrderListResponse extends PageApiResponse<PlatOrderInfoDto> {

}
