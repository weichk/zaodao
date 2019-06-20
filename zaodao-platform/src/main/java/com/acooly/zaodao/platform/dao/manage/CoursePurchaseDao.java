/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-12
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.Course;
import com.acooly.zaodao.platform.entity.CoursePurchase;
import com.acooly.zaodao.platform.order.CoursePurchaseOrder;
import com.acooly.zaodao.platform.order.QueryCoursePurchaseOrder;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * zd_course_purchase JPA Dao
 *
 * Date: 2017-10-12 15:01:58
 * @author zhike
 *
 */
public interface CoursePurchaseDao extends EntityJpaDao<CoursePurchase, Long>,CoursePurchaseCusDao {
    /**
     * 查询已购买课程数量(用户有木有购买该课程)
     * @param userId
     * @param courseId
     * @return
     */
    @Query(value = "SELECT COUNT(1) FROM zd_course_purchase WHERE user_id = ?1 AND course_id = ?2", nativeQuery = true)
    int getCoursePurchaseCount(String userId, Long courseId);

    /**
     * 按用户ID和课程ID查询已购买课程
     * @param userId
     * @param courseId
     * @return
     */
    //@Query(value = "SELECT * FROM zd_course_purchase WHERE user_id=?1 AND course_id=?2", nativeQuery = true)
    //CoursePurchase findByUserIdAndCourseId(String userId, Long courseId);

    @Query("FROM CoursePurchase WHERE userId=?1 AND courseId=?2")
    CoursePurchase findByUserIdAndCourseId(String userId, Long courseId);
}
