package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.zaodao.gateway.base.RequestBase;
import lombok.Data;

/**
 * 观世宇余额支付请求
 * @author xiaohong
 * @create 2017-11-28 20:23
 **/
@Data
public class BalancePayRequest extends RequestBase {
    /**
     * 付款人商户用户id
     */
    private String payerMerchUserId;

    /**
     * 用户ip
     */
    private String userIp;
}
