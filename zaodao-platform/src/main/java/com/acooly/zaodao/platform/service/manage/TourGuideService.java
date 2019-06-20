/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-01
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.GuideInfoDto;
import com.acooly.zaodao.platform.dto.TourGuideDto;
import com.acooly.zaodao.platform.entity.TourGuide;
import com.acooly.zaodao.platform.order.*;
import com.acooly.zaodao.platform.result.GuideInfoResult;

import java.util.Date;
import java.util.List;

/**
 * 导游信息表 Service接口
 *
 * <p>Date: 2017-06-01 17:44:06
 *
 * @author zhike
 */
public interface TourGuideService extends EntityService<TourGuide> {

  /**
   * 通过userId获取导游信息
   *
   * @param userId
   * @return
   */
  TourGuide getEntityByUserId(String userId);

  /**
   * 获取导游休息日期
   *
   * @param userId
   * @return
   */
  List<String> getGuideRestDays(String userId);

  /**
   * 获取导游订单锁定的日期
   *
   * @param userId
   * @return
   */
  List<String> getOrderLockDays(String userId);

  /**
   * 根据userId获取导游信息（for update）
   *
   * @param userId
   * @return
   */
  TourGuide findByUserIdWithLock(String userId);

  /**
   * @param order
   * @return
   */
  ResultBase applyGuide(ApplyGuideOrder order);

  /**
   * 查询导游列表
   *
   * @param order
   * @return
   */
  PageResult<GuideInfoDto> queryGuideList(QueryGuideListOrder order);

  /**
   * 查询导游列表（排除冲突日期,休息日筛选）
   * @param queryGuideSearchOrder
   * @return
   */
  PageResult<GuideInfoDto> queryGuideSearchList(QueryGuideSearchOrder queryGuideSearchOrder);

  /**
   * 根据导游userId获取导游个人信息
   * @param userId
   * @return
   */
  GuideInfoResult getGuideInfoByUserId(String userId);

  /**
   * 检查导游约导日期
   * @param guideDateCheckOrder
   * @return
   */
    ResultBase getGuideDays(GuideDateCheckOrder guideDateCheckOrder);

  /**
   * 获取约导天数
   * @param guideUserId
   * @param startTime
   * @param endTime
   * @return
   */
  int getGuideDays(String guideUserId, Date startTime, Date endTime);

  /**
   * 获取导游详情
   * @param order
   * @return
   */
    GuideInfoResult getGuideInfo(GuideInfoOrder order);

  /**
   * 导游提交一级审核
   */
  ResultBase addGuideLevelOne(GuideLevelOneOrder order);

  /**
   * 导游信息修改
   */
    ResultBase modifyGuideInfo(GuideModifyOrder order);
}
