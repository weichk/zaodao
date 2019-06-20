/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2018 All Rights Reserved.
 * create by zhike
 * date:2018-05-29
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.zaodao.platform.dto.OrderServiceConditionDto;
import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.service.manage.OrderServiceConditionService;
import com.acooly.zaodao.platform.dao.manage.OrderServiceConditionDao;
import com.acooly.zaodao.platform.entity.OrderServiceCondition;

import java.util.List;

/**
 * zd_order_service_condition Service实现
 *
 * Date: 2018-05-29 11:22:47
 *
 * @author zhike
 *
 */
@Service("orderServiceConditionService")
public class OrderServiceConditionServiceImpl extends EntityServiceImpl<OrderServiceCondition, OrderServiceConditionDao> implements OrderServiceConditionService {

    /**
     * 获取条件列表
     */
    @Override
    public List<OrderServiceCondition> getByFeeId(Long feeId) {
        return this.getEntityDao().getByFeeId(feeId);
    }

    /**
     * 按feeId删除记录
     */
    @Override
    public int removeByFeeId(Long feeId) {
        return this.getEntityDao().removeByFeeId(feeId);
    }
}
