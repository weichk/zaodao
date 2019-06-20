/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-06-07
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.CustomerImg;

import java.util.List;

/**
 * 用户照片表 JPA Dao
 *
 * Date: 2017-06-07 11:37:17
 * 
 * @author zhike
 *
 */
public interface CustomerImgDao extends EntityJpaDao<CustomerImg, Long>, CustomerImgCusDao {

	List<CustomerImg> findByAlbumId(Long albumId);
}
