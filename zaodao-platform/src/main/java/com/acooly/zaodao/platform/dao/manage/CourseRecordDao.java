/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-09-22
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.CourseRecord;
import com.acooly.zaodao.platform.enums.RecordStatusEnum;

import java.util.List;

/**
 * zd_course_record JPA Dao
 *
 * Date: 2017-09-22 15:25:10
 * @author zhike
 *
 */
public interface CourseRecordDao extends EntityJpaDao<CourseRecord, Long> {
    /**
     * 根据课程ID查找音频
     * @param courseId
     * @return
     */
    List<CourseRecord> findByCourseIdOrderByRecordTitleAsc(Long courseId);

    /**
     * 查询课程音频列表
     * @param courseId
     * @param recordStatus
     * @return
     */
    List<CourseRecord> findByCourseIdAndRecordStatus(Long courseId, RecordStatusEnum recordStatus);

    /**
     * 删除课程音频
     */
    Integer removeByCourseId(Long courseId);
}
