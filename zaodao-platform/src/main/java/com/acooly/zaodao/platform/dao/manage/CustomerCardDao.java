/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-24
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.CustomerCard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 用户绑卡信息表 JPA Dao
 * <p>
 * Date: 2017-05-24 22:44:01
 *
 * @author zhike
 */
public interface CustomerCardDao extends EntityJpaDao<CustomerCard, Long> {

    @Query("FROM CustomerCard WHERE userId = ?1")
    CustomerCard getEntityByUserId(String userId);

    @Modifying
    @Query("DELETE FROM CustomerCard WHERE userId = ?1")
    void deleteEntityByUserId(String userId);

    /**
     * 获取用户银行卡列表
     * @param userId
     * @return
     */
    List<CustomerCard> getByUserId(String userId);
}
