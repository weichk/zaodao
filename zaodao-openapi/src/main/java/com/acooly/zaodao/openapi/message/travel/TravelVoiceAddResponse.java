package com.acooly.zaodao.openapi.message.travel;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * Created by xiaohong on 2017/10/26.
 */
@Data
@OpenApiMessage(service = "travelVoiceAdd", type = ApiMessageType.Response)
public class TravelVoiceAddResponse extends ApiResponse {
}
