/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-31
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.zaodao.platform.dao.manage.CourseFocusDao;
import com.acooly.zaodao.platform.dto.CourseFocusDto;
import com.acooly.zaodao.platform.entity.Course;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.GuideMessage;
import com.acooly.zaodao.platform.enums.GuideMessageType;
import com.acooly.zaodao.platform.order.CourseFocusAddOrder;
import com.acooly.zaodao.platform.order.CourseFocusListOrder;
import com.acooly.zaodao.platform.result.CourseFocusResult;
import com.acooly.zaodao.platform.service.manage.CourseFocusService;
import com.acooly.zaodao.platform.service.manage.CourseService;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.acooly.zaodao.platform.service.manage.GuideMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acooly.zaodao.platform.entity.CourseFocus;
import org.springframework.transaction.annotation.Transactional;

/**
 * zd_course_focus Service实现
 *
 * Date: 2017-10-31 11:17:56
 *
 * @author zhike
 *
 */
@Slf4j
@Service("courseFocusService")
public class CourseFocusServiceImpl extends EntityServiceImpl<CourseFocus, CourseFocusDao> implements CourseFocusService {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private GuideMessageService guideMessageService;

    @Autowired
    private CourseService courseService;

    /**
     * 课程关注
     * @param courseFocusAddOrder
     * @return
     */
    @Override
    @Transactional
    public CourseFocusResult addCourseFocus(CourseFocusAddOrder courseFocusAddOrder) {
        CourseFocusResult result = new CourseFocusResult();
        try {
            courseFocusAddOrder.check();
            Course course = courseService.get(courseFocusAddOrder.getCourseId());
            if (null == course) {
                throw new BusinessException("课程不存在");
            }
            Customer customer = customerService.getUser(courseFocusAddOrder.getUserId());
            if (null == customer) {
                throw new BusinessException("用户不存在");
            }

            CourseFocus courseFocus = this.getEntityDao().getByUserIdAndFocusCourseId(courseFocusAddOrder.getUserId(), courseFocusAddOrder.getCourseId());
            result.setFocusFlag(courseFocusAddOrder.getFocusFlag());
            if (courseFocusAddOrder.getFocusFlag() == 1) {
                 if(courseFocus == null) {
                     //关注课程
                     courseFocus = new CourseFocus();
                     courseFocus.setUserId(courseFocusAddOrder.getUserId());
                     courseFocus.setFocusCourseId(courseFocusAddOrder.getCourseId());
                     result.setFocusFlag(1);
                     this.getEntityDao().save(courseFocus);
                 }
                //关注人员不是课程作者则添加动态
                if (!course.getUserId().equals(courseFocusAddOrder.getUserId())) {
                    guideMessageService.addGuideMessage(courseFocus.getUserId(), GuideMessageType.focusCourse, course.getCourseTitle(), "", course.getId().toString(), course.getUserId());
                }
            } else if (courseFocusAddOrder.getFocusFlag() == 0 && null != courseFocus) {
                //取消关注
                this.getEntityDao().delete(courseFocus.getId());
                result.setFocusFlag(0);
            }
            //课程被关注数量
            Long courseFocusCount = this.getEntityDao().getCourseFocusCount(courseFocus.getFocusCourseId());
            result.setFocusCount(courseFocusCount);
        }catch (BusinessException ex){
            log.info(String.format("操作失败:%s", ex.getMessage()));
            result.setStatus(ResultStatus.failure);
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(ex.getMessage());
        }catch (Exception e){
            log.info("保存失败！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("保存失败！");
        }

        return result;
    }

    /**
     * 获取课程关注列表
     * @param courseFocusListOrder
     * @return
     */
    @Override
    public PageResult<CourseFocusDto> getCourseFocusList(CourseFocusListOrder courseFocusListOrder) {
        PageResult<CourseFocusDto> pageResult = new PageResult<>();

        try{
            courseFocusListOrder.check();
            pageResult = PageResult.from(this.getEntityDao().getPageCourseFocusList(courseFocusListOrder));
        }
        catch (Exception e){
            log.info("系统错误！{}", e.getMessage());
            pageResult.setStatus(ResultStatus.failure);
            pageResult.setCode(ResultStatus.failure.getCode());
            pageResult.setDetail("系统错误！");
        }

        return pageResult;
    }

    @Override
    public CourseFocus getByUserIdAndFocusCourseId(String userId, Long courseId) {
        return this.getEntityDao().getByUserIdAndFocusCourseId(userId, courseId);
    }

    @Override
    public Long getFocusCountByFocusCourseId(Long courseId) {
        return this.getEntityDao().getCourseFocusCount(courseId);
    }
}
