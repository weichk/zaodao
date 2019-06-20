/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-15
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.ShopGoodsDetail;
import org.springframework.data.jpa.repository.Query;

/**
 * 商品详情 JPA Dao
 *
 * Date: 2017-06-15 15:23:25
 * @author zhike
 *
 */
public interface ShopGoodsDetailDao extends EntityJpaDao<ShopGoodsDetail, Long> {

    @Query("from ShopGoodsDetail where goodsId =?1")
    ShopGoodsDetail getEntityByGoodsId(Long goodsId);
}
