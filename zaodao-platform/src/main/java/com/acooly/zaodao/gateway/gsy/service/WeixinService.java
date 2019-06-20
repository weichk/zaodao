package com.acooly.zaodao.gateway.gsy.service;

import com.acooly.zaodao.gateway.gsy.message.WxAccessTokenResponse;
import com.acooly.zaodao.gateway.gsy.message.WxAuthResponse;
import com.acooly.zaodao.gateway.gsy.message.WxUserResponse;

/**
 * 微信服务
 * @author xiaohong
 */
public interface WeixinService {
    /**
     * 检验授权凭证（access_token）是否有效
     * @param accessToken
     * @param openId
     * @return
     */
    WxAuthResponse getAuth(String wxAppId, String wxSecretKey, String accessToken, String openId);

    /**
     * 获取用户访问信息
     * @param wxAppId
     * @param wxSecretKey
     * @param wxCode
     * @return
     */
    WxAccessTokenResponse getAccessToken(String wxAppId, String wxSecretKey, String wxCode);

    /**
     * 获取微信用户信息
     * @param accessToken
     * @param openId
     * @return
     */
    WxUserResponse getUserInfo(String wxAppId, String wxSecretKey,String accessToken, String refreshToken, String openId);

    /**
     * 刷新access_token
     * @param wxAppId
     * @param refreshToken
     * @return
     */
    WxAccessTokenResponse refreshAccessToken(String wxAppId, String refreshToken);
}
