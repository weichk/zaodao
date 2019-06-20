/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-07
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.CustomerAlbum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 用户相册表 JPA Dao
 *
 * Date: 2017-06-07 11:37:17
 * @author zhike
 *
 */
public interface CustomerAlbumDao extends EntityJpaDao<CustomerAlbum, Long> {

    @Modifying
    @Query("DELETE FROM CustomerAlbum WHERE userId = ?1 and type = 1")
    void deleteDefaulCustomerAlbumByUserId(String userId);

    @Query("FROM CustomerAlbum WHERE userId = ?1 and type = 1")
    CustomerAlbum getDefaulCustomerAlbumByUserId(String userId);
}
