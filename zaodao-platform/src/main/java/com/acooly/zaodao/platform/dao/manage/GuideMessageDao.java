/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-31
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.entity.GuideMessage;
import com.acooly.zaodao.platform.order.GuideMessageListOrder;
import org.apache.ibatis.annotations.Delete;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * zd_guide_message JPA Dao
 *
 * Date: 2017-10-31 16:45:32
 * @author zhike
 *
 */
public interface GuideMessageDao extends EntityJpaDao<GuideMessage, Long>,GuideMessageCusDao {
    @Modifying
    @Query("DELETE FROM GuideMessage WHERE userId = ?1 AND messageType = ?2 AND contentBomId = ?3")
    void deleteGuidMessage(String userId, String code, String contentBomId);

    @Modifying
    @Query("UPDATE GuideMessage SET readStatus=?2 WHERE Id = ?1")
    Integer updateReadStatus(Long id, int status);

    //@Query("SELECT COUNT(1) FROM GuideMessage WHERE contentBomId=?1 AND readStatus=0")
    /*@Query(value = "SELECT SUM(cnt) AS cnt FROM (" +
            "SELECT COUNT(1) AS cnt FROM zd_guide_message a " +
            "JOIN zd_travel_voice b ON(a.content_bom_id=b.id) " +
            "WHERE b.user_id=?1 AND a.read_status=0 " +
            "UNION " +
            "SELECT COUNT(1) AS cnt FROM zd_guide_message a " +
            "JOIN zd_course b ON(a.content_bom_id=b.id) " +
            "WHERE b.user_id=?1 AND a.read_status=0 " +
            "UNION " +
            "SELECT COUNT(1) AS cnt FROM zd_guide_message a WHERE a.content_bom_id=?1 AND a.read_status=0) ta", nativeQuery = true)*/
    @Query("SELECT COUNT(1) FROM GuideMessage WHERE contentUserId=?1 AND readStatus=0")
    int getGuideMessageCount(String userId);
}
