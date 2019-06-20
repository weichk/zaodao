/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 10:57 创建
 *
 */
package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "createPlatOrder", type = ApiMessageType.Response)
public class CreateOrderResponse extends ApiResponse{

}
