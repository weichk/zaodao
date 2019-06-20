package com.acooly.zaodao.openapi.message.guide;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.platform.dto.GuideEvalTagDto;
import lombok.Data;

/**
 * 导游评价标签
 *
 * @author xiaohong
 * @create 2018-05-07 20:57
 **/
@Data
@OpenApiMessage(service = "guideEvalTags", type = ApiMessageType.Response)
public class GuideEvalTagsResponse extends PageApiResponse<GuideEvalTagDto> {
}
