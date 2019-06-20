/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-15
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.ShopGoodsType;

import java.util.List;

/**
 * 商品分类 Service接口
 *
 * Date: 2017-06-15 15:23:25
 * @author zhike
 *
 */
public interface ShopGoodsTypeService extends EntityService<ShopGoodsType> {

    ShopGoodsType create(Long parentId, String name, String comments);

    List<ShopGoodsType> loadTree(Long parentId);
}
