package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.PlatOrderEvalCusDao;
import com.acooly.zaodao.platform.dto.PlatOrderEvalDto;
import com.acooly.zaodao.platform.order.PlatOrderEvalListOrder;

/**
 * @author xiaohong
 * @create 2017-12-12 10:04
 **/
public class PlatOrderEvalDaoImpl  extends AbstractJdbcTemplateDao implements PlatOrderEvalCusDao {
    /**
     * 获取分页订单评价
     * @param order
     * @return
     */
    @Override
    public PageInfo<PlatOrderEvalDto> getPagePlateOrderEvalList(PlatOrderEvalListOrder order) {
        StringBuilder sbSql = new StringBuilder();

        sbSql.append("SELECT b.user_id AS userId,b.user_name AS userName,b.real_name AS realName");
        sbSql.append(",b.head_img AS headImg,a.score,a.content,a.create_time AS createTime");
        sbSql.append(" FROM zd_plat_order_eval a");
        sbSql.append(" JOIN zd_customer b ON(a.tourist_id=b.user_id)");
        sbSql.append(String.format(" WHERE a.tour_guide_id='%s'", order.getUserId()));
        sbSql.append(" ORDER BY a.create_time DESC");
        PageInfo<PlatOrderEvalDto> pageInfo = query(order.getPageInfo(), sbSql.toString(), PlatOrderEvalDto.class);

        return pageInfo;
    }
}
