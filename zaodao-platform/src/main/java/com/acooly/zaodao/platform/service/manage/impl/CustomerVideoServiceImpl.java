/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-29
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.CustomerVideoDao;
import com.acooly.zaodao.platform.entity.CustomerVideo;
import com.acooly.zaodao.platform.service.manage.CustomerVideoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户视频表 Service实现
 *
 * Date: 2017-06-29 12:16:54
 *
 * @author zhike
 *
 */
@Service("customerVideoService")
public class CustomerVideoServiceImpl extends EntityServiceImpl<CustomerVideo, CustomerVideoDao> implements CustomerVideoService {


    @Override
    public List<CustomerVideo> getEntityByUserId(String userId) {
        return getEntityDao().getEntityByUserId(userId);
    }
}
