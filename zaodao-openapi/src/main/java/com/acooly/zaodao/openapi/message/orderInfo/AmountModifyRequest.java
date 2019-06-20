package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 订单金额修改
 *
 * @author xiaohong
 * @create 2018-05-08 17:37
 **/
@Data
@OpenApiMessage(service = "amountModify", type = ApiMessageType.Request)
public class AmountModifyRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "订单号", constraint = "订单号", demo = "O00117052610240611600000")
    private String platOrderNo;

    @NotNull
    @OpenApiField(desc = "订单金额", constraint = "修改后的订单金额")
    private Money orderAmount;
}
