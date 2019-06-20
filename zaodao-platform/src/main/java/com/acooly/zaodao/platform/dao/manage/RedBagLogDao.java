/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-07-18
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.RedBag;
import com.acooly.zaodao.platform.entity.RedBagLog;
import org.springframework.data.jpa.repository.Query;

/**
 * 红包领取记录表 JPA Dao
 *
 * Date: 2017-07-18 09:42:32
 * @author zhike
 *
 */
public interface RedBagLogDao extends EntityJpaDao<RedBagLog, Long> {

    @Query("from RedBagLog where userId = ?1 AND refId = ?2")
    RedBagLog getEntityByUserIdAndRefId(String userId, Long refId);
}
