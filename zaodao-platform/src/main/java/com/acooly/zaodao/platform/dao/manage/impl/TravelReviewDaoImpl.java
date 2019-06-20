package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.TravelReviewCusDao;
import com.acooly.zaodao.platform.dto.TravelReviewDto;
import com.acooly.zaodao.platform.entity.TravelReview;
import com.acooly.zaodao.platform.order.TravelReviewListOrder;

/**
 * @author xiaohong
 * @create 2017-10-27 15:27
 **/
public class TravelReviewDaoImpl extends AbstractJdbcTemplateDao implements TravelReviewCusDao {
    /**
     * 获取评论列表
     * @param travelReviewListOrder
     * @return
     */
    @Override
    public PageInfo<TravelReviewDto> getPageTravelReviewList(TravelReviewListOrder travelReviewListOrder) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.id as travelReviewId, a.user_id AS userId,b.user_name AS userName,b.real_name AS realName,b.head_Img AS headImg,a.review_parent_id AS reviewParentId,a.content,a.create_time AS createTime ");
        sql.append(" FROM zd_travel_review a JOIN zd_customer b ON(a.user_id=b.user_id) ");
        sql.append(String.format(" WHERE a.travel_voice_id='%s' ORDER BY a.create_time desc", travelReviewListOrder.getTravelVoiceId()));

        PageInfo<TravelReviewDto> pageInfo = query(travelReviewListOrder.getPageInfo(), sql.toString(), TravelReviewDto.class);

        return pageInfo;
    }
}
