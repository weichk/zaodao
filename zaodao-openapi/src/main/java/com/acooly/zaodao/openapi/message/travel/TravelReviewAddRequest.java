package com.acooly.zaodao.openapi.message.travel;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 *旅声评论添加
 */
@Data
@OpenApiMessage(service = "travelReviewAdd", type = ApiMessageType.Request)
public class TravelReviewAddRequest extends ApiRequest {
    @NotNull
    @OpenApiField(desc = "旅声内容ID", constraint = "旅声内容ID")
    private Long travelVoiceId;

    @NotBlank
    @OpenApiField(desc = "评论用户ID", constraint = "评论用户ID")
    private String userId;

    @OpenApiField(desc = "评论父级ID", constraint = "评论父级ID")
    private Long reviewParentId;

    @OpenApiField(desc = "评论内容", constraint = "评论内容")
    @NotBlank
    private String content;
}
