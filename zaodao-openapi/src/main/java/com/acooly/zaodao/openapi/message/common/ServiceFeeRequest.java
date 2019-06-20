package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import lombok.Data;

/**
 * 服务费
 *
 * @author xiaohong
 * @create 2018-06-01 18:05
 **/
@Data
@OpenApiMessage(service = "serviceFee", type = ApiMessageType.Request)
public class ServiceFeeRequest  extends PageApiRequest {
}
