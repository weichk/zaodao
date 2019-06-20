/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-31
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.ResultStatus;
import com.acooly.zaodao.platform.dao.manage.GuideMessageDao;
import com.acooly.zaodao.platform.dto.GuideMessageDto;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.enums.GuideMessageType;
import com.acooly.zaodao.platform.order.CustomerFocusAddOrder;
import com.acooly.zaodao.platform.order.GuideMessageListOrder;
import com.acooly.zaodao.platform.service.manage.CustomerService;
import com.acooly.zaodao.platform.service.manage.GuideMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acooly.zaodao.platform.entity.GuideMessage;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * zd_guide_message Service实现
 *
 * Date: 2017-10-31 16:45:32
 *
 * @author zhike
 *
 */
@Slf4j
@Service("guideMessageService")
public class GuideMessageServiceImpl extends EntityServiceImpl<GuideMessage, GuideMessageDao> implements GuideMessageService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private GuideMessageService guideMessageService;
    /**
     * 获取导游详情动态列表
     */
    @Override
    @Transactional
    public PageResult<GuideMessageDto> getGuideMessageList(GuideMessageListOrder guideMessageListOrder) {
        PageResult<GuideMessageDto> pageResult = new PageResult<>();
        try{
            guideMessageListOrder.check();
            pageResult = PageResult.from(this.getEntityDao().getPageGuideMessageList(guideMessageListOrder));
            modifyGuideMessages(pageResult.getDto().getPageResults());
        } catch (Exception e){
            log.info("系统错误！{}", e.getMessage());
            pageResult.setStatus(ResultStatus.failure);
            pageResult.setCode(ResultStatus.failure.getCode());
            pageResult.setDetail("系统错误！");
        }
        return pageResult;
    }

    /**
     * 调整消息状态
     */
    @Override
    @Transactional
    public void modifyGuideMessages(List<GuideMessageDto> list){
        for (GuideMessageDto guideMessageDto : list){
            if(guideMessageDto.getReadStatus() == null || guideMessageDto.getReadStatus() == 0){
                guideMessageService.updateGuideMessageReadStatus(guideMessageDto.getGuidMessageId(), 1);
                guideMessageDto.setReadStatus(1);
            }
        }
    }
    /**
     * 添加导游详情动态
     * @param userId
     * @param guidMessageType
     * @param content
     * @return
     */
    @Override
    public GuideMessage addGuideMessage(String userId, GuideMessageType guidMessageType, String title, String content, String contentBomId, String contentUserId) {
        GuideMessage guideMessage = null;

        try {
            customerService.checkUser(userId);
            Customer customer = customerService.getUser(userId);
            if (null == customer) {
                throw new BusinessException("未找到用户");
            } else if (null == guidMessageType) {
                throw new BusinessException("动态类型不能为空");
            }

            guideMessage = new GuideMessage();
            guideMessage.setUserId(customer.getUserId());
            guideMessage.setMessageType(guidMessageType.getCode());
            guideMessage.setMessageName(guidMessageType.getMessage());
            guideMessage.setTitle(title);
            guideMessage.setContent(content);
            guideMessage.setContentBomId(contentBomId);
            guideMessage.setContentUserId(contentUserId);
            guideMessage.setReadStatus(0);

            this.getEntityDao().save(guideMessage);
        } catch (Exception e) {
            log.info("保存失败！{}", e.getMessage());
        }

        return guideMessage;
    }

    /**
     * 删除详情
     * @param userId
     * @param guidMessageType
     * @param contentBomId
     */
    @Override
    public void removeGuidMessage(String userId, GuideMessageType guidMessageType, String contentBomId) {
        this.getEntityDao().deleteGuidMessage(userId, guidMessageType.getCode(), contentBomId);
    }

    /**
     * 修改消息读取状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public Integer updateGuideMessageReadStatus(Long id, int status) {
        return this.getEntityDao().updateReadStatus(id, status);
    }

    /**
     * 获取给用户的消息个数
     * @param userId
     * @return
     */
    @Override
    public int getGuideMessageCount(String userId) {
        return this.getEntityDao().getGuideMessageCount(userId);
    }
}
