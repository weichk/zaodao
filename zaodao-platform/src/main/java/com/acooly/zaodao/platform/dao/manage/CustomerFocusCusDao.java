package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dto.CustomerFocusCountDto;
import com.acooly.zaodao.platform.dto.CustomerFocusDto;
import com.acooly.zaodao.platform.order.CustomerFocusCountOrder;
import com.acooly.zaodao.platform.order.CustomerFocusListOrder;

/**
 * 关注数量和粉丝数
 *
 * @author xiaohong
 * @create 2017-10-30 18:17
 **/
public interface CustomerFocusCusDao {
    /**
     * 关注数量和粉丝数
     * @param userId
     * @return
     */
    CustomerFocusCountDto getCustomerFocusCount(String userId);

    /**
     * 获取关注/粉丝列表
     * @param customerFocusListOrder
     * @return
     */
    PageInfo<CustomerFocusDto> getCustomerFocusList(CustomerFocusListOrder customerFocusListOrder);
}
