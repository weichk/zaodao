/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 14:31 创建
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
@OpenApiMessage(service = "closeOrder", type = ApiMessageType.Response)
public class CloseOrderResponse extends ApiResponse{

}
