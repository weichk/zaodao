package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.TradingRecordDto;
import lombok.Data;

/**
 * 提现交易明细响应
 * @author xiaohong
 * @create 2017-11-23 17:27
 **/
@Data
@OpenApiMessage(service = "tradeList", type = ApiMessageType.Response)
public class TradeListResponse extends PageApiResponse<TradingRecordDto>{
}
