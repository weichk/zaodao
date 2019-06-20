package com.acooly.zaodao.openapi.service.article;

import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.article.RewardRequest;
import com.acooly.zaodao.openapi.message.article.RewardResponse;
import com.acooly.zaodao.platform.order.RewardOrder;
import com.acooly.zaodao.platform.result.RewardResult;
import com.acooly.zaodao.platform.service.manage.RewardService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xiaohong on 2017/9/27.
 */
@OpenApiService(name = "reward", desc = "打赏", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_reward", name = "打赏")
public class RewardApiService extends BaseApiService<RewardRequest, RewardResponse> {
    @Autowired
    private RewardService rewardService;

    @Override
    protected void doService(RewardRequest rewardRequest, RewardResponse rewardResponse) {
        RewardOrder articleRewardOrder = rewardRequest.toOrder(RewardOrder.class);
        BeanCopier.copy(rewardRequest, articleRewardOrder);
        articleRewardOrder.setGid(Ids.gid());
        RewardResult rewardResult = rewardService.getReward(articleRewardOrder);
        rewardResult.throwExceptionIfNotSuccess();
    }
}
