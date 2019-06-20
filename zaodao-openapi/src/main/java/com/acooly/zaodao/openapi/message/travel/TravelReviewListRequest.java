package com.acooly.zaodao.openapi.message.travel;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 评论列表请求
 *
 * @author xiaohong
 * @create 2017-10-27 14:49
 **/
@Data
@OpenApiMessage(service = "travelReviewList", type = ApiMessageType.Request)
public class TravelReviewListRequest extends PageApiRequest {
    @NotNull
    @OpenApiField(desc = "旅声内容ID", constraint = "旅声内容ID")
    private Long travelVoiceId;
}
