/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2018 All Rights Reserved.
 * create by zhike
 * date:2018-01-10
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.CustomerIngore;
import com.acooly.zaodao.platform.order.CustomerIngoreOrder;

/**
 * 用户屏蔽表 Service接口
 *
 * Date: 2018-01-10 10:10:27
 * @author zhike
 *
 */
public interface CustomerIngoreService extends EntityService<CustomerIngore> {

    /**
     * 添加用户屏蔽
     * @param order
     * @return
     */
    ResultBase addCustomerIngore(CustomerIngoreOrder order);
}
