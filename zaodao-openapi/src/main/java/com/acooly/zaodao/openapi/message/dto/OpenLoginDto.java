package com.acooly.zaodao.openapi.message.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;

import java.io.Serializable;

/**
 * 开发平台登录
 *
 * @author xiaohong
 * @create 2017-12-13 16:27
 **/
@Data
public class OpenLoginDto implements Serializable {
    @OpenApiField(desc = "访问码", constraint = "客户的用户名,作为登录所有接口的签名accessKey")
    private String accessKey;

    @OpenApiField(desc = "安全码", constraint = "登录后所有接口的签名秘钥")
    private String secretKey;

    @OpenApiField(desc = "客户id", constraint = "客户id")
    private String customerId;
}
