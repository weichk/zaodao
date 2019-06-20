package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dto.TravelVoiceDto;
import com.acooly.zaodao.platform.entity.TravelVoice;
import com.acooly.zaodao.platform.order.TravelVoiceDetailOrder;
import com.acooly.zaodao.platform.order.TravelVoiceListOrder;

public interface TravelVoiceCusDao {
    /**
     * 获取旅声列表
     * @param travelVoiceListOrder
     * @return
     */
    PageInfo<TravelVoiceDto> getPageTravelVoiceList(TravelVoiceListOrder travelVoiceListOrder);

    /**
     * 获取旅声详情
     * @param travelVoiceDetailOrder
     * @return
     */
    TravelVoiceDto getTravelVoiceDetail(TravelVoiceDetailOrder travelVoiceDetailOrder);
}
