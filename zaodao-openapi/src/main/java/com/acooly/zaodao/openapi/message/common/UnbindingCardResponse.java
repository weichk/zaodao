package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 解绑银行卡
 *
 * @author xiaohong
 * @create 2017-11-24 20:11
 **/
@Data
@OpenApiMessage(service = "unbindingCard", type = ApiMessageType.Response)
public class UnbindingCardResponse extends ApiResponse {
}
