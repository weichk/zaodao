/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-09-22
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.facade.ResultCode;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.module.ofile.domain.OnlineFile;
import com.acooly.openapi.framework.common.utils.Dates;
import com.acooly.openapi.tool.fastjson.JSON;
import com.acooly.zaodao.platform.dao.manage.CourseDao;
import com.acooly.zaodao.platform.dao.manage.CoursePurchaseDao;
import com.acooly.zaodao.platform.dto.CourseInfoDto;
import com.acooly.zaodao.platform.dto.CourseRecordInfoDto;
import com.acooly.zaodao.platform.dto.QueryCourseDto;
import com.acooly.zaodao.platform.dto.RecordOnlineFile;
import com.acooly.zaodao.platform.entity.*;
import com.acooly.zaodao.platform.enums.CourseStatusEnum;
import com.acooly.zaodao.platform.enums.CourseType;
import com.acooly.zaodao.platform.enums.RecordStatusEnum;
import com.acooly.zaodao.platform.order.CourseDetailOrder;
import com.acooly.zaodao.platform.order.CourseIntroOrder;
import com.acooly.zaodao.platform.order.CourseRecordOrder;
import com.acooly.zaodao.platform.order.QueryCourseOrder;
import com.acooly.zaodao.platform.result.CourseIntroResult;
import com.acooly.zaodao.platform.result.CourseResult;
import com.acooly.zaodao.platform.service.manage.*;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * zd_course Service实现
 *
 * <p>Date: 2017-09-22 13:55:30
 *
 * @author zhike
 */
