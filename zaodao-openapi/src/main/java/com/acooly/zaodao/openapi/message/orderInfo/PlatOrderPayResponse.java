package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 订单支付响应
 *
 * @author xiaohong
 * @create 2017-11-22 14:41
 **/
@Data
@OpenApiMessage(service = "platOrderPay", type = ApiMessageType.Response)
public class PlatOrderPayResponse extends ApiResponse {
    @OpenApiField(desc = "二维码http路径", constraint = "二维码http路径")
    private String codeUrl;

    @OpenApiField(desc = "二维码链接内容", constraint = "二维码链接内容")
    private String codeUrlContent;
}
