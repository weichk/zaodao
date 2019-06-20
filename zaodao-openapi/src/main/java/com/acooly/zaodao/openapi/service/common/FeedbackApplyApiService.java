/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 10:56 创建
 *
 */
package com.acooly.zaodao.openapi.service.common;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.zaodao.openapi.message.common.FeedbackApplyRequest;
import com.acooly.zaodao.openapi.message.common.FeedbackApplyResponse;
import com.acooly.zaodao.platform.order.FeedbackApplyOrder;
import com.acooly.zaodao.platform.service.manage.CustomerFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiService(name = "feedbackApply", desc = "意见反馈", responseType = ResponseType.SYN)
@ApiDocType(code = "zd_common", name = "通用解决方案")
public class FeedbackApplyApiService extends BaseApiService<FeedbackApplyRequest, FeedbackApplyResponse> {
    @Autowired
    private CustomerFeedbackService customerFeedbackService;

    @Override
    protected void doService(FeedbackApplyRequest request, FeedbackApplyResponse response) {
        FeedbackApplyOrder order = request.toOrder(FeedbackApplyOrder.class);
        order.setGid(Ids.gid());
        ResultBase resultBase = customerFeedbackService.feedbackApplyAdd(order);
        resultBase.throwExceptionIfNotSuccess();
    }
}
