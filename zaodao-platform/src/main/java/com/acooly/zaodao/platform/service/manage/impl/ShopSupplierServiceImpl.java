/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-15
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Collections3;
import com.acooly.zaodao.platform.dao.manage.ShopSupplierDao;
import com.acooly.zaodao.platform.entity.ShopSupplier;
import com.acooly.zaodao.platform.service.manage.ShopSupplierService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商城供货商 Service实现
 *
 * Date: 2017-06-15 16:02:42
 *
 * @author zhike
 *
 */
@Service("shopSupplierService")
public class ShopSupplierServiceImpl extends EntityServiceImpl<ShopSupplier, ShopSupplierDao> implements ShopSupplierService {

    @Override
    public ShopSupplier getByOptUser(String userName) {
        List<ShopSupplier> suppliers = getEntityDao().find("EQ_optUser", userName);
        if (Collections3.isEmpty(suppliers)) {
            return null;
        }
        return Collections3.getFirst(suppliers);
    }
}
