/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-12-01
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.module.portlet.entity.Feedback;
import com.acooly.zaodao.platform.dao.manage.CustomerFeedbackDao;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.order.FeedbackApplyOrder;
import com.acooly.zaodao.platform.service.manage.CustomerFeedbackService;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acooly.zaodao.platform.entity.CustomerFeedback;

/**
 * 用户反馈 Service实现
 *
 * Date: 2017-12-01 10:14:19
 *
 * @author zhike
 *
 */
@Slf4j
@Service("customerFeedbackService")
public class CustomerFeedbackServiceImpl extends EntityServiceImpl<CustomerFeedback, CustomerFeedbackDao> implements CustomerFeedbackService {
    @Autowired
    private CustomerService customerService;
    /**
     * 添加用户反馈
     * @param order
     * @return
     */
    @Override
    public ResultBase feedbackApplyAdd(FeedbackApplyOrder order) {
        ResultBase resultBase = new ResultBase();

        try {
            order.check();
            customerService.getUser(order.getUserId());
            CustomerFeedback customerFeedback = new CustomerFeedback();
            customerFeedback.setUserId(order.getUserId());
            customerFeedback.setTitle(order.getTitle());
            customerFeedback.setContent(order.getContent());
            this.getEntityDao().save(customerFeedback);
        }catch (BusinessException e){
            log.info("添加用户反馈失败！{}", e.getMessage());
            resultBase.setStatus(ResultStatus.failure);
            resultBase.setCode(ResultStatus.failure.getCode());
            resultBase.setDetail(e.getMessage());
        }catch (Exception e) {
            log.info("系统错误！{}", e.getMessage());
            resultBase.setStatus(ResultStatus.failure);
            resultBase.setCode(ResultStatus.failure.getCode());
            resultBase.setDetail("系统错误！");
        }

        return resultBase;

    }
}
