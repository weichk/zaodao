package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 订单详情请求
 *
 * @author xiaohong
 * @create 2017-11-22 18:11
 **/
@Data
@OpenApiMessage(service = "platOrderDetail", type = ApiMessageType.Request)
public class PlatOrderDetailRequest extends ApiRequest {

    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "订单号", constraint = "订单号")
    private String platOrderNo;
}
