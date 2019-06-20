/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-05-18
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.SecretDao;
import com.acooly.zaodao.platform.entity.Secret;
import com.acooly.zaodao.platform.service.manage.SecretService;
import org.springframework.stereotype.Service;

/**
 * 密保问题表 Service实现
 *
 * Date: 2017-05-18 16:21:58
 *
 * @author zhike
 *
 */
@Service("secretService")
public class SecretServiceImpl extends EntityServiceImpl<Secret, SecretDao> implements SecretService {

}
