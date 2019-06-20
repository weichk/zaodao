/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-08-09
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.TradeOrderInfo;

/**
 * 交易订单表 Service接口
 *
 * Date: 2017-08-09 11:10:35
 * @author zhike
 *
 */
public interface TradeOrderInfoService extends EntityService<TradeOrderInfo> {

    /**
     * 根据订单号获取交易订单信息
     */
    TradeOrderInfo findByOrderNo(String orderNo);

    /**
     * 根据交易订单号获取交易订单信息
     */
    TradeOrderInfo findByTradeOrderNo(String merchOrderNo);

    /**
     * 检查汇付到卡异步通知
     */
    void checkWithdrawCard();
}
