package com.acooly.zaodao.platform.service.manage;

import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.order.RewardOrder;
import com.acooly.zaodao.platform.result.RewardResult;

/**
 * Created by xiaohong on 2017/9/27.
 */
public interface RewardService {
    /**
     * 获取打赏结果
     * @param articleRewardOrder
     * @return
     */
    RewardResult getReward(RewardOrder articleRewardOrder);

    /**
     * 处理积分
     * @param rewardOrder
     * @param customer
     */
    void executePointTrade(RewardOrder rewardOrder, Customer customer);
    /**
     * 把积分从一个人分配给另一个人
     * @param rewardUserId 被分配者
     * @param customerId 分配者
     * @param point 积分数量
     * @param busiType 分配类型
     * @param busiTypeText 分配类型说明
     */
    void executePointTrade(String rewardUserId, String customerId, long point, String busiType, String busiTypeText);
}
