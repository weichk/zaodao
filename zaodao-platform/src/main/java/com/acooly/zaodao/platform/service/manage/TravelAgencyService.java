/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2018 All Rights Reserved.
 * create by zhike
 * date:2018-05-04
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.TravelAgencyDto;
import com.acooly.zaodao.platform.entity.TravelAgency;
import com.acooly.zaodao.platform.order.TravelAgencyAddOrder;
import com.acooly.zaodao.platform.order.TravelAgencyListOrder;
import com.acooly.zaodao.platform.result.TravelAgencyListResult;

import java.util.List;

/**
 * 旅行社 Service接口
 *
 * Date: 2018-05-04 16:34:01
 * @author zhike
 *
 */
public interface TravelAgencyService extends EntityService<TravelAgency> {

    /**
     * 添加旅行社
     */
    ResultBase addTravelAgency(TravelAgencyAddOrder order);

    /**
     * 获取旅行社列表
     */
    TravelAgencyListResult getTravelAgencyInfos(TravelAgencyListOrder order);
}
