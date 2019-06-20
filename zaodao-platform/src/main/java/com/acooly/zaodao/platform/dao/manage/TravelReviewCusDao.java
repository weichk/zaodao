package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dto.TravelReviewDto;
import com.acooly.zaodao.platform.entity.TravelReview;
import com.acooly.zaodao.platform.order.TravelReviewListOrder;

public interface TravelReviewCusDao {
    /**
     * 获取评论列表
     * @param travelReviewListOrder
     * @return
     */
    PageInfo<TravelReviewDto> getPageTravelReviewList(TravelReviewListOrder travelReviewListOrder);
}
