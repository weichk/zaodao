/*
 * 修订记录:
 * zhike@yiji.com 2017-08-15 14:52 创建
 *
 */
package com.acooly.zaodao.platform.service.platform;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public interface ZaodaoTaskService {

    /**
     * 订单清分定时任务
     */
    void tradeProfitTask();

    /**
     * 发布课程定时任务
     */
    void coursePublishTask();

    /**
     * 课程截止定时任务
     */
    void executeEnrolCourse();

    /**
     * 订单支付超时关闭定时任务
     */
    void platOrderCloseTask();

    /**
     * 订单完成客户未确认，给导游上账
     */
    void guideAccountUp();

    /**
     * 检查汇付到卡异步通知
     */
    void checkWithdrawCard();
}
