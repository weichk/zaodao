/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-15
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.ShopOrderInfo;

/**
 * 商城订单信息 Service接口
 *
 * Date: 2017-06-15 15:23:25
 * @author zhike
 *
 */
public interface ShopOrderInfoService extends EntityService<ShopOrderInfo> {

    ShopOrderInfo findByOrderNo(String orderNo);

    ShopOrderInfo findById(long parseLong);

    PageInfo<ShopOrderInfo> queryPageByUserName(Integer currentPage, Integer countOfCurrentPage, String userName);
}
