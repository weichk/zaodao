/*
 * 修订记录:
 * zhike@yiji.com 2017-06-20 22:24 创建
 *
 */
package com.acooly.zaodao.platform.service.platform.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Ids;
import com.acooly.module.point.domain.PointAccount;
import com.acooly.module.point.domain.PointTrade;
import com.acooly.module.point.enums.PointTradeType;
import com.acooly.module.point.service.PointAccountService;
import com.acooly.module.point.service.PointTradeService;
import com.acooly.zaodao.platform.dao.platform.PointTradeInfoDao;
import com.acooly.zaodao.platform.entity.ShopGoods;
import com.acooly.zaodao.platform.entity.ShopOrderInfo;
import com.acooly.zaodao.platform.enums.ShopOrderStatus;
import com.acooly.zaodao.platform.service.manage.ShopGoodsService;
import com.acooly.zaodao.platform.service.manage.ShopOrderInfoService;
import com.acooly.zaodao.platform.service.platform.PointTradeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Service
public class PointTradeInfoServiceImpl implements PointTradeInfoService {

    @Resource
    private PointTradeInfoDao pointTradeInfoDao;

    @Autowired
    private PointAccountService pointAccountService;

    @Autowired
    private ShopGoodsService shopGoodsService;

    @Autowired
    private ShopOrderInfoService shopOrderInfoService;

    @Autowired
    private PointTradeService pointTradeService;

    @Override
    public List<PointTrade> findByUserIdAndGoodsId(String userId, String goodsId) {
        return pointTradeInfoDao.findByUserIdAndGoodsId(userId, goodsId);
    }

    @Transactional
    @Override
    public void consumePoint(Long accountId, ShopOrderInfo orderInfo, long amount) {
        try {
            PointAccount account = pointAccountService.get(accountId);
            ShopGoods goods = shopGoodsService.get(orderInfo.getGoodsId());
            orderInfo.setOrderNo(Ids.oid());
            orderInfo.setUpdateTime(orderInfo.getCreateTime());
            orderInfo.setStatus(ShopOrderStatus.Paid.getCode());
            shopOrderInfoService.save(orderInfo);
            goods.setSellQuantity(goods.getSellQuantity() + orderInfo.getQuantity());
            goods.setUpdateTime(new Date());
            shopGoodsService.save(goods);
            account = pointAccountService.pointExpense(orderInfo.getUserName(), amount, false);
            saveOrderTrade(account, amount, goods.getId(), goods.getName(),goods.getDeliveryType());
//            sendNoticeSuppler(orderInfo);
        } catch (Exception e) {
            throw new BusinessException("消费:" + e.getMessage());
        }
    }


    @Transactional
    public PointTrade saveOrderTrade(PointAccount account, long amount, Long goodId, String goodsName, Integer deliveryType) {
        PointTrade tradeInfo = new PointTrade();
        tradeInfo.setAccountId(account.getId());
        tradeInfo.setEndBalance(account.getBalance());
        tradeInfo.setAmount(amount);
        tradeInfo.setUserName(account.getUserName());
        tradeInfo.setTradeNo(getTradeNo(PointTradeType.produce));
        tradeInfo.setTradeType(PointTradeType.produce);
        tradeInfo.setBusiId(goodId + "");
        tradeInfo.setBusiType(deliveryType+"");
        tradeInfo.setBusiTypeText(deliveryType == 1 ? "实物购买":"虚拟商品购买");
        tradeInfo.setBusiData(goodsName);
        pointTradeService.save(tradeInfo);
        return tradeInfo;
    }

    protected String getTradeNo(PointTradeType trade) {
        return (trade == PointTradeType.produce ? "I" : "O")+ Ids.oid();
    }
}
