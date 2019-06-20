/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-12
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.TradingRecordDto;
import com.acooly.zaodao.platform.entity.TradingRecord;
import com.acooly.zaodao.platform.order.TradeListOrder;

/**
 * 交易记录 Service接口
 *
 * Date: 2017-06-12 22:57:24
 * @author zhike
 *
 */
public interface TradingRecordService extends EntityService<TradingRecord> {

    /**
     * 分页获取交易记录列表
     *
     * @param userId
     * @return
     */
    PageInfo<TradingRecord> getPageTradingRecordByUserId(Integer currentPageNo, Integer countOfCurrentPage, String userId, String tradingType,String startTime,String endTime);

    /**
     * 获取会员最后一条交易记录
     * @param userId
     * @return
     */
    TradingRecord findLastRecodeByUserId(String userId);

    /**
     * 根据用户ID和交易订单ID查询交易记录
     * @param userId
     * @param orderId
     * @return
     */
    TradingRecord findRecodeByUserIdAndOrderNo(String userId,Long orderId);

    /**
     * 获取交易列表
     * @param tradeListOrder
     * @return
     */
    PageInfo<TradingRecordDto> getPageTradingRecordList(TradeListOrder tradeListOrder);
}
