package com.acooly.zaodao.openapi.message.travel;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 旅声点赞响应
 *
 * @author xiaohong
 * @create 2017-10-30 14:29
 **/
@Data
@OpenApiMessage(service = "travelVoicePraise", type = ApiMessageType.Response)
public class TravelVoicePraiseResponse extends ApiResponse {
    @OpenApiField(desc = "点赞数", constraint = "点赞数")
    private Long praiseCount = 0l;

    /**
     * 是否已经点过赞 0-未点赞; 1-已点赞
     */
    @OpenApiField(desc = "是否已点赞", constraint = "是否已点赞")
    private Integer praiseFlag;
}
