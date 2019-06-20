/*
 * 修订记录:
 * zhike@yiji.com 2017-06-20 22:23 创建
 *
 */
package com.acooly.zaodao.platform.service.platform;

import com.acooly.module.point.domain.PointAccount;
import com.acooly.module.point.domain.PointTrade;
import com.acooly.zaodao.platform.entity.ShopOrderInfo;

import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public interface PointTradeInfoService {

    /**
     * 更具用户id和商品ID获取交易列表
     * @param userId
     * @param goodsId
     * @return
     */
    List<PointTrade> findByUserIdAndGoodsId(String userId, String goodsId);

    void consumePoint(Long accountId, ShopOrderInfo orderInfo, long amount);

    /**
     * 保存订单信息
     *
     * @param account
     * @param goodId
     * @param goodsName
     * @param amount
     * @param orderNo
     * @param comments
     * @return
     */
     PointTrade saveOrderTrade(PointAccount account, long amount, Long goodId, String goodsName,Integer deliveryType);
}
