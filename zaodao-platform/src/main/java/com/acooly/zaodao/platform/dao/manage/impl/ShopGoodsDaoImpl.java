/*
 * 修订记录:
 * zhike@yiji.com 2017-06-16 20:40 创建
 *
 */
package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.ShopGoodsCusDao;
import com.acooly.zaodao.platform.entity.ShopGoods;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public class ShopGoodsDaoImpl extends AbstractJdbcTemplateDao implements ShopGoodsCusDao{

    @Override
    public PageInfo<ShopGoods> getPageShopGoodsByGoodTypeCode(PageInfo<ShopGoods> pageInfo, String goodsTypeCode) {
        String sql = "SELECT zsg.* FROM zd_shop_goods zsg WHERE  zsg.type_id IN (SELECT id FROM zd_shop_goods_type zsgt WHERE zsgt.code LIKE '"+goodsTypeCode+"%')" +
                " AND (validity_date >= NOW() OR validity_date is NULL) ORDER BY zsg.create_time desc";
        PageInfo<ShopGoods> result = query(pageInfo, sql, ShopGoods.class);
        return result;
    }
}
