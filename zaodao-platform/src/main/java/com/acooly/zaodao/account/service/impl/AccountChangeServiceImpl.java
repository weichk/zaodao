package com.acooly.zaodao.account.service.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.account.dao.AccountChangeDao;
import com.acooly.zaodao.account.entity.AccountChange;
import com.acooly.zaodao.account.service.AccountChangeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户变动表 Service实现
 *
 * Date: 2016-10-16 15:30:54
 *
 * @author acooly
 *
 */
@Service("accountChangeService")
public class AccountChangeServiceImpl extends EntityServiceImpl<AccountChange, AccountChangeDao> implements AccountChangeService {

	@Override
	public List<AccountChange> findByOrderNo(String orderNo) {
		return getEntityDao().findByOrderNo(orderNo);
	}
}
