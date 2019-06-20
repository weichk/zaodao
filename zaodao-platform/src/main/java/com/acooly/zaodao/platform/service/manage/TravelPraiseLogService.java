/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-30
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.TravelPraiseLog;
import com.acooly.zaodao.platform.order.TravelVoicePraiseOrder;

import java.io.Serializable;

/**
 * zd_travel_praise_log Service接口
 *
 * Date: 2017-10-30 15:02:32
 * @author zhike
 *
 */
public interface TravelPraiseLogService extends EntityService<TravelPraiseLog> {

    /**
     * 删除旅声点赞
     * @param userId
     * @param travelVoiceId
     */
    void removeTravelVoicePraise(String userId, Long travelVoiceId);

    /**
     * 获取旅声点赞记录
     * @param travelVoicePraiseOrder
     * @return
     */
    TravelPraiseLog getTravelPraiseLog(TravelVoicePraiseOrder travelVoicePraiseOrder);

    /**
     * 获取点赞数量
     * @param travelVoicePraiseOrder
     * @return
     */
    Long getTravelPraiseCount(TravelVoicePraiseOrder travelVoicePraiseOrder);

    /**
     * 批量删除旅声点赞
     * @param ids
     */
    void removeByTravelVoiceIds(Long[] ids);
}
