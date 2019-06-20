package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 订单读取个数统计
 *
 * @author xiaohong
 * @create 2018-06-25 11:01
 **/
@Data
@OpenApiMessage(service = "platOrderCount", type = ApiMessageType.Request)
public class PlatOrderCountRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;
}
