package com.acooly.zaodao.openapi.message.travel;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 旅声详情请求
 *
 * @author xiaohong
 * @create 2017-10-30 9:35
 **/
@Data
@OpenApiMessage(service = "travelVoiceDetail", type = ApiMessageType.Request)
public class TravelVoiceDetailRequest extends ApiRequest {
    @NotNull
    @OpenApiField(desc = "旅声ID", constraint = "旅声ID")
    private Long travelVoiceId;

    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;
}
