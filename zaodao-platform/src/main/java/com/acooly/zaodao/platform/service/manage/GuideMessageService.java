/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-31
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.GuideMessageDto;
import com.acooly.zaodao.platform.entity.GuideMessage;
import com.acooly.zaodao.platform.enums.GuideMessageType;
import com.acooly.zaodao.platform.order.CustomerFocusAddOrder;
import com.acooly.zaodao.platform.order.GuideMessageListOrder;

import java.util.List;

/**
 * zd_guide_message Service接口
 *
 * Date: 2017-10-31 16:45:32
 * @author zhike
 *
 */
public interface GuideMessageService extends EntityService<GuideMessage> {
    /**
     * 获取导游详情动态列表
     * @param guideMessageListOrder
     * @return
     */
    PageResult<GuideMessageDto> getGuideMessageList(GuideMessageListOrder guideMessageListOrder);

    /**
     * 修改消息
     * @param list
     */
    void modifyGuideMessages(List<GuideMessageDto> list);
    /**
     * 添加导游详情动态
     * @param userId
     * @param guidMessageType
     * @param content
     * @param contentUserId
     * @return
     */
    GuideMessage addGuideMessage(String userId, GuideMessageType guidMessageType, String title, String content, String contentBomId, String contentUserId);

    /**
     * 删除详情
     * @param userId
     * @param guidMessageType
     * @param contentBomId
     */
    void removeGuidMessage(String userId, GuideMessageType guidMessageType, String contentBomId);

    /**
     * 修改消息读取状态
     * @param i
     * @return
     */
    Integer updateGuideMessageReadStatus(Long id, int i);

    /**
     * 获取消息个数
     * @param userId
     * @return
     */
    int getGuideMessageCount(String userId);
}
