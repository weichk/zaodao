/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-12
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Dates;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.zaodao.platform.dao.manage.TradingRecordDao;
import com.acooly.zaodao.platform.dto.TradingRecordDto;
import com.acooly.zaodao.platform.entity.TradingRecord;
import com.acooly.zaodao.platform.order.TradeListOrder;
import com.acooly.zaodao.platform.service.manage.TradingRecordService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 交易记录 Service实现
 * <p>
 * Date: 2017-06-12 22:57:24
 *
 * @author zhike
 */
@Service("tradingRecordService")
public class TradingRecordServiceImpl extends EntityServiceImpl<TradingRecord, TradingRecordDao> implements TradingRecordService {

    @Override
    public PageInfo<TradingRecord> getPageTradingRecordByUserId(Integer currentPageNo, Integer countOfCurrentPage, String userId, String tradingType, String startTime, String endTime) {
        return getEntityDao().getPageTradingRecordByUserId(getMyPageInfo(currentPageNo, countOfCurrentPage), userId, tradingType, startTime, endTime);
    }

    /**
     * 获取交易明细列表
     * @param tradeListOrder
     * @return
     */
    @Override
    public PageInfo<TradingRecordDto> getPageTradingRecordList(TradeListOrder tradeListOrder) {
        PageInfo<TradingRecordDto> pageInfo = tradeListOrder.getPageInfo();
        String userId = tradeListOrder.getUserId();
        String tradeType = tradeListOrder.getTradingType()  == null ? "" : tradeListOrder.getTradingType().getCode();
        String startTime = tradeListOrder.getStartTime() == null ? "" : Dates.format(tradeListOrder.getStartTime(), "yyyy-MM-dd");
        String endTime = tradeListOrder.getEndTime() == null ? "" : Dates.format(tradeListOrder.getEndTime(), "yyyy-MM-dd");

        PageInfo<TradingRecord> tradingRecordPageInfo = getPageTradingRecordByUserId(pageInfo.getCurrentPage(), pageInfo.getCountOfCurrentPage(), userId, tradeType, startTime, endTime);
        List<TradingRecordDto> list = Lists.newArrayList();
        for (TradingRecord tradingRecord : tradingRecordPageInfo.getPageResults()){
            TradingRecordDto tradingRecordDto = new TradingRecordDto();
            BeanCopier.copy(tradingRecord, tradingRecordDto);
            tradingRecordDto.setTradingRecordId(tradingRecord.getId());
            list.add(tradingRecordDto);
        }
        pageInfo.setPageResults(list);

        return pageInfo;
    }
    /**
     * 获取分页对象
     *
     * @param currentPage
     * @return
     */
    private PageInfo<TradingRecord> getMyPageInfo(Integer currentPage, Integer countOfCurrentPage) {
        PageInfo<TradingRecord> pageinfo = new PageInfo<>();
        pageinfo.setCurrentPage(currentPage);
        pageinfo.setCountOfCurrentPage(countOfCurrentPage);
        return pageinfo;
    }

    @Override
    public TradingRecord findLastRecodeByUserId(String userId) {
        return getEntityDao().findLastRecodeByUserId(userId);
    }

    @Override
    public TradingRecord findRecodeByUserIdAndOrderNo(String userId, Long orderId) {
        return getEntityDao().findByUserIdAndOrderId(userId,orderId);
    }
}
