/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-12-11
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.zaodao.platform.dao.manage.PlatOrderEvalDao;
import com.acooly.zaodao.platform.dto.PlatOrderEvalDto;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.OrderGuideTag;
import com.acooly.zaodao.platform.entity.PlatOrderInfo;
import com.acooly.zaodao.platform.enums.PlatOrderInfoOrderStatus;
import com.acooly.zaodao.platform.order.PlatOrderEvalListOrder;
import com.acooly.zaodao.platform.order.PlatOrderEvalOrder;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.acooly.zaodao.platform.service.manage.OrderGuideTagService;
import com.acooly.zaodao.platform.service.manage.PlatOrderEvalService;
import com.acooly.zaodao.platform.service.manage.PlatOrderInfoService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.entity.PlatOrderEval;

import java.util.Arrays;
import java.util.List;

/**
 * zd_plat_order_eval Service实现
 *
 * Date: 2017-12-11 17:03:29
 *
 * @author zhike
 *
 */
@Slf4j
@Service("platOrderEvalService")
public class PlatOrderEvalServiceImpl extends EntityServiceImpl<PlatOrderEval, PlatOrderEvalDao> implements PlatOrderEvalService {
    @Autowired
    private PlatOrderInfoService platOrderInfoService;

    @Autowired
    private OrderGuideTagService orderGuideTagService;
    /**
     * 评价订单
     * @param order
     * @return
     */
    @Override
    public ResultBase addPlatOrderEval(PlatOrderEvalOrder order) {
        ResultBase resultBase = new ResultBase();
        try{
            order.check();
            //PlatOrderInfo platOrderInfo = platOrderInfoService.findByOrderNo(order.getPlatOrderNo());
            PlatOrderInfo platOrderInfo = platOrderInfoService.findByOrderNoWithLock(order.getPlatOrderNo());
            if(platOrderInfo == null){
                throw new BusinessException("未找到订单");
            }else if(!platOrderInfo.getTouristId().equals(order.getUserId())){
                throw new BusinessException("评价人员不符");
            }
            //添加评价内容
            addPlatOrderEvalContent(platOrderInfo, order);
            //添加评价标签
            addPlatOrderEvalTags(platOrderInfo, order);
            //订单修改为已评价
            platOrderInfo.setOrderStatus(PlatOrderInfoOrderStatus.evaluated);
            platOrderInfoService.update(platOrderInfo);
        } catch (BusinessException e){
            log.info("订单评价失败！{}", e.getMessage());
            resultBase.setStatus(ResultStatus.failure);
            resultBase.setCode(ResultStatus.failure.getCode());
            resultBase.setDetail(e.getMessage());
        }catch (Exception e){
            log.info("系统错误！{}", e.getMessage());
            resultBase.setStatus(ResultStatus.failure);
            resultBase.setCode(ResultStatus.failure.getCode());
            resultBase.setDetail("系统错误！");
        }

        return resultBase;
    }

    /**
     * 添加订单评价内容
     */
    private void addPlatOrderEvalContent(PlatOrderInfo platOrderInfo, PlatOrderEvalOrder order){
        PlatOrderEval platOrderEval = this.getEntityDao().findByTouristIdAndOrderNo(platOrderInfo.getTouristId(), platOrderInfo.getOrderNo());
        if(platOrderEval == null){
            platOrderEval = new PlatOrderEval();
            platOrderEval.setOrderNo(order.getPlatOrderNo());
            platOrderEval.setContent(order.getContent());
            platOrderEval.setScore(order.getScore());
            platOrderEval.setTourGuideId(platOrderInfo.getTourGuideId());
            platOrderEval.setTouristId(platOrderInfo.getTouristId());
            this.getEntityDao().save(platOrderEval);
        }else{
            throw new BusinessException("订单已经评价");
        }
    }

    /**
     * 添加导游标签
     */
    private void addPlatOrderEvalTags(PlatOrderInfo platOrderInfo, PlatOrderEvalOrder order){
        if(Strings.isNotBlank(order.getGuidTagIds())) {
            List<String> list = Arrays.asList(order.getGuidTagIds().split(","));
            List<OrderGuideTag> tags = Lists.newArrayList();

            list.forEach(p ->{
                OrderGuideTag orderGuideTag = new OrderGuideTag();
                orderGuideTag.setEvalTagId(Long.parseLong(p));
                orderGuideTag.setOrderNo(platOrderInfo.getOrderNo());
                orderGuideTag.setTourGuideId(platOrderInfo.getTourGuideId());
                orderGuideTag.setUserId(order.getUserId());

                tags.add(orderGuideTag);
            });
            if(tags.size() > 0) {
                orderGuideTagService.saves(tags);
            }
        }
    }
    /**
     * 订单评价
     * @param order
     * @return
     */
    @Override
    public PageResult<PlatOrderEvalDto> getPlateOrderEvalList(PlatOrderEvalListOrder order) {
        PageResult<PlatOrderEvalDto> pageResult = new PageResult<>();
        try{
            order.check();
            pageResult = PageResult.from(this.getEntityDao().getPagePlateOrderEvalList(order));
        } catch (Exception e){
            log.info("系统错误！{}", e.getMessage());
            pageResult.setStatus(ResultStatus.failure);
            pageResult.setCode(ResultStatus.failure.getCode());
            pageResult.setDetail("系统错误！");
        }

        return pageResult;
    }

    /**
     * 获取订单评价
     * @param userId
     * @param orderNo
     * @return
     */
    @Override
    public PlatOrderEval getPlatOrderEval(String userId, String orderNo) {
        return  this.getEntityDao().findByTouristIdAndOrderNo(userId, orderNo);
    }

    /**
     * 订单评价数量
     * @param guideUserId
     * @return
     */
    @Override
    public Integer getPlatOrderEvalCount(String guideUserId) {
        return this.getEntityDao().getCountByTourGuideId(guideUserId);
    }
}
