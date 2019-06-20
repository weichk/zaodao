/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-09-22
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.CourseRecord;
import com.acooly.zaodao.platform.enums.RecordStatusEnum;
import com.acooly.zaodao.platform.order.CourseRecordIntroOrder;
import com.acooly.zaodao.platform.order.CourseRecordOrder;
import com.acooly.zaodao.platform.result.CourseRecordIntroResult;
import com.acooly.zaodao.platform.result.CourseRecordResult;

import java.util.List;

/**
 * zd_course_record Service接口
 *
 * Date: 2017-09-22 15:25:10
 * @author zhike
 *
 */
public interface CourseRecordService extends EntityService<CourseRecord> {
    /**
     * 修改音频状态
     * @param courseRecordOrder
     * @return CourseRecordResult
     */
    CourseRecordResult changeRecordStatus(CourseRecordOrder courseRecordOrder);

    /**
     * 添加音频
     * @param courseRecordIntroOrder
     * @return
     */
    CourseRecordIntroResult addCourseRecord(CourseRecordIntroOrder courseRecordIntroOrder);

    /**
     * 根据课程获取音频集合
     * @param courseId
     * @param
     * @return
     */
    List<CourseRecord> findByCourseId(Long courseId);

    /**
     * 获取音频列表
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
