package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.PointTradeInfoDto;
import lombok.Data;

/**
 * 钙片交易明细
 * @author xiaohong
 * @create 2017-11-29 19:26
 **/
@Data
@OpenApiMessage(service = "tradeCaList", type = ApiMessageType.Response)
public class TradeCaListResponse extends PageApiResponse<PointTradeInfoDto> {
}