@Slf4j
@Service("courseService")
public class CourseServiceImpl extends EntityServiceImpl<Course, CourseDao>
    implements CourseService {

  @Autowired private CourseRecordService courseRecordService;

  @Autowired private CustomerService customerService;

  @Autowired private TourGuideService tourGuideService;

  @Autowired
  private CoursePurchaseDao coursePurchaseDao;

  @Autowired
  private CourseFocusService courseFocusService;

  @Autowired
  private CoursePurchaseService coursePurchaseService;

  @Autowired
  private ArticleRewardLogService articleRewardLogService;

  @Value("${site.gateway.url}")
  private String siteGatewayUrl;

  @Override
  @Transactional
  public CourseIntroResult addOrEditCourseIntro(CourseIntroOrder order) {
    CourseIntroResult result = new CourseIntroResult();
    try {
      order.check();
      Course course = null;
      if (null == order.getCourseId()) {
        course = new Course();
        BeanCopier.copy(order, course);
        course.setCourseStatus(CourseStatusEnum.review);
        course.setCourseType(CourseType.official);
        course.setSourceCourseType(CourseType.official);
        this.getEntityDao().save(course);
      } else {
        course = this.getEntityDao().get(order.getCourseId());
        BeanCopier.copy(order, course);
        this.getEntityDao().update(course);
      }
      saveCourseRecord(course.getId(), order);
      result.setCourseId(course.getId());
    } catch (Exception e) {
      log.info("系统错误！{}", e.getMessage());
      result.setStatus(ResultStatus.failure);
      result.setCode(ResultStatus.failure.getCode());
      result.setDetail("系统错误！");
    }
    return result;
  }

  /**
   * 课程音频
   */
  private void saveCourseRecord(Long courseId, CourseIntroOrder order){
    //删除课程音频
    courseRecordService.removeByCourseId(courseId);
    if(!Strings.isBlank(order.getCourseRecordListJson())) {
      List<CourseRecord> courseRecords = JSON.parseArray(order.getCourseRecordListJson(), CourseRecord.class);
      if(courseRecords == null || courseRecords.size() <= 0){
        throw new BusinessException("未找到课程文件");
      }
      courseRecords.forEach(p ->{
        p.setCourseId(courseId);
        p.setUserId(order.getUserId());
      });
      courseRecordService.saves(courseRecords);
    }
  }

  /**
   * 获取课程列表
   *
   */
  @Override
  public PageResult<CourseInfoDto> getPageCourseList(QueryCourseOrder order) {
    PageResult<CourseInfoDto> pageResult = new PageResult<CourseInfoDto>();
    try {
      order.check();
      PageInfo<CourseInfoDto> pageInfo = this.getEntityDao().getPageCourseList(order);
      pageInfo.getPageResults().forEach(p -> {
        p.setTagContent(getCourseFirstTag(p.getTagContent()));
        p.setSystemTime(new Date());
        p.setIsPurchased(isPurchased(order.getAppUserId(), p.getId()));
        List<CourseRecord> records = courseRecordService.findByCourseId(p.getId());
        p.setRecordCount(records == null ? 0 : records.size());
        p.setMediaType(0); //目前只有音频
      });
      pageResult = PageResult.from(pageInfo);
    } catch (Exception e) {
      log.info("系统错误！{}", e.getMessage());
      pageResult.setStatus(ResultStatus.failure);
      pageResult.setCode(ResultStatus.failure.getCode());
      pageResult.setDetail("系统错误！");
    }
    return pageResult;
  }

  /**
   * 获取课程第一个标签
   */
  private String getCourseFirstTag(String tagContent){
    if(Strings.isNotBlank(tagContent)) {
      String[] tags = tagContent.split(",");
      return tags.length > 0 ? tags[0] : "";
    }
    return "";
  }

  /**
   * 判断课程是否购买
   */
  private boolean isPurchased(String userId, Long courseId){
    if(Strings.isNotBlank(userId)) {
      CoursePurchase coursePurchase = coursePurchaseDao.findByUserIdAndCourseId(userId, courseId);
      return coursePurchase != null;
    }
    return false;
  }

  /**
   * 获取课程详情
   * @param order
   * @return
   */
  @Override
  public CourseResult getCourseDetail(CourseDetailOrder order) {
    CourseResult courseResult = new CourseResult();
    try {
      order.check();
      //课程
      Course course = this.getEntityDao().get(order.getCourseId());
      if (course == null) {
        throw new BusinessException(ResultCode.FAILURE, "课程信息不存在！");
      }
      //会员
      Customer customer = customerService.findEntityByUserId(course.getUserId());
      if (customer == null) {
        throw new BusinessException(ResultCode.FAILURE, "会员信息不存在！");
      }
     //导游
      TourGuide tourGuide = tourGuideService.getEntityByUserId(course.getUserId());
//      if (tourGuide == null) {
//        throw new BusinessException(ResultCode.FAILURE, "导游信息不存在！");
//      }
      if(null != tourGuide){
        courseResult.setIntroduceMyself(tourGuide.getIntroduceMyself());
      }
      BeanCopier.copy(course, courseResult, "recordList");
      //courseResult.setIntroduceMyself(tourGuide.getIntroduceMyself());
      courseResult.setRealName(customer.getRealName());
      courseResult.setHeadImg(customer.getHeadImg());
      courseResult.setCourseId(course.getId());
      courseResult.setUserId(course.getUserId());

      List<CourseRecord> courseRecords = courseRecordService.findByCourseId(course.getId());

      // 复制音频数据给result
      for (CourseRecord cr : courseRecords) {
        CourseRecordInfoDto c = new CourseRecordInfoDto();
        BeanCopier.copy(cr, c);
        c.setRecordId(cr.getId());
        courseResult.getRecordList().add(c);
      }
      int count = 0;
      courseResult.setRecordCount(courseRecords != null ? courseRecords.size() : 0);
      courseResult.setFocusFlag(0);
      if(Strings.isNotBlank(order.getUserId())) {
        count = coursePurchaseService.getCoursePurchaseCount(order.getUserId(), order.getCourseId());
        //是否关注该课程
        CourseFocus courseFocus = courseFocusService.getByUserIdAndFocusCourseId(order.getUserId(), order.getCourseId());
        if(courseFocus != null){
          courseResult.setFocusFlag(1);
        }
      }
      courseResult.setPurchaseFlag(count > 0 ? 1 : 0);
      //课程被关注数量
      Long focusCount = courseFocusService.getFocusCountByFocusCourseId(order.getCourseId());
      courseResult.setFocusCount(focusCount);
      //课程打赏数量
      Long rewardCount = articleRewardLogService.getRewardCount(order.getCourseId());
      courseResult.setRewardCount(rewardCount);
      //6个购买者头像
      courseResult.setUserHeadImgUrl(getPurchaseUserHeadUrl(order.getCourseId(), course.getUserCount()));
    } catch (BusinessException e) {
      log.info(e.getMessage());
      courseResult.setStatus(ResultStatus.failure);
      courseResult.setCode(e.getCode());
      courseResult.setDetail(e.getMessage());
    } catch (Exception e) {
      log.info("系统错误！{}", e.getMessage());
      courseResult.setStatus(ResultStatus.failure);
      courseResult.setCode(ResultStatus.failure.getCode());
      courseResult.setDetail("系统错误！");
    }
    return courseResult;
  }

  private static final long MAX_USER_COUNT = 6;
  /**
   * 获取购买课程人员头像(最多6名)
   */
  private String getPurchaseUserHeadUrl(Long courseId, Long userCount){
    List<Customer> list = customerService.getByCourseId(courseId);
    StringBuilder headImgUrls = new StringBuilder();
    for(int i = 0; i < list.size(); i++){
      if(Strings.isNotBlank(list.get(i).getHeadImg())) {
        headImgUrls.append(list.get(i).getHeadImg());
      }else{
        //前端展示默认头像
        headImgUrls.append("0");
      }
      if (i < list.size() - 1) {
        headImgUrls.append("|");
      }
    }

    if(null != userCount && list.size() < MAX_USER_COUNT){
      long tempCnt = 0;
      if(userCount <= MAX_USER_COUNT){
        tempCnt = userCount - list.size();
      }else{
        tempCnt = MAX_USER_COUNT - list.size();
      }

      if(list.size() > 0){
        headImgUrls.append("|");
      }
      for(long i = 0; i < tempCnt; i++){
        headImgUrls.append("0");
        if (i < tempCnt - 1) {
          headImgUrls.append("|");
        }
      }
    }
    return headImgUrls.toString();
  }

  /**
   * 修改课程状态
   *
   * @param courseId
   * @param status
   * @return
   */
  @Override
  @Transactional
  public ResultBase changeCourseStatus(Long courseId, CourseStatusEnum status) {
    ResultBase resultBase = new ResultBase();

    try {
      if (null == courseId) {
        throw new BusinessException(
            ResultCode.PARAMETER_ERROR,
            String.format("%s,课程ID不能为空", ResultCode.PARAMETER_ERROR.getMessage()));
      }
      if (null == status) {
        throw new BusinessException(
            ResultCode.PARAMETER_ERROR,
            String.format("%s,课程状态不能为空", ResultCode.PARAMETER_ERROR.getMessage()));
      }
      Course course = this.getEntityDao().get(courseId);
      course.setCourseStatus(status);
      if (CourseStatusEnum.published.equals(status)) {
        course.setPublishTime(new Date());
      }
      this.getEntityDao().update(course);
    } catch (Exception e) {
      log.info("系统错误！{}", e.getMessage());
      resultBase.setStatus(ResultStatus.failure);
      resultBase.setCode(ResultStatus.failure.getCode());
      resultBase.setDetail("系统错误！");
    }

    return resultBase;
  }

  /**
   * 获取课程
   * @param courseId
   * @return
   */
  public Course getCourse(Long courseId) {
    Course course = this.getEntityDao().get(courseId);
    if (null == course) {
      throw new BusinessException(ResultCode.FAILURE, String.format("%s,未找到课程", ResultCode.FAILURE.getMessage()));
    }
    return course;
  }

  /**
   * 将课程从报名中修改到已发布
   */
  @Override
  public void executePublishTask() {
    this.getEntityDao().updateCourse(CourseStatusEnum.draft.getCode(), CourseStatusEnum.published.getCode());
  }

  //超过报名截止时间，将报名中状态的报名课程修改为报名截止
  @Override
  public void executeEnrolCourse(){
    this.getEntityDao().updateEnrolCourse();
  }

  /**
   * 添加课程
   */
  @Override
  public Course addCourse(Course course) {
    Date current = new Date();
    if(course.getCourseTime().getTime() < current.getTime()){
      throw new BusinessException("课程时间小于当前时间");
    }else if(course.getEndlineTime().getTime() < current.getTime()){
      throw new BusinessException("课程截止时间小于当前时间");
    }
    //课程状态为报名中
    course.setCourseStatus(CourseStatusEnum.enroll);
    course.setCourseType(CourseType.enrolable);
    course.setSourceCourseType(CourseType.enrolable);
    course.setPublishTime(current);
    course.setUserCount(0L);
    course.setSumRecordTime(course.getSumRecordTime());
    return this.getEntityDao().save(course);
  }

  /**
   * 编辑课程
   */
  @Override
  public void updateCourse(Course model) {
    Date current = new Date();

    if(model.getCourseTime().getTime() < current.getTime()){
      throw new BusinessException("课程时间小于当前时间");
    }
    if(model.getEndlineTime().getTime() < current.getTime()){
      throw new BusinessException("课程截止时间小于当前时间");
    }
    Course course = this.getEntityDao().get(model.getId());
    course.setCourseTitle(model.getCourseTitle());
    course.setUserId(model.getUserId());
    course.setCoursePrice(model.getCoursePrice());
    course.setCourseTime(model.getCourseTime());
    course.setSumRecordTime(model.getSumRecordTime());
    course.setEndlineTime(model.getEndlineTime());
    course.setUserCount(model.getUserCount());
    this.getEntityDao().update(course);
  }

  /**
   * 分页查询课程列表
   */
  @Override
  public PageInfo<Course> queryPageCourseList(QueryCourseDto queryCourseDto) {
    return this.getEntityDao().queryPageCourseList(queryCourseDto);
  }

  /**
   * 保存制作课程
   */
  @Override
  @Transactional
  public void updateMakeCourse(Course course, List<RecordOnlineFile> listRecordOfile, OnlineFile courseImgOflie) {
    //删除原音频
    courseRecordService.removeByCourseId(course.getId());
    if(listRecordOfile != null && listRecordOfile.size() > 0){
      //保存音频信息
      List<CourseRecord> courseRecords = Lists.newArrayList();
      listRecordOfile.forEach(f ->{
        CourseRecord courseRecord = new CourseRecord();
        courseRecord.setCourseId(course.getId());
        courseRecord.setUserId(course.getUserId());
        courseRecord.setRecordUrl(f.getAccessUrl());
        courseRecord.setRecordTime(f.getRecordTime());
        courseRecord.setRecordTitle(f.getRecordTitle());
        courseRecords.add(courseRecord);
      });
      courseRecordService.saves(courseRecords);
    }
    //课程信息
    Course model = this.getEntityDao().get(course.getId());
    model.setUserId(course.getUserId());
    model.setCourseTitle(course.getCourseTitle());
    model.setCourseIntro(course.getCourseIntro());
    model.setCoursePrice(course.getCoursePrice());
    model.setSumRecordTime(course.getSumRecordTime());
    model.setCourseStatus(course.getCourseStatus());
    model.setCourseType(CourseType.official);

    //课程封面
    if(courseImgOflie != null){
      model.setCourseImg(courseImgOflie.getAccessUrl());
    }

    this.getEntityDao().update(model);
  }

  /**
   * 时间s转换为文本描述
   * 0~119秒 ----约1分钟
   * 120秒~179秒 ----约2分钟
   * 180秒~239秒 ----约3分钟
   */
  @Override
  public String getCourseTimeText(Long seconds) {
    if(seconds == null) return "0分钟";

    double x = seconds / 60;
    double m = seconds % 60;
    if(x == 0 && m > 0){
      x = 1;
    }else {
      x = m >= 30 ? x + 1 : x;
    }
    return String.format("%s分钟", (int)x);
  }

  /**
   * 首页课程列表
   * @return
   */
  @Override
  public PageResult<CourseInfoDto> getHomeCourseList(String appUserId) {
    PageResult<CourseInfoDto> pageResult = new PageResult<CourseInfoDto>();
    try {
      PageInfo<CourseInfoDto> pageInfo = this.getEntityDao().getHomeCourseList();
      pageInfo.getPageResults().forEach(p -> {
        p.setTagContent(getCourseFirstTag(p.getTagContent()));
        p.setSystemTime(new Date());
        p.setIsPurchased(isPurchased(appUserId, p.getId()));
        List<CourseRecord> records = courseRecordService.findByCourseId(p.getId());
        p.setRecordCount(records == null ? 0 : records.size());
        p.setMediaType(0); //目前只有音频
        if(!Strings.isNotBlank(p.getCourseImg())) {
          p.setCourseImg("/courseImg/bmkc_default.png");
        }
      });
      pageResult = PageResult.from(pageInfo);
    } catch (Exception e) {
      log.info("系统错误！{}", e.getMessage());
      pageResult.setStatus(ResultStatus.failure);
      pageResult.setCode(ResultStatus.failure.getCode());
      pageResult.setDetail("系统错误！");
    }
    return pageResult;
  }
}
