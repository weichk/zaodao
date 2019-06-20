/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-09-22
 */
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.dto.CourseInfoDto;
import com.acooly.zaodao.platform.entity.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * zd_course JPA Dao
 *
 * <p>Date: 2017-09-22 13:55:30
 *
 * @author zhike
 */
public interface CourseDao extends EntityJpaDao<Course, Long>, CourseCusDao {
    /**
     * 2018-05-18 xh modify
     * 将非报名课程从一个状态修改到另一个状态
     */
    @Modifying
    @Query(value = "UPDATE zd_course SET course_status=?2 WHERE course_status=?1 AND publish_time<= NOW() AND course_type!='enrolable'", nativeQuery = true)
    void updateCourse(String fromStatus, String toStatus);

    /*
    * 将报名课程从报名中修改为报名截止
    */
    @Modifying
    @Query(value = "UPDATE zd_course SET course_status='endline' WHERE course_status='enroll' AND endline_time<NOW() AND course_type='enrolable'", nativeQuery = true)
    void updateEnrolCourse();

    /**
     * 获取报名人数
     */
    @Query(value = "SELECT COUNT(DISTINCT user_id) AS userCount FROM zd_course_purchase WHERE course_id=?1", nativeQuery = true)
    Long getCourseUserCount(Long id);

    /**
     * 课程总时长
     */
    @Query(value = "SELECT SUM(record_time) FROM zd_course_record WHERE course_id=?1", nativeQuery = true)
    Long getSumRecordTime(Long courseId);
}
