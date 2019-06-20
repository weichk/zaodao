package com.acooly.zaodao.platform.order;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.OrderBase;
import com.acooly.module.app.enums.DeviceType;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.OpenPlatformType;
import com.google.common.base.Strings;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 开放平台登录
 * @author xiaohong
 * @create 2017-12-13 16:03
 **/
@Data
public class OpenPlatformLoginOrder extends OrderBase {

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

    @Override
    public void check(){
        super.check();
        if(openLoginMethod == OpenPlatformType.weixin){
            if(Strings.isNullOrEmpty(openUserId)){
                throw new BusinessException("微信登录用户ID不能为空");
            }
        }else if(openLoginMethod == OpenPlatformType.qq){
            if(Strings.isNullOrEmpty(openUserId)){
                throw new BusinessException("QQ登录用户ID不能为空");
            }
        }else if(openLoginMethod == OpenPlatformType.weibo){
            if(Strings.isNullOrEmpty(openUserId)){
                throw new BusinessException("微博登录用户ID不能为空");
            }
        }
    }
}
