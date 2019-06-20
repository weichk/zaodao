package com.acooly.zaodao.account.service;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.account.entity.AccountChange;

import java.util.List;


/**
 * 账户变动表 Service接口
 */
public interface AccountChangeService extends EntityService<AccountChange> {

	List<AccountChange> findByOrderNo(String orderNo);
}
