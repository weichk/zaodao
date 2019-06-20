package com.acooly.zaodao.gateway.gsy;

/**
 * 微信服务
 *
 * @author xiaohong
 * @create 2017-12-14 16:59
 **/
public class WxApiConstant {
    //检验微信授权凭证（access_token）是否有效
    public static final String WX_AUTH_URL = "https://api.weixin.qq.com/sns/auth";
    //获取用户访问信息
    public static final String WX_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    //获取用户信息
    public static final String WX_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";
    //刷新access_token
    public static final String WX_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    //检验微信授权凭证成功
    public static final String WX_AUTH_SUCCESS = "OK";
}
