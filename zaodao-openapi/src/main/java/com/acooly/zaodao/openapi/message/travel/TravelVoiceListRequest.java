package com.acooly.zaodao.openapi.message.travel;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import com.acooly.zaodao.platform.enums.TravelVoiceType;
import lombok.Data;

/**
 * 旅声列表请求
 *
 * @author xiaohong
 * @create 2017-10-27 17:23
 **/
@Data
@OpenApiMessage(service = "travelVoiceList", type = ApiMessageType.Request)
public class TravelVoiceListRequest extends PageApiRequest {

    @OpenApiField(desc = "旅声作者ID", constraint = "旅声作者ID")
    private String userId;

    @OpenApiField(desc = "App用户ID", constraint = "App用户ID")
    private String appUserId;

    @OpenApiField(desc = "旅声分类", constraint = "旅声分类", demo = "热门,关注")
    private TravelVoiceType travelVoiceType;

}
