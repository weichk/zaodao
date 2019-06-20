package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

import java.util.Date;

/**
 * Created by xiaohong on 2017/9/26.
 */
@Data
@OpenApiMessage(service = "platOrderAdd", type = ApiMessageType.Response)
public class PlatOrderAddResponse extends ApiResponse {
    @OpenApiField(desc = "订单号", constraint = "订单号")
    private String platOrderNo;
}
