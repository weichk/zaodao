package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 修改订单状态
 *
 * @author xiaohong
 * @create 2018-05-09 9:41
 **/
@Data
@OpenApiMessage(service = "modifyProcess", type = ApiMessageType.Response)
public class ModifyProcessResponse extends ApiResponse {
}
