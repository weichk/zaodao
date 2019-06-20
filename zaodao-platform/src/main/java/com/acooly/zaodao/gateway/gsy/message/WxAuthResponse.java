package com.acooly.zaodao.gateway.gsy.message;

import lombok.Data;

/**
 * 微信检验授权凭证
 *
 * @author xiaohong
 * @create 2017-12-14 16:36
 **/
@Data
public class WxAuthResponse {
    /**
     * 代码(0:成功)
     */
    private String errcode;
    /**
     * 消息(ok:成功)
     */
    private String errmsg;
}
