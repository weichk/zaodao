package com.acooly.zaodao.openapi.message.guide;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.GuideArticleIntoDto;
import lombok.Data;

/**
 * 导游文章列表
 *
 * @author xiaohong
 * @create 2017-12-06 14:06
 **/
@Data
@OpenApiMessage(service = "guideArticleList", type = ApiMessageType.Response)
public class GuideArticleListResponse extends PageApiResponse<GuideArticleIntoDto> {
}
