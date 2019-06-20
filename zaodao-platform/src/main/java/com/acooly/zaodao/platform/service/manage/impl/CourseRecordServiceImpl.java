/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-09-22
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.zaodao.platform.dao.manage.CourseRecordDao;
import com.acooly.zaodao.platform.entity.CourseRecord;
import com.acooly.zaodao.platform.enums.RecordStatusEnum;
import com.acooly.zaodao.platform.order.CourseRecordIntroOrder;
import com.acooly.zaodao.platform.order.CourseRecordOrder;
import com.acooly.zaodao.platform.result.CourseRecordIntroResult;
import com.acooly.zaodao.platform.result.CourseRecordResult;
import com.acooly.zaodao.platform.service.manage.CourseRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * zd_course_record Service实现
 *
 * <p>Date: 2017-09-22 15:25:10
 *
 * @author zhike
 */
@Slf4j
@Service("courseRecordService")
public class CourseRecordServiceImpl extends EntityServiceImpl<CourseRecord, CourseRecordDao>
    implements CourseRecordService {

  /**
   * 删除音频
   *
   * @param courseRecordOrder
   * @return CourseRecordResult
   */
  @Override
  public CourseRecordResult changeRecordStatus(CourseRecordOrder courseRecordOrder) {
    CourseRecordResult courseRecordResult = new CourseRecordResult();
    try {
      courseRecordOrder.check();
      CourseRecord courseRecord = this.getEntityDao().get(courseRecordOrder.getRecordId());
      courseRecord.setRecordStatus(courseRecordOrder.getRecordStatus());
      this.getEntityDao().update(courseRecord);
    } catch (Exception e) {
      log.info("系统错误！{}", e.getMessage());
      courseRecordResult.setStatus(ResultStatus.failure);
      courseRecordResult.setCode(ResultStatus.failure.getCode());
      courseRecordResult.setDetail("系统错误！");
    }

    return courseRecordResult;
  }

  /**
   * 添加音频
   *
   * @param courseRecordIntroOrder
   * @return
   */
  @Override
  public CourseRecordIntroResult addCourseRecord(CourseRecordIntroOrder courseRecordIntroOrder) {
    CourseRecordIntroResult result = new CourseRecordIntroResult();

    try {
      CourseRecord courseRecord = new CourseRecord();
      BeanCopier.copy(courseRecordIntroOrder, courseRecord);
      courseRecord.setRecordStatus(RecordStatusEnum.published);
      this.getEntityDao().save(courseRecord);

      result.setRecordId(courseRecord.getId());
      result.setCourseId(courseRecord.getCourseId());
    } catch (Exception e) {
      log.info("系统错误！{}", e.getMessage());
      result.setStatus(ResultStatus.failure);
      result.setCode(ResultStatus.failure.getCode());
      result.setDetail("系统错误！");
    }
    return result;
  }

  @Override
  public List<CourseRecord> findByCourseId(Long courseId) {
    return this.getEntityDao().findByCourseIdOrderByRecordTitleAsc(courseId);
  }

  @Override
  public List<CourseRecord> findByCourseIdAndRecordStatus(Long courseId, RecordStatusEnum recordStatus) {
    return this.getEntityDao().findByCourseIdAndRecordStatus(courseId, recordStatus);
  }

  /**
   * 删除课程音频
   */
  @Override
  public Integer removeByCourseId(Long courseId) {
    return this.getEntityDao().removeByCourseId(courseId);
  }
}
