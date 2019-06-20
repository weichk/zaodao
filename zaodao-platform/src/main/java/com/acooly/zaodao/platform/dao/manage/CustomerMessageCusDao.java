/*
 * 修订记录:
 * zhike@yiji.com 2017-06-02 14:52 创建
 *
 */
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dto.MessageDto;
import com.acooly.zaodao.platform.entity.CustomerMessage;
import com.acooly.zaodao.platform.order.MessageListOrder;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public interface CustomerMessageCusDao {

    /**
     * 分页获取用户系统消息列表
     * @param userId
     * @return
     */
    PageInfo<CustomerMessage> getCustomerPageEntityInfo(PageInfo<CustomerMessage> pageInfo, String userId);

    /**
     * 获取系统消息列表
     * @param messageListOrder
     * @return
     */
    PageInfo<MessageDto> getPageMessageList(MessageListOrder messageListOrder);
}
