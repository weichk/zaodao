/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-30
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.module.point.service.PointAccountService;
import com.acooly.zaodao.platform.dao.manage.CustomerFocusDao;
import com.acooly.zaodao.platform.dto.CustomerFocusCountDto;
import com.acooly.zaodao.platform.dto.CustomerFocusDto;
import com.acooly.zaodao.platform.entity.CreditSignin;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.enums.GuideMessageType;
import com.acooly.zaodao.platform.order.CustomerFocusAddOrder;
import com.acooly.zaodao.platform.order.CustomerFocusCountOrder;
import com.acooly.zaodao.platform.order.CustomerFocusListOrder;
import com.acooly.zaodao.platform.order.CustomerSigninAddOrder;
import com.acooly.zaodao.platform.result.CustomerFocusCountResult;
import com.acooly.zaodao.platform.result.CustomerSigninAddResult;
import com.acooly.zaodao.platform.service.manage.CreditSigninService;
import com.acooly.zaodao.platform.service.manage.CustomerFocusService;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.acooly.zaodao.platform.service.manage.GuideMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acooly.zaodao.platform.entity.CustomerFocus;
import org.springframework.transaction.annotation.Transactional;

/**
 * zd_customer_focus Service实现
 *
 * <p>Date: 2017-10-30 15:56:37
 *
 * @author zhike
 */
@Slf4j
@Service("customerFocusService")
public class CustomerFocusServiceImpl extends EntityServiceImpl<CustomerFocus, CustomerFocusDao>
    implements CustomerFocusService {
  @Autowired private CustomerFocusService customerFocusService;

  @Autowired private CustomerService customerService;

  @Autowired private GuideMessageService guideMessageService;

  @Autowired private PointAccountService pointAccountService;

  @Autowired private CreditSigninService creditSigninService;
  /**
   * 添加用户关注
   *
   * @param customerFocusAddOrder
   * @return
   */
  @Override
  @Transactional
  public ResultBase addCustomerFocus(CustomerFocusAddOrder customerFocusAddOrder) {
    ResultBase resultBase = new ResultBase();
    try {
      customerFocusAddOrder.check();

      if(customerFocusAddOrder.getUserId().equals(customerFocusAddOrder.getFocusUserId())) {
        throw new BusinessException("关注人和被关注人不能相同");
      }

      CustomerFocus customerFocus = customerFocusService.getCustomerFocus(customerFocusAddOrder);
      // 关注
      if (customerFocusAddOrder.getCustomerFocusFlag() == 1 && null == customerFocus) {
        customerFocus = new CustomerFocus();
        customerFocus.setUserId(customerFocusAddOrder.getUserId());
        customerFocus.setFocusUserId(customerFocusAddOrder.getFocusUserId());
        this.getEntityDao().save(customerFocus);
        // 构造详情动态
        Customer focusUser = customerService.getUser(customerFocusAddOrder.getFocusUserId());
        guideMessageService.addGuideMessage(customerFocusAddOrder.getUserId(), GuideMessageType.focusUser, focusUser.getRealName(),"", focusUser.getUserId(), focusUser.getUserId());
      } else if (customerFocusAddOrder.getCustomerFocusFlag() == 0 && null != customerFocus) {
        guideMessageService.removeGuidMessage(
            customerFocusAddOrder.getUserId(),
            GuideMessageType.focusUser,
            customerFocusAddOrder.getFocusUserId());
        this.getEntityDao().delete(customerFocus.getId());
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
   * 获取关注数量和粉丝数
   *
   * @param customerFocusCountOrder
   * @return
   */
  @Override
  public CustomerFocusCountResult getCustomerFocusCount(
      CustomerFocusCountOrder customerFocusCountOrder) {
    CustomerFocusCountResult customerFocusCountResult = new CustomerFocusCountResult();
    try {
      customerFocusCountOrder.check();
      CustomerFocusCountDto customerFocusCountDto =
          this.getEntityDao().getCustomerFocusCount(customerFocusCountOrder.getUserId());
      customerFocusCountResult.setCount(customerFocusCountDto.getCount());
      customerFocusCountResult.setFocusCount(customerFocusCountDto.getFocusCount());
    }catch (BusinessException e){
      log.info("获取信息失败！{}", e.getMessage());
      customerFocusCountResult.setStatus(ResultStatus.failure);
      customerFocusCountResult.setCode(ResultStatus.failure.getCode());
      customerFocusCountResult.setDetail(e.getMessage());
    }catch (Exception e) {
      log.info("系统错误！{}", e.getMessage());
      customerFocusCountResult.setStatus(ResultStatus.failure);
      customerFocusCountResult.setCode(ResultStatus.failure.getCode());
      customerFocusCountResult.setDetail("系统错误！");
    }

    return customerFocusCountResult;
  }


  /**
   * 用户关注/粉丝列表
   *
   * @param customerFocusListOrder
   * @return
   */
  @Override
  public PageResult<CustomerFocusDto> getCustomerFocusList(
      CustomerFocusListOrder customerFocusListOrder) {
    PageResult<CustomerFocusDto> pageResult = new PageResult<>();

    try {
      customerFocusListOrder.check();
      if (customerFocusListOrder.getFocusFlag() != 0
          && customerFocusListOrder.getFocusFlag() != 1) {
        throw new BusinessException("关注标识取值不在范围内");
      }
      pageResult =
          PageResult.from(this.getEntityDao().getCustomerFocusList(customerFocusListOrder));
    } catch (Exception e) {
      log.info("系统错误！{}", e.getMessage());
      pageResult.setStatus(ResultStatus.failure);
      pageResult.setCode(ResultStatus.failure.getCode());
      pageResult.setDetail("系统错误！");
    }

    return pageResult;
  }

  /**
   * 获取关注用户信息
   *
   * @param customerFocusAddOrder
   * @return
   */
  @Override
  public CustomerFocus getCustomerFocus(CustomerFocusAddOrder customerFocusAddOrder) {
    return this.getEntityDao()
        .getByUserIdAndFocusUserId(
            customerFocusAddOrder.getUserId(), customerFocusAddOrder.getFocusUserId());
  }

  /**
   * 签到
   *
   * @param customerSigninAddOrder
   * @return
   */
  @Override
  @Transactional
  public CustomerSigninAddResult addCustomerSignin(CustomerSigninAddOrder customerSigninAddOrder) {
    CustomerSigninAddResult result = new CustomerSigninAddResult();
    try {
      customerSigninAddOrder.check();
      // 用户
      Customer customer = customerService.getUser(customerSigninAddOrder.getUserId());
      // 签到
      CreditSignin creditSignin = creditSigninService.CerditSignin(customer);
      result.setTimes(creditSignin.getTimes());
      log.info("用户{}签到成功", customer.getMobileNo());
    } catch (BusinessException ex) {
      log.info(String.format("签到失败:%s", ex.getMessage()));
      result.setStatus(ResultStatus.failure);
      result.setCode(ResultStatus.failure.getCode());
      result.setDetail(ex.getMessage());
    } catch (Exception e) {
      log.info(String.format("签到失败:%s", e.getMessage()));
      result.setStatus(ResultStatus.failure);
      result.setCode(ResultStatus.failure.getCode());
      result.setDetail("签到失败");
    }

    return result;
  }

  /**
   * 获取用户与用户的关注信息
   * @param userId
   * @param focusUserId
   * @return
   */
  @Override
  public CustomerFocus getCustomerFocus(String userId, String focusUserId) {
    return this.getEntityDao().getByUserIdAndFocusUserId(userId, focusUserId);
  }
}
