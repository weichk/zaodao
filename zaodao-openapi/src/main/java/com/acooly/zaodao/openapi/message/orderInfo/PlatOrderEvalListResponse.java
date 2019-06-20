package com.acooly.zaodao.openapi.message.orderInfo;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.PlatOrderEvalInfoDto;
import lombok.Data;

/**
 * @author xiaohong
 * @create 2017-12-12 9:35
 **/
@Data
@OpenApiMessage(service = "platOrderEvalList", type = ApiMessageType.Response)
public class PlatOrderEvalListResponse extends PageApiResponse<PlatOrderEvalInfoDto> {
}
