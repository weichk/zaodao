/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-05-18
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.SecretAnswerDao;
import com.acooly.zaodao.platform.entity.SecretAnswer;
import com.acooly.zaodao.platform.service.manage.SecretAnswerService;
import org.springframework.stereotype.Service;

/**
 * 密保答案表 Service实现
 *
 * Date: 2017-05-18 16:21:58
 *
 * @author zhike
 *
 */
@Service("secretAnswerService")
public class SecretAnswerServiceImpl extends EntityServiceImpl<SecretAnswer, SecretAnswerDao> implements SecretAnswerService {

    @Override
    public SecretAnswer getEntityBySerretIdAndCusId(Long secretId, String userId) {
        return this.getEntityDao().getEntityBySerretIdAndCusId(secretId,userId);
    }

    @Override
    public void deleteByUserId(String userId) {
        getEntityDao().deleteByUserId(userId);
    }
}
