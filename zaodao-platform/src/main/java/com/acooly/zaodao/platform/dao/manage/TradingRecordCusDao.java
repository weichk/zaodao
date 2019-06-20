/*
 * 修订记录:
 * zhike@yiji.com 2017-06-12 23:01 创建
 *
 */
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.entity.TradingRecord;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public interface TradingRecordCusDao {
    /**
     * 分页获取交易记录列表
     *
     * @param userId
     * @return
     */
    PageInfo<TradingRecord> getPageTradingRecordByUserId(PageInfo<TradingRecord> pageInfo, String userId, String tradingType,String startTime,String endTime);
}
