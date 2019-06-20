/*
 * 修订记录:
 * zhike@yiji.com 2017-06-16 20:39 创建
 *
 */
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.entity.ShopGoods;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public interface ShopGoodsCusDao {

    /**
     * 根据商品类型编码分页获取商品列表
     * @param pageInfo
     * @param goodsTypeCode
     * @return
     */
    PageInfo<ShopGoods> getPageShopGoodsByGoodTypeCode(PageInfo<ShopGoods> pageInfo, String goodsTypeCode);
}
