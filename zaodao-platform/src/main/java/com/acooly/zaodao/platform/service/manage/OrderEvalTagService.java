/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2018 All Rights Reserved.
 * create by zhike
 * date:2018-05-07
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.OrderEvalTag;
import com.acooly.zaodao.platform.result.OrderEvalTagListResult;

/**
 * zd_order_eval_tag Service接口
 *
 * Date: 2018-05-07 20:38:48
 * @author zhike
 *
 */
public interface OrderEvalTagService extends EntityService<OrderEvalTag> {

    /**
     * 获取标签列表
     * @return
     */
    OrderEvalTagListResult getEvalTagList();

}
