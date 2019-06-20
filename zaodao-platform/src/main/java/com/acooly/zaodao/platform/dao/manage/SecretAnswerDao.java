/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-18
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.SecretAnswer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 密保答案表 JPA Dao
 *
 * Date: 2017-05-18 16:21:58
 * @author zhike
 *
 */
public interface SecretAnswerDao extends EntityJpaDao<SecretAnswer, Long> {

    @Query("from SecretAnswer where secretId = ?1 AND userId = ?2")
    SecretAnswer getEntityBySerretIdAndCusId(Long secretId, String userId);

    @Modifying
    @Query("DELETE FROM SecretAnswer where userId = ?1")
    void deleteByUserId(String userId);
}
