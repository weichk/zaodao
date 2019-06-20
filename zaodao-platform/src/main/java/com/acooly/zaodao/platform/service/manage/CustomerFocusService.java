/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-10-30
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.platform.dto.CustomerFocusDto;
import com.acooly.zaodao.platform.entity.CustomerFocus;
import com.acooly.zaodao.platform.order.CustomerFocusAddOrder;
import com.acooly.zaodao.platform.order.CustomerFocusCountOrder;
import com.acooly.zaodao.platform.order.CustomerFocusListOrder;
import com.acooly.zaodao.platform.order.CustomerSigninAddOrder;
import com.acooly.zaodao.platform.result.CustomerFocusCountResult;
import com.acooly.zaodao.platform.result.CustomerSigninAddResult;

/**
 * zd_customer_focus Service接口
 *
 * Date: 2017-10-30 15:56:37
 * @author zhike
 *
 */
public interface CustomerFocusService extends EntityService<CustomerFocus> {

    /**
     * 添加用户关注信息
     * @param customerFocusAddOrder
     * @return
     */
    ResultBase addCustomerFocus(CustomerFocusAddOrder customerFocusAddOrder);

    /**
     * 获取关注数量和粉丝数量
     * @param customerFocusCountOrder
     * @return
     */
    CustomerFocusCountResult getCustomerFocusCount(CustomerFocusCountOrder customerFocusCountOrder);

    /**
     * 用户粉丝、关注列表
     * @param customerFocusListOrder
     * @return
     */
    PageResult<CustomerFocusDto> getCustomerFocusList(CustomerFocusListOrder customerFocusListOrder);

    /**
     * 获取关注用户信息
     * @param customerFocusAddOrder
     * @return
     */
    CustomerFocus getCustomerFocus(CustomerFocusAddOrder customerFocusAddOrder);

    /**
     * 用戶签到
     * @param customerSigninAddOrder
     * @return
     */
    CustomerSigninAddResult addCustomerSignin(CustomerSigninAddOrder customerSigninAddOrder);

    /**
     * 获取用户和用户的信息
     * @param userId
     * @param focusUserId
     * @return
     */
    CustomerFocus getCustomerFocus(String userId, String focusUserId);
}
