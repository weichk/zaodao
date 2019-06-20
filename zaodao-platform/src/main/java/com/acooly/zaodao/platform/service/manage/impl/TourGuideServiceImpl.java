/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-01
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.zaodao.common.utils.Dates;
import com.acooly.zaodao.platform.dao.manage.TourGuideDao;
import com.acooly.zaodao.platform.dto.CustomerFocusCountDto;
import com.acooly.zaodao.platform.dto.GuideInfoDto;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.CustomerFocus;
import com.acooly.zaodao.platform.entity.PlatOrderInfo;
import com.acooly.zaodao.platform.entity.TourGuide;
import com.acooly.zaodao.platform.enums.*;
import com.acooly.zaodao.platform.order.*;
import com.acooly.zaodao.platform.result.GuideInfoResult;
import com.acooly.zaodao.platform.service.manage.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 导游信息表 Service实现
 *
 * <p>
 *
 * <p>Date: 2017-06-01 17:44:06
 *
 * @author zhike
 */
@Slf4j
@Service("tourGuideService")
public class TourGuideServiceImpl extends EntityServiceImpl<TourGuide, TourGuideDao>
    implements TourGuideService {

  @Autowired
  private PlatOrderInfoService platOrderInfoService;

  @Autowired
  private TourGuideDao tourGuideDao;

  @Autowired
  private ArticleService articleService;

  @Autowired
  private CustomerFocusService customerFocusService;

  @Autowired
  private PlatOrderEvalService platOrderEvalService;

  @Autowired
  private CustomerService customerService;

  @Override
  public TourGuide getEntityByUserId(String userId) {
    return getEntityDao().findEntityByUserId(userId);
  }

  @Override
  public List<String> getGuideRestDays(String userId) {
    List<String> resultList = Lists.newArrayList();
    // 获取导游休息日期
    TourGuide guide = tourGuideDao.findEntityByUserId(userId);
    if (guide == null) {
      throw new BusinessException("导游信息不存在");
    }
    if (Strings.isNotBlank(guide.getRestDays())) {
      String[] days = guide.getRestDays().split(",");
      for (String day : days) {
        if (Dates.checkLatest(day)) {
          resultList.add(day);
        }
      }
    }
    log.info("获取导游休息锁定日期为{}", resultList);
    return resultList;
  }

  @Override
  public List<String> getOrderLockDays(String userId) {
    List<String> resultList = Lists.newArrayList();
    List<PlatOrderInfo> list = platOrderInfoService.findLatestOrder(userId);
    // 获取所有订单锁定的日期
    for (PlatOrderInfo info : list) {
      List<String> days = Dates.getBetweenDates(info.getStartPlayTime(), info.getEndPlayTime());
      resultList.addAll(days);
    }
    log.info("获取导游订单锁定日期为{}", resultList);
    return resultList;
  }

  @Override
  public TourGuide findByUserIdWithLock(String userId) {
    return tourGuideDao.findByUserIdWithLock(userId);
  }

  /**
   * 二级导游审核
   */
  @Override
  @Transactional
  public ResultBase applyGuide(ApplyGuideOrder order) {
    ResultBase result = new ResultBase();
    try {
      order.check();
      Customer customer = customerService.getUser(order.getUserId());
      customer.setIsTourGuide(IsTourGuide.TWO_APPROVE.getKey());
      customer.setAuditResult(GuideAuditResult.two_level_ing);
      customer.setAuditDesc("");
      customer.setRealName(order.getRealName());
      customer.setSex(order.getSex());

      if(null != order.getGovReceptCount()){
        customer.setIsGovRecept(IsOpenable.YES.getKey());
      }else{
        customer.setIsGovRecept(IsOpenable.NO.getKey());
      }
      if(null != order.getBusReceptCount()) {
        customer.setIsBusRecept(IsOpenable.YES.getKey());
      }else{
        customer.setIsBusRecept(IsOpenable.NO.getKey());
      }
      customerService.update(customer);

      TourGuide tourGuide = getEntityDao().findEntityByUserId(order.getUserId());
      if (tourGuide == null) {
        throw new BusinessException("请申请导游一级认证");
      }
      tourGuide.setGuideCoverImg(order.getGuideCoverImg());
      tourGuide.setGoodRoute(order.getGoodRoute());
      tourGuide.setPermanentCity(order.getPermanentCity());
      tourGuide.setIntroduceMyself(order.getIntroduceMyself());
      tourGuide.setUserId(order.getUserId());
      tourGuide.setTourTime(order.getTourTime());
      tourGuide.setTourRank(TourRank.copperMedal);
      tourGuide.setStarLevel(1);
      tourGuide.setHotGuide(0);
      tourGuide.setGuideLevel(GuideLevel.general);
      tourGuide.setPricePerDay(order.getPricePerDay().getCent());
      tourGuide.setLanguage(order.getLanguage());
      tourGuide.setBusReceptCount(order.getBusReceptCount());
      tourGuide.setGovReceptCount(order.getGovReceptCount());
      tourGuide.setModifyPriceTime(new Date());
      this.getEntityDao().update(tourGuide);
    } catch (BusinessException e) {
      log.info("申请导游失败！{}", e.message());
      throw e;
    }catch (Exception e) {
      log.info("申请导游出错！{}", e.getMessage());
      throw e;
    }
    return result;
  }

  @Override
  public PageResult<GuideInfoDto> queryGuideList(QueryGuideListOrder order) {
    PageResult<GuideInfoDto> result = new PageResult<>();
    try {
      order.check();
      return result.from(this.getEntityDao().queryGuideList(order));
    } catch (Exception e) {
      result.setStatus(ResultStatus.failure);
      result.setCode(ResultStatus.failure.getCode());
      result.setDetail("查询导游列表失败！");
    }
    return result;
  }

  @Override
  public PageResult<GuideInfoDto> queryGuideSearchList(
          QueryGuideSearchOrder queryGuideSearchOrder) {
    PageResult<GuideInfoDto> result = new PageResult<>();
    try {
      return result.from(this.getEntityDao().queryGuideSearchList(queryGuideSearchOrder));
    } catch (Exception e) {
      result.setStatus(ResultStatus.failure);
      result.setCode(ResultStatus.failure.getCode());
      result.setDetail("查询导游列表失败！");
    }
    return result;
  }


  @Override
  public GuideInfoResult getGuideInfo(GuideInfoOrder order) {
    GuideInfoResult result = new GuideInfoResult();
    try {
      order.check();
      //导游用户
      Customer guide = customerService.getUser(order.getGuideUserId());
      //导游
      TourGuide tourGuide = this.getEntityDao().findEntityByUserId(order.getGuideUserId());
      if (tourGuide == null) {
        throw new BusinessException("导游信息不存在");
      }
      BeanCopier.copy(tourGuide, result);
      result.setAge(guide.getAge());
      result.setRealName(guide.getRealName());
      result.setHeadImg(guide.getHeadImg());
      result.setMobileNo(guide.getMobileNo());
      result.setSex(guide.getSex());
      result.setIsBusRecept(guide.getIsBusRecept());
      result.setIsGovRecept(guide.getIsGovRecept());
      Integer nums = articleService.countUserArticles(guide.getUserId(), ArticleStatusEnum.published.getCode());
      result.setArticleNums(nums);

      Integer reviewCount = platOrderEvalService.getPlatOrderEvalCount(order.getGuideUserId());
      result.setReviewCount(reviewCount);

      CustomerFocusCountDto focusCountDto = customerService.getCustomerFocusCount(order.getGuideUserId());
      if(focusCountDto != null) {
        result.setFocusCount(focusCountDto.getFocusCount());
        result.setCount(focusCountDto.getCount());
      }
      result.setFocused(0);
      //导游是否被关注
      if(order.getGuideUserId().equals(order.getUserId())){
        result.setFocused(1);
      }else{
        CustomerFocus customerFocus = customerFocusService.getCustomerFocus(order.getUserId(), order.getGuideUserId());
        if(customerFocus != null){
          result.setFocused(1);
        }
      }
    } catch (BusinessException e) {
      result.setStatus(ResultStatus.failure);
      result.setCode(ResultStatus.failure.getCode());
      result.setDetail("查询导游列表失败！");
    }
    return result;
  }

  /**
   * 导游提交一级审核
   */
  @Override
  public ResultBase addGuideLevelOne(GuideLevelOneOrder order) {
    ResultBase result = new ResultBase();
    try {
      order.check();
      //申请成为导游
      TourGuide tourGuide = this.getEntityByUserId(order.getUserId());
      if(tourGuide == null){
        tourGuide = new TourGuide();
        tourGuide.setUserId(order.getUserId());
      }
      tourGuide.setGuideCertificateImg(order.getGuideCertificateImg());
      this.getEntityDao().save(tourGuide);
      //用户表
      Customer guide = customerService.getUser(order.getUserId());
      guide.setIsTourGuide(IsTourGuide.WAIT_APPROVE.getKey());
      guide.setAuditResult(GuideAuditResult.one_level_ing);
      guide.setAuditDesc("");
      guide.setIsBusRecept(IsOpenable.NO.getKey());
      guide.setIsGovRecept(IsOpenable.NO.getKey());
      guide.setIsLinkAngency(IsOpenable.NO.getKey());
      guide.setIsLeader(IsOpenable.NO.getKey());
      customerService.update(guide);
    }catch (BusinessException e){
      log.info("导游提交一级审核出现错误！{}", e.getMessage());
      result.setStatus(ResultStatus.failure);
      result.setCode(ResultStatus.failure.getCode());
      result.setDetail(e.getMessage());
    }catch (Exception e) {
      log.info("系统错误！{}", e.getMessage());
      result.setStatus(ResultStatus.failure);
      result.setCode(ResultStatus.failure.getCode());
      result.setDetail("系统错误！");
    }
    return result;
  }

  /**
   * 导游信息修改
   */
  @Override
  public ResultBase modifyGuideInfo(GuideModifyOrder order) {
    ResultBase result = new ResultBase();
    try {
      order.check();
      TourGuide tourGuide = this.getEntityByUserId(order.getUserId());
      if(!compartModifyPriceTime(tourGuide)){
        throw new BusinessException("带团价格1月只有1次修改机会，已用完");
      }
      tourGuide.setPermanentCity(order.getPermanentCity());
      tourGuide.setTourTime(order.getTourTime());
      tourGuide.setLanguage(order.getLanguage());
      tourGuide.setPricePerDay(order.getPricePerDay().getCent());
      tourGuide.setGoodRoute(order.getGoodRoute());
      tourGuide.setModifyPriceTime(new Date());
      tourGuide.setGuideCoverImg(order.getGuideCoverImg());
      tourGuide.setIntroduceMyself(order.getIntroduceMyself());
      this.getEntityDao().update(tourGuide);
    }catch (BusinessException e){
      log.info("导游信息修改出错！{}", e.getMessage());
      result.setStatus(ResultStatus.failure);
      result.setCode(ResultStatus.failure.getCode());
      result.setDetail(e.getMessage());
    }catch (Exception e) {
      log.info("系统错误！{}", e.getMessage());
      result.setStatus(ResultStatus.failure);
      result.setCode(ResultStatus.failure.getCode());
      result.setDetail("系统错误！");
    }
    return result;
  }

  /**
   * 判断距离上一次修改价格时间是否超过一个月
   */
  private boolean compartModifyPriceTime(TourGuide tourGuide){
    Date current = new Date();
    if(current.getTime() - tourGuide.getModifyPriceTime().getTime() < 30 * 24 * 60 * 60 * 1000L){
      return true;
    }
    return false;
  }

  @Override
  public GuideInfoResult getGuideInfoByUserId(String userId) {
    GuideInfoResult result = new GuideInfoResult();
    try {
      //用户
      Customer customer = customerService.getUser(userId);
      //导游
      TourGuide tourGuide = this.getEntityDao().findEntityByUserId(userId);
      if (tourGuide == null) {
        throw new BusinessException("导游信息不存在");
      }
      BeanCopier.copy(tourGuide, result);
      result.setAge(customer.getAge());
      result.setRealName(customer.getRealName());
      Integer nums =
              articleService.countUserArticles(userId, ArticleStatusEnum.published.getCode());
      result.setArticleNums(nums);
    } catch (Exception e) {
      result.setStatus(ResultStatus.failure);
      result.setCode(ResultStatus.failure.getCode());
      result.setDetail("查询导游列表失败！");
    }
    return result;
  }

  /**
   * 检查订单约导日期
   *
   * @param guideDateCheckOrder
   * @return
   */
  @Override
  public ResultBase getGuideDays(GuideDateCheckOrder guideDateCheckOrder) {
    ResultBase resultBase = new ResultBase();
    try {
      guideDateCheckOrder.check();
      getGuideDays(guideDateCheckOrder.getGuideUserId(), guideDateCheckOrder.getStartTime(), guideDateCheckOrder.getEndTime());
    }catch (BusinessException e){
      log.info("检查约导日期出现错误！{}", e.getMessage());
      resultBase.setStatus(ResultStatus.failure);
      resultBase.setCode(ResultStatus.failure.getCode());
      resultBase.setDetail(e.getMessage());
    }catch (Exception e) {
      log.info("系统错误！{}", e.getMessage());
      resultBase.setStatus(ResultStatus.failure);
      resultBase.setCode(ResultStatus.failure.getCode());
      resultBase.setDetail("系统错误！");
    }

    return resultBase;
  }

  /**
   * 获取约导天数
   *
   * @param guideUserId
   * @param startTime
   * @param endTime
   */
  @Override
  public int getGuideDays(String guideUserId, Date startTime, Date endTime) {
    List<String> days = Dates.getBetweenDates(startTime, endTime);
    List<String> orderDays = getOrderLockDays(guideUserId);
    List<String> restDays = getGuideRestDays(guideUserId);

    for (String day : days) {
      for (String restDay : restDays) {
        if (day.equals(restDay)) {
          throw new BusinessException("该日期已被预定！");
        }
      }
      for (String orderDay : orderDays) {
        if (day.equals(orderDay)) {
          throw new BusinessException("该日期已被预定！");
        }
      }
    }

    return days.size();
  }
}
