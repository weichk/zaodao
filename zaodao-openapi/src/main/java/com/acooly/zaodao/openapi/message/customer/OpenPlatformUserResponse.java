package com.acooly.zaodao.openapi.message.customer;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.zaodao.openapi.message.dto.OpenWxUserDto;
import lombok.Data;

/**
 * 开放平台访问key
 *
 * @author xiaohong
 * @create 2017-12-18 11:05
 **/
@Data
@OpenApiMessage(service = "openPlatformUser", type = ApiMessageType.Response)
public class OpenPlatformUserResponse extends ApiResponse {
    @OpenApiField(desc = "开放平台用户实体", constraint = "开放平台用户实体")
    private OpenWxUserDto openWxUserDto = new OpenWxUserDto();
}
