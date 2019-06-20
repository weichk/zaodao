/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-05-16
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.CustomerFocusCountDto;
import com.acooly.zaodao.platform.dto.LectorDto;
import com.acooly.zaodao.platform.dto.PointTradeInfDto;
import com.acooly.zaodao.platform.enums.OpenPlatformType;
import com.acooly.zaodao.platform.order.*;
import com.acooly.zaodao.platform.result.*;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.CustomerLanguage;
import com.acooly.zaodao.platform.entity.TourGuide;

import java.util.List;

/**
 * 用户表 Service接口
 *
 * <p>Date: 2017-05-16 20:08:56
 *
 * @author zhike
 */
public interface CustomerService extends EntityService<Customer> {

  /**
   * 会员登录
   *
   * @param mobileNo
   * @param password
   * @return
   */
  Customer login(String mobileNo, String password);

  /**
   * 根据电话号码查询会员信息
   *
   * @param mobileNo
   * @return
   */
  Customer findEntityByMobileNo(String mobileNo);

  /**
   * 会员注册
   *
   * @param customer
   * @param mobileCaptcha
   */
  CustomerRegisterResult register(Customer customer, String mobileCaptcha);

  /**
   * 注册保存用户信息
   * @param customer
   */
  void saveCustomerInfo(Customer customer);

  /**
   * 找回密码
   *
   * @param customer
   * @param newPassword
   * @return
   */
  Customer findPassword(Customer customer, String newPassword);

  /**
   * 校验原密码是否正确
   *
   * @param customer
   * @param oldPassword
   * @return
   */
  boolean checkPassword(Customer customer, String oldPassword);

  /**
   * 申请导游保存导游信息
   *
   * @param customer
   * @param tourGuide
   * @param customerLanguages
   */
  void setApplyGuideInfo(
          Customer customer, TourGuide tourGuide, List<CustomerLanguage> customerLanguages);

  /**
   * 上传用户头像
   *
   * @param id
   * @param headPortrait
   */
  void uploadHearPortrait(Long id, String headPortrait);

  /**
   * 根据userId获取会员信息
   *
   * @param userId
   * @return
   */
  Customer findEntityByUserId(String userId);

  /**
   * 导游审核通过
   *
   * @param id
   */
  void auditPass(Long id);

  /**
   * 申请讲课
   */
  ResultBase applyTeach(String userId, String realName);

  /**
   * 查询用户详细信息
   *
   * @param userId
   * @return
   */
  UserInfoResult findUserInfo(String userId);

  /**
   * 检查用户ID
   *
   * @param userId
   * @return
   */
  boolean checkUser(String userId);

  /**
   * 获取用户
   *
   * @param userId
   * @return
   */
  Customer getUser(String userId);

  /**
   * 修改用户头像
   * @param userHeadModifyOrder
   * @return
   */
  ResultBase modifyUserHead(UserHeadModifyOrder userHeadModifyOrder);

  /**
   * 密码加密
   * @param customer
   */
  void entryptPassword(Customer customer);

  /**
   * 获取用户钙片列表
   * @param order
   * @return
   */
    PageResult<PointTradeInfDto> getPointTradeList(TradeCaListOrder order);

  /**
   * 开放平台登录
   * @param order
   * @return
   */
  OpenPlatformLoginResult openPlaformLogin(OpenPlatformLoginOrder order);

  /**
   * 开放平台获取用户访问信息
   * @param order
   * @return
   */
    OpenPlatformUserResult getOpenPlatformUser(OpenPlatformUserOrder order);

  /**
   * 获取粉丝数和关注数
   * @param userId
   * @return
   */
    CustomerFocusCountDto getCustomerFocusCount(String userId);

  /**
   * 开放平台检查用户手机号
   * @param order
   * @return
   */
  CustomerMobileCheckResult checkCustomerMobile(CustomerMobileCheckOrder order);

  /**
   * 根据开放平台登录方式设置openUserId
   * @param openUserId
   * @param openPlatformType
   * @param customer
   */
  void setOpenUserId(String openUserId, OpenPlatformType openPlatformType, Customer customer);

  /**
   * 根据是否是讲师查询用户集合
   */
    List<LectorDto> getByIsLector(int i);

  /**
   * 根据课程Id获取购买课程人员头像(最多6名)
   */
    List<Customer> getByCourseId(Long courseId);
}
