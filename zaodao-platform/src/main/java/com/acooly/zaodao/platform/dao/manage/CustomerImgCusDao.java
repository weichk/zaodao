/*
 * 修订记录:
 * zhike@yiji.com 2017-06-08 16:13 创建
 *
 */
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.zaodao.platform.entity.CustomerImg;

import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public interface CustomerImgCusDao {

    /**
     * 获取会员默认照片列表
     * @param userId
     * @return
     */
    List<CustomerImg> getCusDefaultImgListByUserId(String userId);
}
