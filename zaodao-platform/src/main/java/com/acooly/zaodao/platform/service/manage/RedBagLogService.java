/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-07-18
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.RedBagLog;

/**
 * 红包领取记录表 Service接口
 *
 * Date: 2017-07-18 09:42:32
 * @author zhike
 *
 */
public interface RedBagLogService extends EntityService<RedBagLog> {

    RedBagLog getEntityByUserIdAndRefId(String userId, Long refId);
}
