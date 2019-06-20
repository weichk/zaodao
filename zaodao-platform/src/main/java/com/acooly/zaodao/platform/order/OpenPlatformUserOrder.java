package com.acooly.zaodao.platform.order;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.OpenPlatformType;
import com.google.common.base.Strings;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 开放平台用户信息
 *
 * @author xiaohong
 * @create 2017-12-18 12:03
 **/
@Data
public class OpenPlatformUserOrder extends OrderBase {
    @OpenApiField(desc = "微信Code", constraint = "微信Code")
    private String wxCode;

    @NotNull
    @OpenApiField(desc = "开放平台登录方式", constraint = "开放平台登录方式{weixin,qq,weibo}")
    private OpenPlatformType openLoginMethod;

    @Override
    public void check(){
        super.check();
        if(openLoginMethod == OpenPlatformType.weixin){
           if(Strings.isNullOrEmpty(wxCode)){
                throw new BusinessException("微信Code不能为空");
            }
        }
    }
}
