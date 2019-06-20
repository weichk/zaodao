/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-07-17
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.RedBag;

/**
 * 红包表 Service接口
 * <p>
 * Date: 2017-07-17 22:06:47
 *
 * @author zhike
 *
 */
public interface RedBagService extends EntityService<RedBag> {

    RedBag getEntityByUserIdAndRefId(String userId, Long refId);
}
