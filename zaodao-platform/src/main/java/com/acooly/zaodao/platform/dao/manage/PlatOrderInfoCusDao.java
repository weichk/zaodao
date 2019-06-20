/*
 * 修订记录:
 * zhike@yiji.com 2017-06-11 23:56 创建
 *
 */
package com.acooly.zaodao.platform.dao.manage;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.zaodao.platform.dto.OrderInfoDto;
import com.acooly.zaodao.platform.entity.PlatOrderInfo;
import com.acooly.zaodao.platform.enums.OrderCallEnum;
import com.acooly.zaodao.platform.order.QueryPlateOrder;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
public interface PlatOrderInfoCusDao {
    /**
     * 分页获取订单列表
     *
     * @param userId
     * @return
     */
    PageInfo<OrderInfoDto> getPageOrderInfoListByUserId(PageInfo<OrderInfoDto> pageInfo, String userId, String orderStatus,Integer isTourGuide);

    /**
     * 根据订单状态分页获取订单信息
     * @param pageInfo
     * @param orderStatus
     * @return
     */
    PageInfo<PlatOrderInfo> getPageOrderInfoListByOrderStatus(PageInfo<PlatOrderInfo> pageInfo, String orderStatus);

    /**
     * 获取订单详情
     * @param orderNo
     * @return
     */
    OrderInfoDto getOrderDetail(String orderNo);
}
