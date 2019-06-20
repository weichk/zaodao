/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-29
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.CustomerVideo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 用户视频表 JPA Dao
 * <p>
 * Date: 2017-06-29 12:16:54
 *
 * @author zhike
 */
public interface CustomerVideoDao extends EntityJpaDao<CustomerVideo, Long> {

    @Query("from CustomerVideo where userId =?1")
    List<CustomerVideo> getEntityByUserId(String userId);
}
