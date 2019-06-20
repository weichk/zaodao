package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * Created by xiaohong on 2017/10/17.
 */
@Data
@OpenApiMessage(service = "userHead", type = ApiMessageType.Response)
public class UserHeadModifyResponse extends ApiResponse {
}
