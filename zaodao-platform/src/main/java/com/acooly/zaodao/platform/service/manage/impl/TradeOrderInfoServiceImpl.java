/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-08-09
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.gateway.gsy.message.FundQueryRequest;
import com.acooly.zaodao.gateway.gsy.message.FundQueryResponse;
import com.acooly.zaodao.gateway.gsy.message.WithdrawCardNotify;
import com.acooly.zaodao.gateway.gsy.message.dto.FundInfoDto;
import com.acooly.zaodao.gateway.gsy.message.enums.TradeStatusEnum;
import com.acooly.zaodao.gateway.gsy.service.GsyTradePayService;
import com.acooly.zaodao.platform.dao.manage.TradeOrderInfoDao;
import com.acooly.zaodao.platform.entity.TradeOrderInfo;
import com.acooly.zaodao.platform.enums.OrderStatusEnum;
import com.acooly.zaodao.platform.enums.OrderTradeTypeEnum;
import com.acooly.zaodao.platform.service.manage.TradeOrderInfoService;
import com.acooly.zaodao.platform.service.platform.GsyNotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 交易订单表 Service实现
 *
 * Date: 2017-08-09 11:10:35
 *
 * @author zhike
 *
 */
@Slf4j
@Service("tradeOrderInfoService")
public class TradeOrderInfoServiceImpl extends EntityServiceImpl<TradeOrderInfo, TradeOrderInfoDao> implements TradeOrderInfoService {

    @Override
    public TradeOrderInfo findByOrderNo(String orderNo) {
        return getEntityDao().findByOrderNo(orderNo);
    }

    /**
     * 根据交易订单号获取交易订单信息
     */
    @Override
    public TradeOrderInfo findByTradeOrderNo(String orderTradeNo) {
        return getEntityDao().findByOrderTradeNo(orderTradeNo);
    }

    @Autowired
    private GsyTradePayService gsyTradePayService;
    @Autowired
    private GsyNotifyService gsyNotifyService;
    /**
     * 检查汇付到卡异步通知
     */
    @Override
    public void checkWithdrawCard() {
        List<TradeOrderInfo> list = getEntityDao().getListByOrderStatusAndOrderTradeType(OrderStatusEnum.processing, OrderTradeTypeEnum.withdraw);
        log.info("检查汇付到卡异步通知,一共{}条记录", list == null ? 0 : list.size());
        //调用观世宇接口检查交易订单
        list.forEach(p -> {
            FundQueryRequest request = new FundQueryRequest();
            request.setOrigMerchOrdeNo(p.getOrderTradeNo());
            FundQueryResponse response = gsyTradePayService.fundQuery(request);

            FundInfoDto fundInfoDto = response.getFundInfo();
            if(null == fundInfoDto){
                log.info("观世宇未查询到交易订单{}的充提订单记录", request.getOrigMerchOrdeNo());
            }else {
                WithdrawCardNotify notify = new WithdrawCardNotify();
                notify.setMerchOrderNo(fundInfoDto.getMerchOrderNo());
                notify.setActualAmount(fundInfoDto.getActualAmount());
                notify.setAmount(fundInfoDto.getAmount());
                //观世宇的两类枚举值一致,所以直接赋值
                notify.setFundStatus(fundInfoDto.getStatus());
                notify.setTradeMemo(fundInfoDto.getTradeMemo());
                //执行汇付到卡回调逻辑
                gsyNotifyService.withdrawCard(notify);
            }
        });
    }
}
