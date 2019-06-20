/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-09
 */
package com.acooly.zaodao.platform.service.manage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.AreaDao;
import com.acooly.zaodao.platform.entity.Area;
import com.acooly.zaodao.platform.service.manage.AreaService;

/**
 * 地区表 Service实现
 *
 * Date: 2017-06-09 15:43:25
 *
 * @author zhike
 *
 */
@Service("areaService")
public class AreaServiceImpl extends EntityServiceImpl<Area, AreaDao> implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public Area findByCode(String code) {
        return areaDao.findByCode(code);
    }
}
