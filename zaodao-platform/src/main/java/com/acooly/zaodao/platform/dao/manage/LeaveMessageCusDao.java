/*
 * 修订记录:
 * zhike@yiji.com 2017-06-13 15:00 创建
 *
 */
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dto.LeaveMessageDto;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public interface LeaveMessageCusDao {

    /**
     * 分页获取导游留言消息列表
     * @param userId
     * @return
     */
    PageInfo<LeaveMessageDto> getPageLeaveMessages(PageInfo<LeaveMessageDto> pageInfo, String userId);

    /**
     *  分页获取导游留言及回复
     * @param pageInfo
     * @param userId
     * @return
     */
    PageInfo<LeaveMessageDto> getLeaveMsgByGuideUserId(PageInfo<LeaveMessageDto> pageInfo, String userId);
}
