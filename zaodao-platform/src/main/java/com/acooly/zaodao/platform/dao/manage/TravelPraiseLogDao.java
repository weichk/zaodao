/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-30
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.TravelPraiseLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * zd_travel_praise_log JPA Dao
 *
 * Date: 2017-10-30 15:02:33
 * @author zhike
 *
 */
public interface TravelPraiseLogDao extends EntityJpaDao<TravelPraiseLog, Long> {
    /**
     * 根据用户ID和旅声ID删除点赞记录
     * @param userId
     * @param travelVoiceId
     */
    @Modifying
    void deleteByUserIdAndTravelVoiceId(String userId, Long travelVoiceId);

    /**
     * 根据用户ID和旅声ID获取旅声点赞记录
     * @return
     */
    TravelPraiseLog getByUserIdAndTravelVoiceId(String userId, Long travelVoiceId);

    /**
     * 获取旅声点赞数量
     * @param travelVoiceId
     * @return
     */
    @Query(value = "SELECT COUNT(1) FROM zd_travel_praise_log WHERE travel_voice_id=?1", nativeQuery = true)
    Long getCountByTravelVoiceId(Long travelVoiceId);

    /**
     * 根据旅声ID获取旅声点赞记录
     * @param id
     * @return
     */
    TravelPraiseLog getByTravelVoiceId(Long id);
}
