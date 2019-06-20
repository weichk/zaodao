/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-26
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.TravelVoiceDto;
import com.acooly.zaodao.platform.entity.TravelVoice;
import com.acooly.zaodao.platform.order.TravelVoiceAddOrder;
import com.acooly.zaodao.platform.order.TravelVoiceDetailOrder;
import com.acooly.zaodao.platform.order.TravelVoiceListOrder;
import com.acooly.zaodao.platform.order.TravelVoicePraiseOrder;
import com.acooly.zaodao.platform.result.TravelVoiceDetailResult;
import com.acooly.zaodao.platform.result.TravelVoicePraiseResult;

/**
 * zd_travel_voice Service接口
 *
 * Date: 2017-10-26 18:05:37
 * @author zhike
 *
 */
public interface TravelVoiceService extends EntityService<TravelVoice> {

    /**
     * 添加旅声
     * @param travelVoiceAddOrder
     * @return
     */
    ResultBase addTravelVoice(TravelVoiceAddOrder travelVoiceAddOrder);

    /**
     * 获取旅声列表
     * @param travelVoiceListOrder
     * @return
     */
    PageResult<TravelVoiceDto> getTravelVoiceList(TravelVoiceListOrder travelVoiceListOrder);

    /**
     * 获取旅声详情
     * @param travelVoiceDetailOrder
     * @return
     */
    TravelVoiceDetailResult getTravelVoiceDetail(TravelVoiceDetailOrder travelVoiceDetailOrder);

    /**
     * 旅声点赞
     * @param travelVoicePraiseOrder
     * @return
     */
    TravelVoicePraiseResult praiseTravelVoice(TravelVoicePraiseOrder travelVoicePraiseOrder);
}
