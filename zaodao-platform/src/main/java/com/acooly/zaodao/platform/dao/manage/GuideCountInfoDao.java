/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-30
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.GuideCountInfo;

/**
 * 导游订单统计表 JPA Dao
 * <p>
 * Date: 2017-05-30 11:07:30
 *
 * @author zhike
 */
public interface GuideCountInfoDao extends EntityJpaDao<GuideCountInfo, Long> {

    GuideCountInfo findByUserId(String userId);
}
