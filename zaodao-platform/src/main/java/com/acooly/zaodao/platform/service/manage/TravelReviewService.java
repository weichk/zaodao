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
import com.acooly.zaodao.platform.dto.TravelReviewDto;
import com.acooly.zaodao.platform.entity.TravelReview;
import com.acooly.zaodao.platform.order.TravelReviewAddOrder;
import com.acooly.zaodao.platform.order.TravelReviewListOrder;
import com.acooly.zaodao.platform.result.TravelReviewAddResult;

/**
 * zd_travel_review Service接口
 *
 * Date: 2017-10-26 19:17:16
 * @author zhike
 *
 */
public interface TravelReviewService extends EntityService<TravelReview> {
    /**
     * 添加评论
     * @param travelReviewAddOrder
     * @return
     */
    TravelReviewAddResult addTravelReivew(TravelReviewAddOrder travelReviewAddOrder);

    /**
     * 获取评论列表
     * @param travelReviewListOrder
     * @return
     */
    PageResult<TravelReviewDto> getTravelReviewList(TravelReviewListOrder travelReviewListOrder);
}
