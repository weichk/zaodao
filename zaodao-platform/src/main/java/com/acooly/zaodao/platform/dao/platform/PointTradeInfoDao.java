/*
 * 修订记录:
 * zhike@yiji.com 2017-06-19 11:30 创建
 *
 */
package com.acooly.zaodao.platform.dao.platform;

import com.acooly.module.point.domain.PointTrade;

import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public interface PointTradeInfoDao {
    /**
     *
     * @param userId
     * @param goodsId
     * @return
     */
    List<PointTrade> findByUserIdAndGoodsId(String userId, String goodsId);
}
