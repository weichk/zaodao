/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-15
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.ShopGoodsDetailDao;
import com.acooly.zaodao.platform.entity.ShopGoodsDetail;
import com.acooly.zaodao.platform.service.manage.ShopGoodsDetailService;
import org.springframework.stereotype.Service;

/**
 * 商品详情 Service实现
 *
 * Date: 2017-06-15 15:23:25
 *
 * @author zhike
 *
 */
@Service("shopGoodsDetailService")
public class ShopGoodsDetailServiceImpl extends EntityServiceImpl<ShopGoodsDetail, ShopGoodsDetailDao> implements ShopGoodsDetailService {

    @Override
    public ShopGoodsDetail getEntityByGoodsId(Long goodsId) {
        return getEntityDao().getEntityByGoodsId(goodsId);
    }
}
