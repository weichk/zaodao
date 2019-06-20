package com.acooly.zaodao.openapi.message.customer;

import com.acooly.module.app.enums.DeviceType;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.zaodao.platform.enums.OpenPlatformType;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 开放平台登录
 *
 * @author xiaohong
 * @create 2017-12-13 15:27
 **/
@Data
@OpenApiMessage(service = "openPlatformLogin", type = ApiMessageType.Request)
public class OpenPlatformLoginRequest extends ApiRequest {

    @NotEmpty
    @OpenApiField(desc = "开放平台用户ID", constraint = "开放平台用户ID")
    private String openUserId;

    @NotNull
    @OpenApiField(desc = "开放平台登录方式", constraint = "开放平台登录方式{weixin,qq,weibo}")
    private OpenPlatformType openLoginMethod;

    @OpenApiField(desc = "客户IP", constraint = "客户IP")
    private String customerIp;

    @OpenApiField(desc = "登录渠道", constraint = "登录渠道")
    private String channel;

    @OpenApiField(desc = "设备类型", constraint = "设备类型")
    private DeviceType deviceType;

    @OpenApiField(desc = "设备型号", constraint = "设备型号")
    private String deviceModel;

    @OpenApiField(desc = "设备标识", constraint = "设备标识")
    private String deviceId;
}
