/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-09
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.Area;

/**
 * 地区表 Service接口
 *
 * Date: 2017-06-09 15:43:25
 * @author zhike
 *
 */
public interface AreaService extends EntityService<Area> {

    Area findByCode(String code);
}
