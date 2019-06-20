/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-15
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.ShopSupplier;

/**
 * 商城供货商 Service接口
 *
 * Date: 2017-06-15 16:02:41
 * @author zhike
 *
 */
public interface ShopSupplierService extends EntityService<ShopSupplier> {

    ShopSupplier getByOptUser(String userName);
}
