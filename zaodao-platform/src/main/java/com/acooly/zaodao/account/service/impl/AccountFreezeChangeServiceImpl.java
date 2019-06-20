package com.acooly.zaodao.account.service.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.zaodao.account.dao.AccountFreezeChangeDao;
import com.acooly.zaodao.account.entity.AccountFreezeChange;
import com.acooly.zaodao.account.service.AccountFreezeChangeService;
import org.springframework.stereotype.Service;

/**
 * 冻结账户变动表 Service实现
 *
 * Date: 2017-05-12 10:48:13
 *
 * @author acooly
 *
 */
@Service("accountFreezeChangeService")
public class AccountFreezeChangeServiceImpl extends EntityServiceImpl<AccountFreezeChange, AccountFreezeChangeDao> implements AccountFreezeChangeService {

}
