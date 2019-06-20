/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-01
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.CustomerLanguage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 导游语种表 JPA Dao
 *
 * Date: 2017-06-01 17:44:07
 * @author zhike
 *
 */
public interface CustomerLanguageDao extends EntityJpaDao<CustomerLanguage, Long> {

    @Modifying
    @Query("delete from CustomerLanguage where userId = ?1")
    void deleteEntityByUserId(String userId);
}
