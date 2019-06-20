/*
 * 修订记录:
 * zhike@yiji.com 2017-08-10 10:21 创建
 *
 */
package com.acooly.zaodao.platform.service.platform;

import com.acooly.core.utils.Money;
import com.acooly.zaodao.account.entity.Account;
import com.acooly.zaodao.gateway.gsy.message.TradeCreateNotify;
import com.acooly.zaodao.gateway.gsy.message.WithdrawCardNotify;
import com.acooly.zaodao.gateway.gsy.message.WithdrawNotify;
import com.acooly.zaodao.platform.enums.TradeBusiness;
import com.acooly.zaodao.platform.enums.TradeMethod;
import com.acooly.zaodao.platform.enums.TradingType;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public interface GsyNotifyService {
//    /**
//     * 获取客户在观世宇平台账户余额
//     * @param userId
//     * @return
//     */
//    UserBalanceDto getCustomerBalanceGsy(String userId);
    /**
     * 提现异步通知处理
     * @param notify
     */
    void withdraw(WithdrawNotify notify);

//    /**
//     * 交易充值异步处理
//     * @param notify
//     */
//    void tradeCreate(TradeCreateNotify notify);

    /**
     * 写交易记录表
     */
    void writePositTradingRecord(Account account, Money money, TradingType tradingType, String tradeType, Long orderId, TradeBusiness tradeBusiness);

    /**
     * 支付回调处理
     */
    void payNotify(TradeCreateNotify notify, TradeMethod method);

    /**
     * 提现到卡(汇付到卡)
     */
    void withdrawCard(WithdrawCardNotify notify);
}
