/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-08-09
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.TradeOrderInfo;
import com.acooly.zaodao.platform.enums.OrderStatusEnum;
import com.acooly.zaodao.platform.enums.OrderTradeTypeEnum;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * 交易订单表 JPA Dao
 * <p>
 * Date: 2017-08-09 11:10:35
 *
 * @author zhike
 */
public interface TradeOrderInfoDao extends EntityJpaDao<TradeOrderInfo, Long> {
    /**
     * 根据订单号获取订单信息
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    TradeOrderInfo findByOrderNo(String orderNo);

    /**
     * 根据交易订单号获取交易订单信息
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    TradeOrderInfo findByOrderTradeNo(String orderTradeNo);

    /**
     * 获取交易订单列表
     */
    List<TradeOrderInfo> getListByOrderStatusAndOrderTradeType(OrderStatusEnum orderStatus, OrderTradeTypeEnum orderTradeType);
}
