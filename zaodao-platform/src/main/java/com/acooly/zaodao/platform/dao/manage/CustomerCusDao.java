package com.acooly.zaodao.platform.dao.manage;

import com.acooly.zaodao.platform.dto.PointTradeInfDto;
import com.acooly.zaodao.platform.order.TradeCaListOrder;
import com.acooly.core.common.dao.support.PageInfo;

public interface CustomerCusDao {

    /**
     * 获取钙片列表
     * @param order
     * @return
     */
    PageInfo<PointTradeInfDto> getPagePointTradeList(TradeCaListOrder order);
}
