/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-05-30
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.GuideCountInfo;

/**
 * 导游订单统计表 Service接口
 * <p>
 * Date: 2017-05-30 11:07:30
 *
 * @author zhike
 */
public interface GuideCountInfoService extends EntityService<GuideCountInfo> {

    GuideCountInfo findByUserId(String userId);
}
