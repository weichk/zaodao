package com.acooly.zaodao.openapi.message.travel;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.TravelReviewDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 评论列表返回
 *
 * @author xiaohong
 * @create 2017-10-27 14:50
 **/
@Data
@OpenApiMessage(service = "travelReviewList", type = ApiMessageType.Response)
public class TravelReviewListResponse extends PageApiResponse<TravelReviewDto> {

}
