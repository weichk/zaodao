/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-05-16
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.dto.LectorDto;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.order.TradeCaListOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 用户表 JPA Dao
 *
 * Date: 2017-05-16 20:08:56
 * @author zhike
 *
 */
public interface CustomerDao extends EntityJpaDao<Customer, Long>,CustomerCusDao {

    @Query("from Customer where mobileNo = ?1")
    Customer findEntityByMobileNo(String mobileNo);

    @Modifying
    @Query("update Customer set headImg = ?2 where id=?1")
    void uploadHearPortrait(Long id,String headPortrait);

    @Query("from Customer where userId = ?1")
    Customer findEntityByUserId(String userId);

    @Query("from Customer where openId = ?1")
    Customer findEntityByOpenId(String openId);

    @Query("from Customer where qqId = ?1")
    Customer findEntityByQqId(String qqId);

    @Query("from Customer where weiboId = ?1")
    Customer findEntityByWeiboId(String weiboId);

    /**
     * 获取讲师/非讲师集合
     */
    List<Customer> getListByIsLector(Integer isLector);

    /**
     * 获取购买课程的用户
     */
    @Query(value = "SELECT IFNULL(a.head_img,'0') AS head_img,a.* FROM zd_customer a JOIN zd_course_purchase b ON a.user_id=b.user_id WHERE b.course_id=?1 LIMIT 6", nativeQuery = true)
    List<Customer> getByCourseId(Long courseId);
}
