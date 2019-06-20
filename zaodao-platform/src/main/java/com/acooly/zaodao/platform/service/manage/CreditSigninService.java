/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-06-15
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.entity.CreditSignin;
import com.acooly.zaodao.platform.entity.Customer;

/**
 * 积分签到记录 Service接口
 *
 * Date: 2017-06-15 15:23:25
 * @author zhike
 *
 */
public interface CreditSigninService extends EntityService<CreditSignin> {

    /**
     * 签到
     * @param customer
     */
    CreditSignin CerditSignin(Customer customer);

    /**
     * 判断是否签到（true:已签到，false:未签到）
     * @param customer
     * @return
     */
    CreditSignin isCerditSignin(Customer customer);
}
