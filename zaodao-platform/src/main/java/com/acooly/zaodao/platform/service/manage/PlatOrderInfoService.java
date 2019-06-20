/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-05-24
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityService;
import com.acooly.core.utils.Money;
import com.acooly.zaodao.platform.dto.OrderInfoDto;
import com.acooly.zaodao.platform.dto.TradingRecordDto;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.PlatOrderInfo;
import com.acooly.zaodao.platform.entity.TradeOrderInfo;
import com.acooly.zaodao.platform.enums.OrderStatusEnum;
import com.acooly.zaodao.platform.enums.OrderTradeTypeEnum;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import com.acooly.zaodao.platform.order.*;
import com.acooly.zaodao.platform.result.*;

import java.util.List;

/**
 * 订单表 Service接口
 *
 * Date: 2017-05-24 23:14:32
 * 
 * @author zhike
 *
 */
public interface PlatOrderInfoService extends EntityService<PlatOrderInfo> {

	List<PlatOrderInfo> findLatestOrder(String userId);

	/**
	 * 分页获取订单列表
	 *
	 * @param currentPageNo
	 * @param userId
	 * @return
	 */
	PageInfo<OrderInfoDto> getPageOrderInfoListByUserId(Integer currentPageNo, Integer countOfCurrentPage, String userId,String orderStatus,Integer isTourGuide);

    PlatOrderInfo findByOrderNo(String orderNo);

	/**
	 * 读取订单加行锁
	 */
	PlatOrderInfo findByOrderNoWithLock(String orderNo);

	/**
	 * 创建订单（并发处理）
	 * @param platOrderInfo
	 * @return
	 */
	PlatOrderInfo createOrder(PlatOrderInfo platOrderInfo);

	/**
	 * API创建订单
	 * @param plateOrderInfoOrder
	 * @return
	 */
	PlatOrderInfoResult createPlatOrder(PlatOrderInfoOrder plateOrderInfoOrder);
	/**
	 * 确认订单
	 * @param orderNo
	 * @return
	 */
	PlatOrderInfo confirm(Customer customer,String orderNo);

	/**
	 * 确认订单完成
	 */
	void confirmOrder(Customer customer,String orderNo);

	/**
	 * 关闭订单
	 */
	void closeOrder(PlatOrderInfo platOrderInfo, PlatOrderStatusChangeOrder order);

	/**
	 * 根据订单状态分页获取订单信息
	 * @param orderStatus
	 * @return
	 */
	PageInfo<PlatOrderInfo> getPageOrderInfoListByOrderStatus(Integer currentPageNo, Integer countOfCurrentPage, String orderStatus);

	/**
	 * 关闭订单
	 */
    ResultBase changePlatOrderStatus(PlatOrderStatusChangeOrder order);

	/**
	 * 获取订单列表
	 */
	PageResult<OrderInfoDto> getPagePlateOrderList(QueryPlateOrder queryPlateOrder);

	/**
	 * 支付订单
	 */
	PlatOrderPayResult payPlatOrder(PlatOrderPayOrder platOrderPayOrder);

	/**
	 * 获取订单详情
	 */
    PlatOrderDetailResult getPlatOrder(PlatOrderDetailOrder platOrderDetailOrder);

	/**
	 * 客户提现
	 */
	ResultBase withdrawPlat(WithdrawDepositOrder withdrawDepositOrder);

	/**
	 * 绑定银行卡
	 */
    ResultBase bindingCard(BindingCardOrder bindingCardOrder);

	/**
	 * 获取交易明细列表
	 */
    PageResult<TradingRecordDto> getTradeList(TradeListOrder tradeListOrder);

	/**
	 * 充值
	 * @param order
	 * @return
	 */
    DeductDepositResult deductDeposit(DeductDepositOrder order);

	/**
	 * 创建交易订单
	 */
	TradeOrderInfo getTradeOrderInfo(Customer customer, String orderNo, String orderTradeNo, Money amount, OrderTradeTypeEnum orderTradeType);

	/**
	 * 创建交易订单
	 */
	TradeOrderInfo getTradeOrderInfo(Customer customer, String orderNo, String orderTradeNo, Money amount, OrderTradeTypeEnum orderTradeType, OrderStatusEnum orderStatus);

	/**
	 * 钙片交易
	 * @param order
	 * @return
	 */
    ResultBase exchangeCaTrade(ExchangeCaTradeOrder order);

	/**
	 * 执行课程关闭定时任务
	 */
	void executePlatOrderCloseTask();

	/**
	 * 完成订单
	 * @param order
	 * @return
	 */
    ResultBase setPlatOrderFinish(PlatOrderFinishOrder order);

	/**
	 * 创建订单成功后，观世宇回调成功，添加早导网,导游和游客账务
	 */
	void createPlatOrderAccount(PlatOrderInfo platOrderInfo);

	/**
	 * 修改订单金额
	 */
	ResultBase modifyOrderAmount(AmountModifyOrder order);

	/**
	 * 订单完成客户为确认，给导游上账
	 */
    void guideAccountUp();

	/**
	 * 计算约导服务费
	 */
	Money getGuideServiceFee(PlatOrderInfo platOrderInfo);

	/**
	 * 获取订单个数统计
	 */
    PlatOrderCountResult getPlatOrderCount(String userId);
}
