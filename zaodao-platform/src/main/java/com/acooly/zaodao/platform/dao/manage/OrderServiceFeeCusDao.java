package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dto.OrderServiceConditionDto;
import com.acooly.zaodao.platform.dto.OrderServiceFeeDto;
import com.acooly.zaodao.platform.dto.QueryServiceFeeDto;
import com.acooly.zaodao.platform.enums.ServiceFeeNameType;

import java.util.List;

/**
 * 服务费
 *
 * @author xiaohong
 * @create 2018-05-29 13:54
 **/
public interface OrderServiceFeeCusDao {
    /**
     * 获取服务费分页列表
     */
    PageInfo<OrderServiceFeeDto> getPageOrderServiceFee(QueryServiceFeeDto query);

    /**
     * 根据feeName获取服务费列表
     */
    List<OrderServiceFeeDto> getOrderServiceFeeListByFeeName(ServiceFeeNameType feeName);
}
