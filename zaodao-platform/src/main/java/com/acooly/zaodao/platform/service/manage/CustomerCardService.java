/*
 * zhike@yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by zhike
 * date:2017-05-24
 *
 */
package com.acooly.zaodao.platform.service.manage;

import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.gateway.gsy.message.SignCardResponse;
import com.acooly.zaodao.gateway.gsy.message.UnSignCardResponse;
import com.acooly.zaodao.platform.dto.CustomerCardDto;
import com.acooly.zaodao.platform.entity.Customer;
import com.acooly.zaodao.platform.entity.CustomerCard;
import com.acooly.zaodao.platform.order.BankCardListOrder;
import com.acooly.zaodao.platform.order.UnbindingCardOrder;

import java.util.List;

/**
 * 用户绑卡信息表 Service接口
 * <p>
 * Date: 2017-05-24 22:44:01
 *
 * @author zhike
 */
public interface CustomerCardService extends EntityService<CustomerCard> {

    /**
     * 通过userId查询绑卡信息
     *
     * @param userId
     * @return
     */
    CustomerCard getEntityByUserId(String userId);

    /**
     * 删除银行卡
     * @param userId
     */
    void deleteEntityByUserId(String userId);

    /**
     * 获取银行卡列表
     * @param bankCardListOrder
     * @return
     */
    PageResult<CustomerCardDto> getCustomerCardList(BankCardListOrder bankCardListOrder);

    /**
     * 解绑银行卡
     * @param unbindingCardOrder
     * @return
     */
    ResultBase unbindingCard(UnbindingCardOrder unbindingCardOrder);

    /**
     * 接观世宇解绑银行卡
     * @param customer
     */
    UnSignCardResponse unSignCardGsy(Customer customer);

    /**
     * 接观世宇绑定银行卡
     * @param customer
     * @param bankCardNo
     * @param realName
     * @param bankCode
     */
    SignCardResponse doSignCardGsy(Customer customer, String bankCardNo, String realName, String bankCode, String mobileNo, String idNumber);

    /**
     * 获取用户绑定卡
     * @param userId
     * @return
     */
    List<CustomerCard> getBindCardListByUserId(String userId);

    /**
     * 获取用户绑卡数量
     * @param userId
     * @return
     */
    int getBindCardCountByUserId(String userId);
}
