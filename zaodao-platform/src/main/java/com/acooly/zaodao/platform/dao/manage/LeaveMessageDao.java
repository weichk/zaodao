/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-01
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.LeaveMessage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 留言表 JPA Dao
 * <p>
 * Date: 2017-06-01 15:14:52
 *
 * @author zhike
 */
public interface LeaveMessageDao extends EntityJpaDao<LeaveMessage, Long>, LeaveMessageCusDao {

    List<LeaveMessage> findByUserId(String userId);

    @Query("from LeaveMessage where userId =?1 and readStatus=0")
    List<LeaveMessage> getNotReadEntitysByUserId(String userId);

    @Modifying
    @Query("UPDATE LeaveMessage SET readStatus=1 where userId =?1")
    void updateEntityReadStatus(String userId);
}
