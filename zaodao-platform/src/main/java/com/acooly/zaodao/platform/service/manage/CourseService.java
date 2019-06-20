/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-09-22
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityService;
import com.acooly.module.ofile.domain.OnlineFile;
import com.acooly.zaodao.platform.dto.CourseInfoDto;
import com.acooly.zaodao.platform.dto.QueryCourseDto;
import com.acooly.zaodao.platform.dto.RecordOnlineFile;
import com.acooly.zaodao.platform.entity.Course;
import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import com.acooly.zaodao.platform.order.CourseDetailOrder;
import com.acooly.zaodao.platform.order.CourseIntroOrder;
import com.acooly.zaodao.platform.order.QueryCourseOrder;
import com.acooly.zaodao.platform.result.CourseIntroResult;
import com.acooly.zaodao.platform.result.CourseResult;

import java.util.List;

/**
 * zd_course Service接口
 *
 * Date: 2017-09-22 13:55:30
 * @author zhike
 *
 */
public interface CourseService extends EntityService<Course> {

    /**
     * 添加或修改课程简介及内容
     * @param order
     * @return
     */
    CourseIntroResult addOrEditCourseIntro(CourseIntroOrder order);

    /**
     * 查询课程列表
     * @param queryCourseOrder
     * @return
     */
    PageResult<CourseInfoDto> getPageCourseList(QueryCourseOrder queryCourseOrder);

    /**
     * 获取课程详情,包含用户是否购买该课程信息
     * @param courseDetailOrder
     * @return
     */
    CourseResult getCourseDetail(CourseDetailOrder courseDetailOrder);

    /**
     * 修改课程状态为status
     * @param courseId
     * @param status
     * @return
     */
    ResultBase changeCourseStatus(Long courseId, CourseStatusEnum status);

    /**
     * 获取课程
     * @param courseId
     * @return
     */
    Course getCourse(Long courseId);

    /**
     * 执行发布课程定时任务
     */
    void executePublishTask();

    /**
     * 超过报名截止时间，将报名中状态的报名课程修改为报名截止
     */
    void executeEnrolCourse();

    /**
     * 添加报名课程
     */
    Course addCourse(Course course);

    /**
     * 修改报名课程
     */
    void updateCourse(Course course);

    /**
     * 分页查询课程列表
     */
    PageInfo<Course> queryPageCourseList(QueryCourseDto queryCourseDto);

    /**
     * 保存制作课程
     */
    void updateMakeCourse(Course course, List<RecordOnlineFile> listRecordOfile, OnlineFile courseImgOflie);

    /**
     * 课程时长文本
     * @param seconds
     * @return
     */
    String getCourseTimeText(Long seconds);

    /**
     * 首页课程列表
     * @return
     */
    PageResult<CourseInfoDto> getHomeCourseList(String appUserId);
}
