/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-12
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.TradingRecord;
import org.springframework.data.jpa.repository.Query;

/**
 * 交易记录 JPA Dao
 *
 * Date: 2017-06-12 22:57:24
 * @author zhike
 *
 */
public interface TradingRecordDao extends EntityJpaDao<TradingRecord, Long>,TradingRecordCusDao {

    /**
     * 获取会员最后一条交易记录
     * @param userId
     * @return
     */
    @Query(value = "SELECT * FROM zd_trading_record where userId = ?1 ORDER BY create_time DESC LIMIT 1", nativeQuery = true)
    TradingRecord findLastRecodeByUserId(String userId);

    /**
     * 根据用户Id和订单Id查询交易记录
     * @param userId
     * @param orderId
     * @return
     */
    @Query(value = "FROM TradingRecord where userid = ?1 and orderId = ?2")
    TradingRecord findByUserIdAndOrderId(String userId,Long orderId);
}
