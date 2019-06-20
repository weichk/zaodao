/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-12
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.module.point.domain.PointAccount;
import com.acooly.module.point.service.PointAccountService;
import com.acooly.module.point.service.PointTradeService;
import com.acooly.zaodao.platform.dao.manage.CoursePurchaseDao;
import com.acooly.zaodao.platform.dto.CourseDto;
import com.acooly.zaodao.platform.entity.Course;
import com.acooly.zaodao.platform.entity.CoursePurchase;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import com.acooly.zaodao.platform.enums.CourseType;
import com.acooly.zaodao.platform.order.CoursePurchaseOrder;
import com.acooly.zaodao.platform.order.QueryCoursePurchaseOrder;
import com.acooly.zaodao.platform.result.CoursePurchaseResult;
import com.acooly.zaodao.platform.service.manage.CoursePurchaseService;
import com.acooly.zaodao.platform.service.manage.CourseService;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.acooly.zaodao.platform.service.manage.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * zd_course_purchase Service实现
 *
 * <p>Date: 2017-10-12 15:11:59
 *
 * @author zhike
 */
@Slf4j
@Service("coursePurchaseService")
public class CoursePurchaseServiceImpl extends EntityServiceImpl<CoursePurchase, CoursePurchaseDao>
    implements CoursePurchaseService {

  @Autowired private CustomerService customerService;

  @Autowired private CourseService courseService;

  @Autowired private RewardService rewardService;

  @Autowired
  private PointAccountService pointAccountService;

  //钙片不足错误代码,前端会根据这个值显示界面
  private static final String CA_LESS_THAN_BALANCE  = "CA_LESS_THAN_BALANCE";
  /**
   * 添加购买课程记录
   */
  @Override
  @Transactional
  public CoursePurchaseResult addCoursePurchase(CoursePurchaseOrder order) {
    CoursePurchaseResult result = new CoursePurchaseResult();
    try {
      order.check();
      //验证是否购买过
      CoursePurchase coursePurchase = this.getEntityDao().findByUserIdAndCourseId(order.getUserId(), order.getCourseId());
      if(null == coursePurchase) {
        // 验证课程
        Course course = courseService.getCourse(order.getCourseId());
        //如果是报名课程且报名截止
        if(CourseType.enrolable.equals(course.getCourseType()) && CourseStatusEnum.endline.equals(course.getCourseStatus())){
          throw new BusinessException("该课程已经报名截止");
        }
        //验证钙片是否充足
        PointAccount pointAccount = pointAccountService.findByUserName(order.getUserId());
        log.info("钙片数量:{},课程价格:{}", pointAccount.getBalance(), course.getCoursePrice());
        if(pointAccount.getBalance() < course.getCoursePrice()){
          throw new BusinessException("钙片不足,请兑换钙片", CA_LESS_THAN_BALANCE);
        }
        coursePurchase = new CoursePurchase();
        // 验证用户
        String userId = order.getUserId();
        Customer customer = customerService.getUser(userId);
        coursePurchase.setUserId(customer.getUserId());
        coursePurchase.setCourseId(course.getId());
        rewardService.executePointTrade(course.getUserId(), customer.getUserId(), course.getCoursePrice() == null ? 0 : course.getCoursePrice(), "CoursePurchase", "购买课程");
        course.setUserCount(course.getUserCount() == null ? 1 : course.getUserCount() + 1);
        this.getEntityDao().save(coursePurchase);
        courseService.update(course);
      }else{
        result.setDetail("课程已经购买");
      }
      result.setCoursePurchaseId(coursePurchase.getId());
    } catch (BusinessException e) {
      log.info("购买课程失败！{}", e.getMessage());
      result.setStatus(ResultStatus.failure);
      result.setCode(e.getCode());
      result.setDetail("购买课程失败！" + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
      log.info("系统错误！{}", e.getMessage());
      result.setStatus(ResultStatus.failure);
      result.setCode(ResultStatus.failure.getCode());
      result.setDetail("系统错误！");
    }

    return result;
  }

  /**
   * 通过用户ID和课程ID查询
   *
   * @param userId
   * @param courseId
   * @return
   */
  @Override
  public int getCoursePurchaseCount(String userId, Long courseId) {
    return this.getEntityDao().getCoursePurchaseCount(userId, courseId);
  }

  /**
   * 获取已经购买的课程列表
   *
   * @param order
   * @return
   */
  @Override
  public PageResult<CourseDto> getPageCoursePurchaseList(QueryCoursePurchaseOrder order) {
    PageResult<CourseDto> pageResult = new PageResult<CourseDto>();
    try {
      pageResult = PageResult.from(getEntityDao().getPageCoursePurchaseList(order));
    } catch (Exception e) {
      log.info("系统错误！{}", e.getMessage());
      pageResult.setStatus(ResultStatus.failure);
      pageResult.setCode(ResultStatus.failure.getCode());
      pageResult.setDetail("系统错误！");
    }
    return pageResult;
  }
}
