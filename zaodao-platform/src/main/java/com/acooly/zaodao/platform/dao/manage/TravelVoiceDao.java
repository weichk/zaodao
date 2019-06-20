/*
* zhike@yiji.com Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by zhike
* date:2017-10-26
*/
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.module.jpa.EntityJpaDao;
import com.acooly.zaodao.platform.dto.TravelVoiceDto;
import com.acooly.zaodao.platform.entity.TravelVoice;
import com.acooly.zaodao.platform.order.TravelVoiceDetailOrder;
import com.acooly.zaodao.platform.order.TravelVoiceListOrder;
import org.springframework.data.jpa.repository.Query;

/**
 * zd_travel_voice JPA Dao
 *
 * Date: 2017-10-26 18:05:37
 * @author zhike
 *
 */
public interface TravelVoiceDao extends EntityJpaDao<TravelVoice, Long>,TravelVoiceCusDao {

}
