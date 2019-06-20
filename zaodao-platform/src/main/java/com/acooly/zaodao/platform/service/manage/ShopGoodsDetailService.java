/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-15
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.ShopGoodsDetail;

/**
 * 商品详情 Service接口
 *
 * Date: 2017-06-15 15:23:25
 * @author zhike
 *
 */
public interface ShopGoodsDetailService extends EntityService<ShopGoodsDetail> {

    ShopGoodsDetail getEntityByGoodsId(Long goodsId);
}
