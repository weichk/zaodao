/*
 * 修订记录:
 * zhike@yiji.com 2017-06-12 23:05 创建
 *
 */
package com.acooly.zaodao.platform.dao.manage.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.utils.Strings;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.zaodao.platform.dao.manage.TradingRecordCusDao;
import com.acooly.zaodao.platform.entity.TradingRecord;
import org.apache.commons.lang3.StringUtils;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public class TradingRecordDaoImpl extends AbstractJdbcTemplateDao implements TradingRecordCusDao {

    @Override
    public PageInfo<TradingRecord> getPageTradingRecordByUserId(PageInfo<TradingRecord> pageInfo, String userId, String tradingType, String startTime, String endTime) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM zd_trading_record WHERE userId = '" + userId + "'");

        if (Strings.isNotBlank(tradingType) && !StringUtils.equals(tradingType, "all")) {
            sql.append(" AND trading_type = '" + tradingType + "'");
        }
        if (StringUtils.isNotBlank(startTime)) {
            sql.append(" AND create_time >= '" + startTime +" 00:00:00"+ "'");
        }
        if (StringUtils.isNotBlank(endTime)) {
            sql.append(" AND create_time <= '" + endTime +" 23:59:59"+ "'");
        }
        sql.append(" order by create_time desc");
        PageInfo<TradingRecord> result = query(pageInfo, sql.toString(), TradingRecord.class);
        return result;
    }
}
