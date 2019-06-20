/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-01
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.zaodao.platform.dao.manage.CustomerMessageDao;
import com.acooly.zaodao.platform.dto.MessageDto;
import com.acooly.zaodao.platform.entity.CustomerMessage;
import com.acooly.zaodao.platform.entity.GuideMessage;
import com.acooly.zaodao.platform.order.MessageCountOrder;
import com.acooly.zaodao.platform.order.MessageListOrder;
import com.acooly.zaodao.platform.result.MessageCountResult;
import com.acooly.zaodao.platform.service.manage.CustomerMessageService;
import com.acooly.zaodao.platform.service.manage.GuideMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户消息表 Service实现
 *
 * Date: 2017-06-01 14:41:42
 *
 * @author zhike
 *
 */
@Slf4j
@Service("customerMessageService")
public class CustomerMessageServiceImpl extends EntityServiceImpl<CustomerMessage, CustomerMessageDao> implements CustomerMessageService {

    @Autowired
    private GuideMessageService guideMessageService;

    @Override
    public PageInfo<CustomerMessage> getCustomerPageEntityInfo(Integer currentPage, Integer countOfCurrentPage, String userId) {
        return getEntityDao().getCustomerPageEntityInfo(getMyPageInfo(currentPage,countOfCurrentPage),userId);
    }

    /**
     * 获取分页对象
     *
     * @param currentPage
     * @return
     */
    private PageInfo<CustomerMessage> getMyPageInfo(Integer currentPage, Integer countOfCurrentPage) {
        PageInfo<CustomerMessage> pageinfo = new PageInfo<>();
        pageinfo.setCurrentPage(currentPage);
        pageinfo.setCountOfCurrentPage(countOfCurrentPage);
        return pageinfo;
    }

    @Override
    public List<CustomerMessage> getNotReadEntitysByUserId(String userId) {
        return getEntityDao().getNotReadEntitysByUserId(userId);
    }

    @Override
    public void updateEntityReadStatus(String userId) {
        getEntityDao().updateEntityReadStatus(userId);
    }

    /**
     * 系统消息列表
     * @param order
     * @return
     */
    @Override
    public PageResult<MessageDto> getCustomerMessageList(MessageListOrder order) {
        PageResult<MessageDto> pageResult = new PageResult<>();

        try{
            order.check();
            pageResult = PageResult.from(this.getEntityDao().getPageMessageList(order));
            modifyCustomerMessages(pageResult.getDto().getPageResults());
        } catch (Exception e){
            log.info("系统错误！{}", e.getMessage());
            pageResult.setStatus(ResultStatus.failure);
            pageResult.setCode(ResultStatus.failure.getCode());
            pageResult.setDetail("系统错误！");
        }

        return pageResult;
    }
    @Override
    @Transactional
    public void modifyCustomerMessages(List<MessageDto> list){
        for (MessageDto messageDto : list){
            if(messageDto.getReadStatus() == null || messageDto.getReadStatus() == 0){
                this.getEntityDao().updateCustomerMessageReadStatus(messageDto.getCustomerMessageId(), 1);
                messageDto.setReadStatus(1);
            }
        }
    }

    /**
     * 获取用户消息个数(含系统消息)
     * @param messageCountOrder
     * @return
     */
    @Override
    public MessageCountResult getCustomerMessageCount(MessageCountOrder messageCountOrder) {
        MessageCountResult result = new MessageCountResult();

        try{
            messageCountOrder.check();
            int guideMessageCount = guideMessageService.getGuideMessageCount(messageCountOrder.getUserId());
            int customerMessageCount = this.getEntityDao().getCustomerMessageCount(messageCountOrder.getUserId());
            result.setMessageCount(guideMessageCount+customerMessageCount);
        }
        catch (Exception e){
            log.info("系统错误！{}", e.getMessage());
            result.setStatus(ResultStatus.failure);
            result.setCode(ResultStatus.failure.getCode());
            result.setDetail("系统错误！");
        }

        return result;
    }

}
