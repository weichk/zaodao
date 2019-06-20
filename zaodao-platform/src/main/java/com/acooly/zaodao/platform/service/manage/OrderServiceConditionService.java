/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2018 All Rights Reserved.
 * create by zhike
 * date:2018-05-29
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.OrderServiceConditionDto;
import com.acooly.zaodao.platform.entity.OrderServiceCondition;

import java.util.List;

/**
 * zd_order_service_condition Service接口
 *
 * Date: 2018-05-29 11:22:47
 * @author zhike
 *
 */
public interface OrderServiceConditionService extends EntityService<OrderServiceCondition> {

    /**
     * 获取条件列表
     */
    List<OrderServiceCondition> getByFeeId(Long feeId);

    /**
     * 按feeId删除记录
     */
    int removeByFeeId(Long feeId);
}
