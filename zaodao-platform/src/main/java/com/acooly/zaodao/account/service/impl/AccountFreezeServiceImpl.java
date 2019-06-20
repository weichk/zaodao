package com.acooly.zaodao.account.service.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.account.dao.AccountFreezeDao;
import com.acooly.zaodao.account.entity.AccountFreeze;
import com.acooly.zaodao.account.service.AccountFreezeService;
import org.springframework.stereotype.Service;

/**
 * 冻结账户表 Service实现
 *
 * Date: 2016-10-16 15:31:17
 *
 * @author acooly
 *
 */
@Service("accountFreezeService")
public class AccountFreezeServiceImpl extends EntityServiceImpl<AccountFreeze, AccountFreezeDao> implements AccountFreezeService {

}
