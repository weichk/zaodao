/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 10:28 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.service;

import com.acooly.zaodao.gateway.gsy.message.*;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public interface GsyTradePayService {

    /**
     * 创建交易订单
     * @param request
     * @return
     */
    TradeCreateResponse tradeCreate(TradeCreateRequest request);

    /**
     * 支付宝扫码
     * @param request
     * @return
     */
    AliScanCodePayResponse aliScanCodePay(AliScanCodePayRequest request);

    /**
     * 微信扫码
     * @param request
     * @return
     */
    WechatScanCodePayResponse wechatScanCodePay(WechatScanCodePayRequest request);

    /**
     * 发送短息（提现的时候使用）
     * @param request
     * @return
     */
    SendCaptchaSmsResponse sendCaptchaSms(SendCaptchaSmsRequest request);
    /**
     * 提现
     * @param request
     * @return
     */
    WithdrawResponse withdraw(WithdrawRequest request);

    /**
     * 交易清分
     * @param request
     * @return
     */
    TradeProfitResponse tradeProfit(TradeProfitRequest request);

    /**
     * 余额支付
     * @param balancePayRequest
     * @return
     */
    BalancePayResponse balancePay(BalancePayRequest balancePayRequest);

    /**
     * 提现到卡(汇付到卡)
     */
    WithdrawCardResponse withdrawCard(WithdrawCardRequest request);

    /**
     * 查询充提订单(单笔)
     */
    FundQueryResponse fundQuery(FundQueryRequest request);
}
