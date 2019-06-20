package com.acooly.zaodao.openapi.message.customer;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.zaodao.openapi.message.dto.OpenLoginDto;
import lombok.Data;

/**
 * 开放平台登录
 *
 * @author xiaohong
 * @create 2017-12-13 15:27
 **/
@Data
@OpenApiMessage(service = "openPlatformLogin", type = ApiMessageType.Response)
public class OpenPlatformLoginResponse extends ApiResponse {
    @OpenApiField(desc = "开放平台返回实体", constraint = "开放平台返回实体")
    private OpenLoginDto openLoginDto = new OpenLoginDto();
}
