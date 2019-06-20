/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-12-11
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.PlatOrderEvalDto;
import com.acooly.zaodao.platform.entity.PlatOrderEval;
import com.acooly.zaodao.platform.order.PlatOrderEvalListOrder;
import com.acooly.zaodao.platform.order.PlatOrderEvalOrder;

/**
 * zd_plat_order_eval Service接口
 *
 * Date: 2017-12-11 17:03:28
 * @author zhike
 *
 */
public interface PlatOrderEvalService extends EntityService<PlatOrderEval> {

    /**
     * 添加订单评价
     * @param order
     * @return
     */
    ResultBase addPlatOrderEval(PlatOrderEvalOrder order);

    /**
     * 获取订单评价列表
     * @param order
     * @return
     */
    PageResult<PlatOrderEvalDto> getPlateOrderEvalList(PlatOrderEvalListOrder order);

    /**
     * 获取订单评价
     */
    PlatOrderEval getPlatOrderEval(String userId, String orderNo);

    /**
     * 订单评价数量
     * @param guideUserId
     * @return
     */
    Integer getPlatOrderEvalCount(String guideUserId);
}
