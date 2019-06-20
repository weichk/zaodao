package com.acooly.zaodao.gateway.gsy.message;

import lombok.Data;

/**
 * 微信用户访问信息
 *
 * @author xiaohong
 * @create 2017-12-18 14:21
 **/
@Data
public class WxAccessTokenResponse {
    /**
     * 微信access_token
     */
    private String accessToken;

    /**
     * 微信接口调用凭证超时时间
     */
    private Integer expiresIn;

    /**
     * 微信用户刷新access_token
     */
    private String refreshToken;

    /**
     * 微信用户唯一标识
     */
    private String openId;

    /**
     * 代码(发生错误才有值)
     */
    private String errcode;
    /**
     * 消息(发生错误才有值)
     */
    private String errmsg;
}
