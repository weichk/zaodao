package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.service.EntityService;
import com.acooly.core.utils.Money;
import com.acooly.zaodao.platform.dto.OrderServiceConditionDto;
import com.acooly.zaodao.platform.dto.OrderServiceFeeDto;
import com.acooly.zaodao.platform.dto.QueryServiceFeeDto;
import com.acooly.zaodao.platform.entity.OrderServiceFee;
import com.acooly.zaodao.platform.entity.PlatOrderInfo;
import com.acooly.zaodao.platform.enums.ServiceFeeNameType;
import com.acooly.zaodao.platform.result.ServiceFeeListResult;

import java.util.List;

/**
 * 服务费
 *
 * @author xiaohong
 * @create 2018-05-29 9:50
 **/
public interface OrderServiceFeeService extends EntityService<OrderServiceFee> {

    /**
     * 查询服务费
     */
    PageInfo<OrderServiceFeeDto> getServiceFeeList(QueryServiceFeeDto queryServiceFeeDto);

    /**
     * 添加/修改服务费实体
     */
    OrderServiceFee saveOrderServiceFee(OrderServiceFeeDto orderServiceFee, boolean isModify);

    /**
     * 获取取消订单费用
     */
    Money getCancelOrderServiceFee(PlatOrderInfo platOrderInfo);

    /**
     * 获取服务费规则列表
     */
    ServiceFeeListResult getOrderServiceFeeList();

    /**
     * 根据feeName获取服务费集合
     */
    List<OrderServiceFee> getByFeeName(ServiceFeeNameType orderService);
}
