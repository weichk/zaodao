/*
 * 修订记录:
 * zhike@yiji.com 2017-06-19 11:33 创建
 *
 */
package com.acooly.zaodao.platform.dao.platform.impl;

import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.module.point.domain.PointTrade;
import com.acooly.zaodao.platform.dao.platform.PointTradeInfoDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Service
public class PointTradeInfoImplDao extends AbstractJdbcTemplateDao implements PointTradeInfoDao {
    @Override
    public List<PointTrade> findByUserIdAndGoodsId(String userId, String goodsId) {
        String sql = "SELECT * FROM point_trade WHERE user_name = '" + userId + "' AND busi_id = '" + goodsId + "'";
        List<PointTrade> pointTradeList = queryForList(sql, PointTrade.class);
        return pointTradeList;
    }
}
