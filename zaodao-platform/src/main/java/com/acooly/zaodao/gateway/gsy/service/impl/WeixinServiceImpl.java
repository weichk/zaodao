package com.acooly.zaodao.gateway.gsy.service.impl;

import com.acooly.core.utils.net.HttpResult;
import com.acooly.core.utils.net.Https;
import com.acooly.zaodao.gateway.gsy.WxApiConstant;
import com.acooly.zaodao.gateway.gsy.message.WxAccessTokenResponse;
import com.acooly.zaodao.gateway.gsy.message.WxAuthResponse;
import com.acooly.zaodao.gateway.gsy.message.WxUserResponse;
import com.acooly.zaodao.gateway.gsy.service.WeixinService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 微信服务
 *
 * @author xiaohong
 * @create 2017-12-14 16:34
 **/
@Service("WeixinService")
@Slf4j
public class WeixinServiceImpl implements WeixinService {

    /**
     * 检验微信授权凭证（access_token）是否有效
     * @param accessToken
     * @param openId
     * @return
     */
    @Override
    public WxAuthResponse getAuth(String wxAppId, String wxSecretKey, String accessToken, String openId) {
        String url = String.format("%s?access_token=%s&openid=%s", WxApiConstant.WX_AUTH_URL, accessToken, openId);
        log.info(url);
        HttpResult httpResult = Https.getInstance().get(url);
        String responseStr = httpResult.getBody();
        log.info(responseStr);
        WxAuthResponse response = JSON.parseObject(responseStr, WxAuthResponse.class);
        log.info("{}",response);

        return response;
    }

    /**
     * 获取用户访问信息
     * @param wxAppId
     * @param wxSecretKey
     * @param wxCode
     * @return
     */
    @Override
    public WxAccessTokenResponse getAccessToken(String wxAppId, String wxSecretKey, String wxCode) {
        String url = String.format("%s?appid=%s&secret=%s&code=%s&grant_type=authorization_code", WxApiConstant.WX_ACCESS_TOKEN_URL, wxAppId, wxSecretKey, wxCode);
        log.info(url);
        HttpResult httpResult = Https.getInstance().get(url);
        String responseStr = httpResult.getBody();
        log.info(responseStr);
        WxAccessTokenResponse response = JSON.parseObject(responseStr, WxAccessTokenResponse.class);
        log.info("{}",response);

        return response;
    }

    /**
     * 获取微信用户信息
     * @param accessToken
     * @param openId
     * @return
     */
    @Override
    public WxUserResponse getUserInfo(String wxAppId, String wxSecretKey, String accessToken, String refreshToken, String openId) {
        WxAuthResponse authResponse = getAuth(wxAppId, wxSecretKey, accessToken, openId);
        //access_token无效
        if(!authResponse.getErrmsg().equals(WxApiConstant.WX_AUTH_SUCCESS)){
            WxAccessTokenResponse tokenResponse = refreshAccessToken(wxAppId, refreshToken);
            accessToken = tokenResponse.getAccessToken();
        }
        String url = String.format("%s?access_token=%s&openid=%s", WxApiConstant.WX_USERINFO_URL, accessToken, openId);
        log.info(url);
        HttpResult httpResult = Https.getInstance().get(url);
        String responseStr = httpResult.getBody();
        log.info(responseStr);
        WxUserResponse response = JSON.parseObject(responseStr, WxUserResponse.class);
        log.info("{}",response);

        return response;
    }

    /**
     * 刷新access_token
     * @return
     */
    @Override
    public WxAccessTokenResponse refreshAccessToken(String wxAppId, String refreshToken){
        String url = String.format("%s?appid=%s&grant_type=refresh_token&refresh_token=%s", WxApiConstant.WX_REFRESH_TOKEN_URL, wxAppId, refreshToken);
        log.info(url);
        HttpResult httpResult = Https.getInstance().get(url);
        String responseStr = httpResult.getBody();
        log.info(responseStr);
        WxAccessTokenResponse response = JSON.parseObject(responseStr, WxAccessTokenResponse.class);
        log.info("{}",response);
        return response;
    }
}
