/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-26
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.zaodao.platform.dao.manage.TravelReviewDao;
import com.acooly.zaodao.platform.dto.TravelReviewDto;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.TravelVoice;
import com.acooly.zaodao.platform.enums.GuideMessageType;
import com.acooly.zaodao.platform.order.TravelReviewAddOrder;
import com.acooly.zaodao.platform.order.TravelReviewListOrder;
import com.acooly.zaodao.platform.result.TravelReviewAddResult;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.acooly.zaodao.platform.service.manage.GuideMessageService;
import com.acooly.zaodao.platform.service.manage.TravelReviewService;
import com.acooly.zaodao.platform.service.manage.TravelVoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acooly.zaodao.platform.entity.TravelReview;
import org.springframework.transaction.annotation.Transactional;

/**
 * zd_travel_review Service实现
 *
 * Date: 2017-10-26 19:17:16
 *
 * @author zhike
 *
 */
@Service("travelReviewService")
@Slf4j
public class TravelReviewServiceImpl extends EntityServiceImpl<TravelReview, TravelReviewDao> implements TravelReviewService {
    @Autowired
    private TravelVoiceService travelVoiceService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private GuideMessageService guideMessageService;

    /**
     * 添加评论
     * @param order
     * @return
     */
    @Override
    @Transactional
    public TravelReviewAddResult addTravelReivew(TravelReviewAddOrder order) {
        TravelReviewAddResult result = new TravelReviewAddResult();
        try {
            order.check();
            Customer customer = customerService.getUser(order.getUserId());
            //获取评论信息
            TravelReview travelReview = new TravelReview();
            travelReview.setUserId(order.getUserId());
            travelReview.setContent(order.getContent());
            travelReview.setReviewParentId(order.getReviewParentId());
            travelReview.setTravelVoiceId(order.getTravelVoiceId());
            this.getEntityDao().save(travelReview);
            result.setTravelReviewId(travelReview.getId());
            result.setHeadImg(customer.getHeadImg());
            result.setUserName(customer.getUserName());
            result.setContent(order.getContent());
            result.setCreateTime(travelReview.getCreateTime());
            //旅声评论数加1
            TravelVoice travelVoice = travelVoiceService.get(order.getTravelVoiceId());
            travelVoice.setReviewCount(travelVoice.getReviewCount() + 1);
            travelVoiceService.save(travelVoice);
            //不是旅声作者则添加评论动态
            if (!travelVoice.getUserId().equals(order.getUserId())) {
                guideMessageService.addGuideMessage(order.getUserId(), GuideMessageType.reviewTravelVoice, travelVoice.getContent(), order.getContent(), order.getTravelVoiceId().toString(), travelVoice.getUserId());
            }
            result.setReviewCount(travelVoice.getReviewCount());
        } catch (BusinessException e) {
            log.info("添加旅声评论失败！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail(String.format("添加旅声评论失败！%s", e.getMessage()));
        }catch (Exception e) {
            log.info("保存失败！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("保存失败！");
        }

        return result;
    }

    /**
     * 获取评论列表
     * @param travelReviewListOrder
     * @return
     */
    @Override
    public PageResult<TravelReviewDto> getTravelReviewList(TravelReviewListOrder travelReviewListOrder) {
        PageResult<TravelReviewDto> pageResult = new PageResult<>();

        try{
            pageResult = PageResult.from(this.getEntityDao().getPageTravelReviewList(travelReviewListOrder));
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
