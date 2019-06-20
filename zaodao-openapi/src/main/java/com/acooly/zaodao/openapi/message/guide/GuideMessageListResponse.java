package com.acooly.zaodao.openapi.message.guide;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.GuideMessageIntoDto;
import lombok.Data;

/**
 * 导游详情动态响应
 *
 * @author xiaohong
 * @create 2017-10-31 16:51
 **/
@Data
@OpenApiMessage(service = "guideMessageList", type = ApiMessageType.Response)
public class GuideMessageListResponse extends PageApiResponse<GuideMessageIntoDto> {
}
