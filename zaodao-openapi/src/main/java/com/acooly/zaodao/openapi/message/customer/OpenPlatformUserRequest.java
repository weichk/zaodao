package com.acooly.zaodao.openapi.message.customer;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.zaodao.platform.enums.OpenPlatformType;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 开放平台访问key
 *
 * @author xiaohong
 * @create 2017-12-18 11:04
 **/
@Data
@OpenApiMessage(service = "openPlatformUser", type = ApiMessageType.Request)
public class OpenPlatformUserRequest extends ApiRequest {
    @OpenApiField(desc = "微信Code", constraint = "微信Code")
    private String wxCode;

    @NotNull
    @OpenApiField(desc = "开放平台登录方式", constraint = "开放平台登录方式{weixin,qq,weibo}")
    private OpenPlatformType openLoginMethod;

}
