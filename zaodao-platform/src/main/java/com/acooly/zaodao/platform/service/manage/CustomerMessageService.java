/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-01
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.MessageDto;
import com.acooly.zaodao.platform.entity.CustomerMessage;
import com.acooly.zaodao.platform.order.MessageCountOrder;
import com.acooly.zaodao.platform.order.MessageListOrder;
import com.acooly.zaodao.platform.result.MessageCountResult;

import java.util.List;

/**
 * 用户消息表 Service接口
 *
 * Date: 2017-06-01 14:41:42
 * @author zhike
 *
 */
public interface CustomerMessageService extends EntityService<CustomerMessage> {

    /**
     * 分页获取用户系统消息列表
     * @param userId
     * @return
     */
    PageInfo<CustomerMessage> getCustomerPageEntityInfo(Integer currentPage, Integer countOfCurrentPage, String userId);

    /**
     * 修改消息
     * @param list
     */
    void modifyCustomerMessages(List<MessageDto> list);
    /**
     * 获取会员未读系统消息列表
     * @param userId
     * @return
     */
    List<CustomerMessage> getNotReadEntitysByUserId(String userId);

    /**
     * 更新状态为已读
     * @param userId
     */
    void updateEntityReadStatus(String userId);

    /**
     * 获取系统消息列表
     * @param messageListOrder
     * @return
     */
    PageResult<MessageDto> getCustomerMessageList(MessageListOrder messageListOrder);

    /**
     * 获取用户消息个数(含系统消息)
     * @param messageCountOrder
     * @return
     */
    MessageCountResult getCustomerMessageCount(MessageCountOrder messageCountOrder);
}
