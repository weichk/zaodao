package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.utils.Dates;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.CustomerCusDao;
import com.acooly.zaodao.platform.dto.PointTradeInfDto;
import com.acooly.zaodao.platform.order.TradeCaListOrder;

/**
 * @author xiaohong
 * @create 2017-11-29 19:52
 **/
public class CustomerDaoImpl extends AbstractJdbcTemplateDao implements CustomerCusDao {
    /**
     * 钙片列表
     * @param order
     * @return
     */
    @Override
    public PageInfo<PointTradeInfDto> getPagePointTradeList(TradeCaListOrder order) {
        StringBuilder sbSql = new StringBuilder();
        sbSql.append("select id as pointTradeId,user_name as userId,end_available as balance,trade_type as tradeType,amount,create_time as createTime,busi_type_text as busiTypeText from point_trade");
        sbSql.append(String.format(" WHERE user_name='%s'", order.getUserId()));

        if(order.getStartTime() != null && order.getEndTime() != null){
            sbSql.append(String.format(" AND create_time BETWEEN '%s' AND '%s'", Dates.format(order.getStartTime(), "yyyy-MM-dd HH:mm:ss"), Dates.format(order.getEndTime(), "yyyy-MM-dd HH:mm:ss")));
        }

        sbSql.append("ORDER BY create_time DESC");

        PageInfo<PointTradeInfDto> pageInfo = query(order.getPageInfo(), sbSql.toString(), PointTradeInfDto.class);

        return pageInfo;
    }
}
