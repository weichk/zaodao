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
import com.acooly.zaodao.platform.entity.ShopGoods;

import java.util.List;

/**
 * 商品信息 Service接口
 *
 * Date: 2017-06-15 15:23:24
 * @author zhike
 *
 */
public interface ShopGoodsService extends EntityService<ShopGoods> {
    /**
     * 根据类型编码获取商品列表
     * @param code
     * @return
     */
    List<ShopGoods> findByTypeCodeStartWith(String code,Integer count);

    /**
     * 获取热门推荐商品列表
     * @param count
     * @return
     */
    List<ShopGoods> findHotGoods(Integer count);

    /**
     * 根据商品类型编码分页获取商品列表
     * @param goodsTypeCode
     * @return
     */
    PageInfo<ShopGoods> getPageShopGoodsByGoodTypeCode(Integer currentPageNo, Integer countOfCurrentPage, String goodsTypeCode);
}
