package com.acooly.zaodao.openapi.message.customer;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.zaodao.platform.enums.OpenPlatformType;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 开放平台手机号验证
 *
 * @author xiaohong
 * @create 2018-01-05 11:23
 **/
@Data
@OpenApiMessage(service = "customerMobileCheck", type = ApiMessageType.Request)
public class CustomerMobileCheckRequest extends ApiRequest{
    @NotEmpty
    @OpenApiField(desc = "手机号码", constraint = "手机号码", demo = "18697652663")
    private String mobileNo;

    @NotNull
    @OpenApiField(desc = "开放平台登录方式", constraint = "开放平台登录方式{weixin,qq,weibo}")
    private OpenPlatformType openLoginMethod;
}
