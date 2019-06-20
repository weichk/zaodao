/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2018 All Rights Reserved.
 * create by zhike
 * date:2018-05-07
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.OrderGuideTag;
import com.acooly.zaodao.platform.order.GuideEvalTagsOrder;
import com.acooly.zaodao.platform.result.GuideTagListResult;

/**
 * zd_order_guide_tag Service接口
 *
 * Date: 2018-05-07 20:39:17
 * @author zhike
 *
 */
public interface OrderGuideTagService extends EntityService<OrderGuideTag> {

    /**
     * 获取导游评价标签统计
     */
    GuideTagListResult getGuideEvalTags(GuideEvalTagsOrder order);
}
