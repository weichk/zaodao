package com.acooly.zaodao.openapi.message.common;

import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 钙片兑换交易请求
 *
 * @author xiaohong
 * @create 2017-11-28 19:38
 **/
@Data
@OpenApiMessage(service = "exchangeCaTrade", type = ApiMessageType.Request)
public class ExchangeCaTradeRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID", demo = "O00117052610240611600000")
    private String userId;

    @NotNull
    @OpenApiField(desc = "钙片数量", constraint = "钙片数量", demo = "100")
    private Integer caNumber;

    @NotBlank
    @OpenApiField(desc = "用户ip", constraint = "用户ip")
    private String userIp;
}
