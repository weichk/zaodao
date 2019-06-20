/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-01
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.CustomerMessage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 用户消息表 JPA Dao
 *
 * Date: 2017-06-01 14:41:42
 * @author zhike
 *
 */
public interface CustomerMessageDao extends EntityJpaDao<CustomerMessage, Long>,CustomerMessageCusDao {


    @Query("from CustomerMessage where userId =?1 and readStatus=0")
    List<CustomerMessage> getNotReadEntitysByUserId(String userId);

    @Modifying
    @Query("UPDATE CustomerMessage SET readStatus=1 where userId =?1")
    void updateEntityReadStatus(String userId);

    @Modifying
    @Query("UPDATE CustomerMessage SET readStatus=?2 WHERE Id =?1")
    void updateCustomerMessageReadStatus(Long customerMessageId, int status);

    @Query("SELECT COUNT(1) FROM CustomerMessage WHERE userId=?1 AND readStatus=0")
    int getCustomerMessageCount(String userId);

}
