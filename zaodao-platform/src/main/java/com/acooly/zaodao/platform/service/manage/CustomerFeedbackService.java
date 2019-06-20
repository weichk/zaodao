/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-12-01
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.CustomerFeedback;
import com.acooly.zaodao.platform.order.FeedbackApplyOrder;

/**
 * 用户反馈 Service接口
 *
 * Date: 2017-12-01 10:14:19
 * @author zhike
 *
 */
public interface CustomerFeedbackService extends EntityService<CustomerFeedback> {

    /**
     * 添加用户反馈
     * @param order
     * @return
     */
    ResultBase feedbackApplyAdd(FeedbackApplyOrder order);
}
