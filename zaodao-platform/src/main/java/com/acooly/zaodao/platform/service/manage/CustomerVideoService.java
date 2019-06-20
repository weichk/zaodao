/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-29
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.CustomerVideo;

import java.util.List;

/**
 * 用户视频表 Service接口
 *
 * Date: 2017-06-29 12:16:54
 * @author zhike
 *
 */
public interface CustomerVideoService extends EntityService<CustomerVideo> {

    List<CustomerVideo> getEntityByUserId(String userId);
}
