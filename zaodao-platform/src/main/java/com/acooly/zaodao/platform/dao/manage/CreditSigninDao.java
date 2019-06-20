/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-15
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.CreditSignin;
import org.springframework.data.jpa.repository.Query;

/**
 * 积分签到记录 JPA Dao
 *
 * Date: 2017-06-15 15:23:25
 * @author zhike
 *
 */
public interface CreditSigninDao extends EntityJpaDao<CreditSignin, Long> {

    @Query(value = "select * from zd_credit_signin where username = ?1 order by sign_time desc limit 0,1", nativeQuery = true)
    CreditSignin lastRecord(String userId);
}
