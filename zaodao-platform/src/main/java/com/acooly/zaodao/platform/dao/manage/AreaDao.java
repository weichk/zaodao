/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-09
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.Area;

/**
 * 地区表 JPA Dao
 *
 * Date: 2017-06-09 15:43:25
 * 
 * @author zhike
 *
 */
public interface AreaDao extends EntityJpaDao<Area, Long> {

	Area findByCode(String code);
}
