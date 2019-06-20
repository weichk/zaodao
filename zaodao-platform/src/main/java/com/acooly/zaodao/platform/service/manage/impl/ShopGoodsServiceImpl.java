/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-15
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.ShopGoodsDao;
import com.acooly.zaodao.platform.entity.ShopGoods;
import com.acooly.zaodao.platform.service.manage.ShopGoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品信息 Service实现
 *
 * Date: 2017-06-15 15:23:25
 *
 * @author zhike
 *
 */
@Service("shopGoodsService")
public class ShopGoodsServiceImpl extends EntityServiceImpl<ShopGoods, ShopGoodsDao> implements ShopGoodsService {

    @Override
    public List<ShopGoods> findByTypeCodeStartWith(String code,Integer count) {
        return getEntityDao().findByTypeCodeStartWith(code,count);
    }

    @Override
    public List<ShopGoods> findHotGoods(Integer count) {
        return getEntityDao().findHotGoods(count);
    }

    @Override
    public PageInfo<ShopGoods> getPageShopGoodsByGoodTypeCode(Integer currentPageNo, Integer countOfCurrentPage, String goodsTypeCode) {
        return getEntityDao().getPageShopGoodsByGoodTypeCode(getMyPageInfo(currentPageNo,countOfCurrentPage),goodsTypeCode);
    }


    /**
     * 获取分页对象
     *
     * @param currentPage
     * @return
     */
    private PageInfo<ShopGoods> getMyPageInfo(Integer currentPage, Integer countOfCurrentPage) {
        PageInfo<ShopGoods> pageinfo = new PageInfo<>();
        pageinfo.setCurrentPage(currentPage);
        pageinfo.setCountOfCurrentPage(countOfCurrentPage);
        return pageinfo;
    }
}
