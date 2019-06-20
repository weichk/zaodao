/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-30
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.dto.CustomerFocusCountDto;
import com.acooly.zaodao.platform.entity.CustomerFocus;

/**
 * zd_customer_focus JPA Dao
 *
 * Date: 2017-10-30 15:56:37
 * @author zhike
 *
 */
public interface CustomerFocusDao extends EntityJpaDao<CustomerFocus, Long>,CustomerFocusCusDao {

    /**
     * 获取用户关注信息
     * @param userId
     * @param focusUserId
     * @return
     */
    CustomerFocus getByUserIdAndFocusUserId(String userId, String focusUserId);
}
