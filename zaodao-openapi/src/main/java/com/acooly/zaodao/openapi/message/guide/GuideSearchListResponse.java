package com.acooly.zaodao.openapi.message.guide;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.GuideListDto;
import lombok.Data;

/**
 * Created by xiaohong on 2017/10/16.
 */
@Data
@OpenApiMessage(service = "guideSearch", type = ApiMessageType.Response)
public class GuideSearchListResponse extends PageApiResponse<GuideListDto> {
}
