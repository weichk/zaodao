/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-30
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.TravelPraiseLogDao;
import com.acooly.zaodao.platform.order.TravelVoicePraiseOrder;
import com.acooly.zaodao.platform.service.manage.TravelPraiseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acooly.zaodao.platform.entity.TravelPraiseLog;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * zd_travel_praise_log Service实现
 *
 * Date: 2017-10-30 15:02:33
 *
 * @author zhike
 *
 */
@Service("travelPraiseLogService")
public class TravelPraiseLogServiceImpl extends EntityServiceImpl<TravelPraiseLog, TravelPraiseLogDao> implements TravelPraiseLogService {

    /**
     * 根据用户ID和旅声ID删除点赞记录
     */
    @Override
    public void removeTravelVoicePraise(String userId, Long travelVoiceId) {
        this.getEntityDao().deleteByUserIdAndTravelVoiceId(userId, travelVoiceId);
    }

    /**
     * 根据用户ID和旅声ID查询旅声点赞信息
     * @param travelVoicePraiseOrder
     * @return
     */
    @Override
    public TravelPraiseLog getTravelPraiseLog(TravelVoicePraiseOrder travelVoicePraiseOrder) {
        return this.getEntityDao().getByUserIdAndTravelVoiceId(travelVoicePraiseOrder.getUserId(),travelVoicePraiseOrder.getTravelVoiceId());
    }

    /**
     * 获取旅声点赞数量
     * @param travelVoicePraiseOrder
     * @return
     */
    @Override
    public Long getTravelPraiseCount(TravelVoicePraiseOrder travelVoicePraiseOrder) {
        return this.getEntityDao().getCountByTravelVoiceId(travelVoicePraiseOrder.getTravelVoiceId());
    }

    @Override
    public void removeByTravelVoiceIds(Long[] ids) {
        for(Long id : ids){
            TravelPraiseLog travelPraiseLog = this.getEntityDao().getByTravelVoiceId(id);
            if(travelPraiseLog != null) {
                this.getEntityDao().delete(travelPraiseLog);
            }
        }
    }
}
