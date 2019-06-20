/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-31
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.zaodao.platform.dao.manage.CourseReviewDao;
import com.acooly.zaodao.platform.dto.CourseReviewDto;
import com.acooly.zaodao.platform.entity.Course;
import com.acooly.zaodao.platform.entity.TravelReview;
import com.acooly.zaodao.platform.entity.TravelVoice;
import com.acooly.zaodao.platform.enums.GuideMessageType;
import com.acooly.zaodao.platform.order.CourseReviewAddOrder;
import com.acooly.zaodao.platform.order.CourseReviewListOrder;
import com.acooly.zaodao.platform.service.manage.CourseReviewService;
import com.acooly.zaodao.platform.service.manage.CourseService;
import com.acooly.zaodao.platform.service.manage.GuideMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acooly.zaodao.platform.entity.CourseReview;

/**
 * zd_course_review Service实现
 *
 * Date: 2017-10-31 11:09:26
 *
 * @author zhike
 *
 */
@Slf4j
@Service("courseReviewService")
public class CourseReviewServiceImpl extends EntityServiceImpl<CourseReview, CourseReviewDao> implements CourseReviewService {
    @Autowired
    private GuideMessageService guideMessageService;

    @Autowired
    private CourseService courseService;

    /**
     * 添加课程评论
     * @param courseReviewAddOrder
     * @return
     */
    @Override
    public ResultBase addCourseReview(CourseReviewAddOrder courseReviewAddOrder) {
        ResultBase resultBase = new ResultBase();
        try {
            courseReviewAddOrder.check();
            //获取评论信息
            CourseReview courseReview = new CourseReview();
            courseReview.setUserId(courseReviewAddOrder.getUserId());
            courseReview.setContent(courseReviewAddOrder.getContent());
            courseReview.setReviewParentId(courseReviewAddOrder.getReviewParentId());
            courseReview.setCourseId(courseReviewAddOrder.getCourseId());
            this.getEntityDao().save(courseReview);
            Course course = courseService.get(courseReview.getCourseId());
            //不是课程作者则添加评论
            if (!course.getUserId().equals(courseReviewAddOrder.getUserId())) {
                guideMessageService.addGuideMessage(courseReview.getUserId(), GuideMessageType.reviewCourse, course.getCourseTitle(), courseReview.getContent(), courseReview.getId().toString(), course.getUserId());
            }
        } catch (Exception e) {
            log.info("保存失败！{}", e.getMessage());
            resultBase.setStatus(ResultStatus.failure);
            resultBase.setCode(ResultStatus.failure.getCode());
            resultBase.setDetail("保存失败！");
        }

        return resultBase;
    }

    /**
     * 获取课程评论列表
     * @param courseReviewListOrder
     * @return
     */
    @Override
    public PageResult<CourseReviewDto> getCourseReviewList(CourseReviewListOrder courseReviewListOrder) {
        PageResult<CourseReviewDto> pageResult = new PageResult<>();

        try{
            courseReviewListOrder.check();
            pageResult = PageResult.from(this.getEntityDao().getPageCourseReviewList(courseReviewListOrder));
        }
        catch (Exception e){
            log.info("系统错误！{}", e.getMessage());
            pageResult.setStatus(ResultStatus.failure);
            pageResult.setCode(ResultStatus.failure.getCode());
            pageResult.setDetail("系统错误！");
        }

        return pageResult;
    }
}
