/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-05-18
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.SecretAnswer;

/**
 * 密保答案表 Service接口
 *
 * Date: 2017-05-18 16:21:58
 * @author zhike
 *
 */
public interface SecretAnswerService extends EntityService<SecretAnswer> {

    /**
     * 根据用户ID和密保id查询密保答案
     * @param secretId
     * @param userId
     * @return
     */
    SecretAnswer getEntityBySerretIdAndCusId(Long secretId, String userId);

    /**
     * 根据用户ID删除用户密保答案信息
     * @param userId
     */
    void deleteByUserId(String userId);
}
