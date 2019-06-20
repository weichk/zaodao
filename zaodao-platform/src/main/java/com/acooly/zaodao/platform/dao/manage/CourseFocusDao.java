/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-31
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.dto.CourseFocusDto;
import com.acooly.zaodao.platform.entity.CourseFocus;
import com.acooly.zaodao.platform.order.CourseFocusListOrder;
import org.springframework.data.jpa.repository.Query;

/**
 * zd_course_focus JPA Dao
 *
 * Date: 2017-10-31 11:17:56
 * @author zhike
 *
 */
public interface CourseFocusDao extends EntityJpaDao<CourseFocus, Long>,CourseFocusCusDao {
    /**
     * 获取关注
     * @param userId
     * @param courseId
     * @return
     */
    CourseFocus getByUserIdAndFocusCourseId(String userId, Long courseId);

    /**
     * 获取课程关注数量
     * @param focusCourseId
     * @return
     */
    @Query(value = "SELECT COUNT(1) FROM zd_course_focus WHERE focus_course_id=?1", nativeQuery = true)
    Long getCourseFocusCount(Long focusCourseId);
}
