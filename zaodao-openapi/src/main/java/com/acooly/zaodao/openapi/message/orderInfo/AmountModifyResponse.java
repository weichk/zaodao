package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 修改订单金额
 *
 * @author xiaohong
 * @create 2018-05-08 17:39
 **/
@Data
@OpenApiMessage(service = "amountModify", type = ApiMessageType.Response)
public class AmountModifyResponse extends ApiResponse {
}
