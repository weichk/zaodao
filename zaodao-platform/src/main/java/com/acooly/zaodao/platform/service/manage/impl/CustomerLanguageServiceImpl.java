/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-01
 */
package com.acooly.zaodao.platform.service.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.platform.dao.manage.CustomerLanguageDao;
import com.acooly.zaodao.platform.entity.CustomerLanguage;
import com.acooly.zaodao.platform.service.manage.CustomerLanguageService;
import org.springframework.stereotype.Service;

/**
 * 导游语种表 Service实现
 *
 * Date: 2017-06-01 17:44:07
 *
 * @author zhike
 *
 */
@Service("customerLanguageService")
public class CustomerLanguageServiceImpl extends EntityServiceImpl<CustomerLanguage, CustomerLanguageDao> implements CustomerLanguageService {

}
