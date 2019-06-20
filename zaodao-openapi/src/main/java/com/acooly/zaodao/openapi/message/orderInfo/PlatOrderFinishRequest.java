package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 订单完成
 *
 * @author xiaohong
 * @create 2017-12-11 11:30
 **/
@Data
@OpenApiMessage(service = "platOrderFinish", type = ApiMessageType.Request)
public class PlatOrderFinishRequest extends ApiRequest {

    @NotBlank
    @OpenApiField(desc = "订单号", constraint = "订单号")
    private String platOrderNo;
}
