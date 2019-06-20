package com.acooly.zaodao.gateway.gsy.message;

import lombok.Data;

/**
 * 微信用户信息
 *
 * @author xiaohong
 * @create 2017-12-18 17:08
 **/
@Data
public class WxUserResponse {
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String headimgurl;

    /**
     * 代码(发生错误才有值)
     */
    private String errcode;
    /**
     * 消息(发生错误才有值)
     */
    private String errmsg;
}
