/*
 * 修订记录:
 * zhike@yiji.com 2017-08-15 14:53 创建
 *
 */
package com.acooly.zaodao.platform.service.platform.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.zaodao.gateway.gsy.message.TradeProfitRequest;
import com.acooly.zaodao.gateway.gsy.message.TradeProfitResponse;
import com.acooly.zaodao.gateway.gsy.service.GsyBusinessService;
import com.acooly.zaodao.gateway.gsy.service.GsyTradePayService;
import com.acooly.zaodao.platform.entity.PlatOrderInfo;
import com.acooly.zaodao.platform.entity.TradingRecord;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import com.acooly.zaodao.platform.service.manage.CourseService;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import com.acooly.zaodao.platform.service.manage.TradeOrderInfoService;
import com.acooly.zaodao.platform.service.manage.TradingRecordService;
import com.acooly.zaodao.platform.service.platform.ZaodaoTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Service("zaodaoTaskService")
@Slf4j
public class ZaodaoTaskServiceImpl implements ZaodaoTaskService {

    @Autowired
    private PlatOrderInfoService platOrderInfoService;

    @Autowired
    private GsyTradePayService gsyTradePayService;

    @Autowired
    private TradingRecordService tradingRecordService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TradeOrderInfoService tradeOrderInfoService;

    private Integer currentPageNo = 1;

    @Override
    public void tradeProfitTask() {
        Integer countOfCurrentPage = 100;
        PageInfo<PlatOrderInfo> platOrderInfoPageInfo = platOrderInfoService.getPageOrderInfoListByOrderStatus(currentPageNo, countOfCurrentPage, "pay");
        tradeProfit(platOrderInfoPageInfo.getPageResults());
        if (platOrderInfoPageInfo.getTotalPage() > currentPageNo) {
            currentPageNo++;
            tradeProfitTask();
        } else {
            currentPageNo = 1;
        }

    }

    /**
     * 交易清分执行
     */
    private void tradeProfit(List<PlatOrderInfo> platOrderInfos) {
        for (PlatOrderInfo platOrderInfo : platOrderInfos) {
            //交易清分
            TradeProfitRequest request = new TradeProfitRequest();
            request.setMerchOrderNo(platOrderInfo.getOrderNo());
            TradeProfitResponse response = gsyTradePayService.tradeProfit(request);
            if (response.getStatus() == ResultStatus.success || response.getStatus() == ResultStatus.processing) {
                platOrderInfo.setOrderStatus(PlatOrderInfoOrderStatus.confirm);
                platOrderInfoService.update(platOrderInfo);
                //更新交易记录余额字段
                TradingRecord lastRecord = tradingRecordService.findLastRecodeByUserId(platOrderInfo.getTourGuideId());
                Long oldBalance = 0L;
                if (lastRecord != null) {
                    oldBalance = lastRecord.getBalance();
                }
                TradingRecord tradingRecord = tradingRecordService.findRecodeByUserIdAndOrderNo(platOrderInfo.getTourGuideId(), platOrderInfo.getId());
                tradingRecord.setBalance(oldBalance + response.getAmount().getCent() - response.getProfitAmount().getCent());
                tradingRecordService.update(tradingRecord);
            } else {
                log.info("订单orderNo={}清分失败", platOrderInfo.getOrderNo());
            }

        }
    }

    /**
     * 发布课程定时任务
     */
    @Override
    public void coursePublishTask() {
        log.info("开始执行发布课程定时任务...");
        courseService.executePublishTask();
        log.info("结束执行发布课程定时任务");
    }

    /**
     * 超过报名截止时间，将报名中状态的报名课程修改为报名截止
     */
    @Override
    public void executeEnrolCourse(){
        log.info("开始执行报名截止定时任务...");
        courseService.executeEnrolCourse();
        log.info("结束执行报名截止定时任务");
    }

    /**
     * 订单支付超时关闭定时任务
     */
    @Override
    public void platOrderCloseTask() {
        log.info("开始执行处理订单超时定时任务...");
        platOrderInfoService.executePlatOrderCloseTask();
        log.info("结束执行处理订单超时任务");
    }

    /**
     * 订单完成客户为确认，给导游上账
     */
    @Override
    public void guideAccountUp() {
        log.info("开始执行处理导游上账定时任务...");
        platOrderInfoService.guideAccountUp();
        log.info("结束执行处理导游上账任务");
    }

    /**
     * 检查汇付到卡异步通知
     */
    @Override
    public void checkWithdrawCard() {
        log.info("开始执行处理检查汇付到卡异步通知定时任务...");
        tradeOrderInfoService.checkWithdrawCard();
        log.info("结束执行处理检查汇付到卡异步通知定时任务...");
    }
}
