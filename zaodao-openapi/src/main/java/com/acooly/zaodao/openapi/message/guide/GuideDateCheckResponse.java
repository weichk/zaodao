package com.acooly.zaodao.openapi.message.guide;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 检查订单约导日期响应
 *
 * @author xiaohong
 * @create 2017-11-21 14:15
 **/
@Data
@OpenApiMessage(service = "guideDateCheck", type = ApiMessageType.Response)
public class GuideDateCheckResponse extends ApiResponse {
}
