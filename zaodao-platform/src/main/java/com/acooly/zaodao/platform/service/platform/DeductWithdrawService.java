/*
 * 修订记录:
 * zhike@yiji.com 2017-08-09 14:38 创建
 *
 */
package com.acooly.zaodao.platform.service.platform;

import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.service.platform.base.DepositResult;
import com.acooly.zaodao.platform.service.platform.base.WithdrawResult;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public interface DeductWithdrawService {

    /**
     * 创建交易
     * @param customer
     * @param amount
     * @return
     */
    String tradeCreate(Customer customer, String amount);

    /**
     * 充值
     * @param orderNo
     * @return
     */
    DepositResult deposit(String orderNo,String payType);

    /**
     * 提现
     * @param customer
     * @param amount
     * @param captchaCode
     * @return
     */
    WithdrawResult withdraw(Customer customer,String amount,String captchaCode);
}
