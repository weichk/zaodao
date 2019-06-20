/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2018 All Rights Reserved.
 * create by zhike
 * date:2018-01-10
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.zaodao.platform.dao.manage.CustomerIngoreDao;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.enums.IngoreType;
import com.acooly.zaodao.platform.order.CustomerIngoreOrder;
import com.acooly.zaodao.platform.service.manage.CustomerIngoreService;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.entity.CustomerIngore;

/**
 * 用户屏蔽表 Service实现
 *
 * Date: 2018-01-10 10:10:27
 *
 * @author zhike
 *
 */
@Slf4j
@Service("customerIngoreService")
public class CustomerIngoreServiceImpl extends EntityServiceImpl<CustomerIngore, CustomerIngoreDao> implements CustomerIngoreService {
    @Autowired
    private CustomerService customerService;

    @Override
    public ResultBase addCustomerIngore(CustomerIngoreOrder order) {
        ResultBase result = new ResultBase();
        try {
            order.check();
            customerService.getUser(order.getUserId());
            customerService.getUser(order.getIngoreUserId());
            CustomerIngore customerIngore = new CustomerIngore();
            customerIngore.setUserId(order.getUserId());
            customerIngore.setIngoreUserId(order.getIngoreUserId());
            customerIngore.setIngoreType(IngoreType.travelVoice);
            this.getEntityDao().save(customerIngore);
            log.info("用户屏蔽成功");
        } catch (BusinessException ex) {
            log.info(String.format("用户屏蔽失败:%s", ex.getMessage()));
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(ex.getMessage());
        } catch (Exception e) {
            log.info(String.format("用户屏蔽出错:%s", e.getMessage()));
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("用户屏蔽出错");
        }
        return result;
    }
}
