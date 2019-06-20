/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-15
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.ShopGoodsType;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 商品分类 JPA Dao
 *
 * Date: 2017-06-15 15:23:25
 * @author zhike
 *
 */
public interface ShopGoodsTypeDao extends EntityJpaDao<ShopGoodsType, Long> {

    @Query(nativeQuery = true, value = "select max(code) from zd_shop_goods_type where parent_id = ?1")
    String getMaxCode(Long parentId);
    @Query(nativeQuery = true, value = "select max(code) from zd_shop_goods_type where ISNULL(parent_id)")
    String getTopMaxCode();

    List<ShopGoodsType> findByCodeStartingWith(String codePrefix);

    @Query("from ShopGoodsType where parentId is null")
    List<ShopGoodsType> getTops();
}
