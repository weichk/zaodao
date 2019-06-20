package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.OpenPlatformType;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 开放平台验证手机号
 *
 * @author xiaohong
 * @create 2018-01-05 11:36
 **/
@Data
public class CustomerMobileCheckOrder extends OrderBase {
    @NotEmpty
    @OpenApiField(desc = "手机号码", constraint = "手机号码", demo = "18697652663")
    private String mobileNo;

    @NotNull
    @OpenApiField(desc = "开放平台登录方式", constraint = "开放平台登录方式{weixin,qq,weibo}")
    private OpenPlatformType openLoginMethod;
}
