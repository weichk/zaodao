/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-07-17
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.RedBag;
import com.acooly.zaodao.platform.entity.SecretAnswer;
import org.springframework.data.jpa.repository.Query;

/**
 * 红包表 JPA Dao
 * <p>
 * Date: 2017-07-17 22:06:48
 *
 * @author zhike
 *
 */
public interface RedBagDao extends EntityJpaDao<RedBag, Long> {

    @Query("from RedBag where userId = ?1 AND refId = ?2")
    RedBag getEntityByUserIdAndRefId(String userId, Long refId);
}
