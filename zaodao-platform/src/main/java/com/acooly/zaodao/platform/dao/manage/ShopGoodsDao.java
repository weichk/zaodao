/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-15
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.ShopGoods;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 商品信息 JPA Dao
 *
 * Date: 2017-06-15 15:23:25
 * @author zhike
 *
 */
public interface ShopGoodsDao extends EntityJpaDao<ShopGoods, Long>,ShopGoodsCusDao {

    /**
     * 根据类型编码获取商品列表
     * @param code
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT * FROM zd_shop_goods zsg WHERE zsg.type_id IN (SELECT id FROM zd_shop_goods_type zsgt WHERE zsgt.code LIKE ?1%) AND (zsg.validity_date >= NOW() OR zsg.validity_date is NULL) limit ?2")
    List<ShopGoods> findByTypeCodeStartWith(String code,Integer count);

    @Query(nativeQuery = true,value = "SELECT * FROM zd_shop_goods WHERE is_hot=1 AND (validity_date >= NOW() OR validity_date is NULL) ORDER BY create_time DESC limit ?1")
    List<ShopGoods> findHotGoods(Integer count);
}
