package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by xiaohong on 2017/10/17.
 */
@Data
@OpenApiMessage(service = "userHead", type = ApiMessageType.Request)
public class UserHeadModifyRequest extends ApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识", demo = "O00117052610240611600000")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "用户头像地址", constraint = "用户头像地址")
    private String headImg;
}
