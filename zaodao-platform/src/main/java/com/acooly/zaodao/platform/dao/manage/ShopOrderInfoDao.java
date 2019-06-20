/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-15
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.ShopOrderInfo;

/**
 * 商城订单信息 JPA Dao
 *
 * Date: 2017-06-15 15:23:25
 * @author zhike
 *
 */
public interface ShopOrderInfoDao extends EntityJpaDao<ShopOrderInfo, Long> {

    ShopOrderInfo findByOrderNo(String orderNo);


    ShopOrderInfo findByUserName(String userName);
}
