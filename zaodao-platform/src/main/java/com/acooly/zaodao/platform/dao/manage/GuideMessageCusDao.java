package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dto.GuideMessageDto;
import com.acooly.zaodao.platform.order.GuideMessageListOrder;

public interface GuideMessageCusDao {
    /**
     * 获取导游详情列表
     * @param guideMessageListOrder
     * @return
     */
    PageInfo<GuideMessageDto> getPageGuideMessageList(GuideMessageListOrder guideMessageListOrder);
}
