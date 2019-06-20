package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dto.PlatOrderEvalDto;
import com.acooly.zaodao.platform.order.PlatOrderEvalListOrder;

public interface PlatOrderEvalCusDao {

    /**
     * 分页获取评价列表
     * @param order
     * @return
     */
    PageInfo<PlatOrderEvalDto> getPagePlateOrderEvalList(PlatOrderEvalListOrder order);
}
