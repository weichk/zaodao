package com.acooly.zaodao.account.service.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.OrderCheckException;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.validate.Validators;
import com.acooly.zaodao.account.dao.AccountChangeDao;
import com.acooly.zaodao.account.dao.AccountDao;
import com.acooly.zaodao.account.dao.AccountFreezeChangeDao;
import com.acooly.zaodao.account.dao.AccountFreezeDao;
import com.acooly.zaodao.account.entity.Account;
import com.acooly.zaodao.account.entity.AccountChange;
import com.acooly.zaodao.account.entity.AccountFreeze;
import com.acooly.zaodao.account.entity.AccountFreezeChange;
import com.acooly.zaodao.account.enums.*;
import com.acooly.zaodao.account.info.*;
import com.acooly.zaodao.account.service.AccountService;
import com.acooly.zaodao.account.util.AccountConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 账户表 Service实现
 *
 * Date: 2016-10-16 15:28:30
 *
 * @author acooly
 *
 */
@Service("accountService")
public class AccountServiceImpl extends EntityServiceImpl<Account, AccountDao> implements AccountService {
	protected static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	@Autowired
	private AccountFreezeDao accountFreezeDao;
	@Autowired
	private AccountChangeDao accountChangeDao;
	@Autowired
	private AccountFreezeChangeDao accountFreezeChangeDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Account createAccount(String partnerId, String userId, String userName, String type, String parentUserId, String comments) {
		String serviceName = "[开户]";
		/** 参数验证及默认值赋值 */
		Validators.assertEmpty("partnerId", partnerId);
		Validators.assertEmpty("userId", userId);
		Validators.assertEmpty("userName", userName);
		type = Strings.isBlankDefault(type, AccountConstants.ACCOUNT_TYPE_DEFAULT);
		parentUserId = Strings.isBlankDefault(parentUserId, partnerId);

		try {
			//如果账户以存在,直接返回账户信息
			Account account = getEntityDao().findByUserIdAndAccountTypeAndLock(userId, type);
			if (account != null) {
				if (!account.getPartnerId().equals(partnerId)) {
					logger.warn(serviceName + "失败,用户不属于指定接入商,account.partnerId:{},in.partnerId:{}", account.getParentUserId(), partnerId);
					throw new BusinessException("用户不属于指定接入商");
				}
				if (!account.getUserName().equals(userName)) {
					logger.warn(serviceName + "失败,账户以存在,并且用户名与原有不符,account.userName:{},in.userName:{}", account.getUserName(), userName);
					throw new BusinessException("账户以存在,并且用户名与原有不符");
				}
				return account;
			}
			//通用账户直接使用userId作为accountNo,其他类型的账户生成新的accountNo
			String accountNo = AccountConstants.ACCOUNT_TYPE_DEFAULT.equals(type) ? userId : Ids.getDid();
			//查询索引
			String searchPath = null;
			if (userId.equals(parentUserId)) {
				searchPath = parentUserId + ",";
			} else {
				List<Account> accountList = getEntityDao().findByUserIdAndStatusNotEqStatus(parentUserId, AccountStatusEnum.DISABLE.getCode());
				if (accountList == null || accountList.size() <= 0) {
					logger.info("接入商({})不存在", parentUserId);
					throw new BusinessException("接入商不存在");
				}
				Account parentAccount = accountList.get(0);
				searchPath = parentAccount.getSearchPath() + userId + ",";
			}
			//保存账户信息
			account = new Account(partnerId, userId, userName, type, accountNo, 0l, 0l, AccountStatusEnum.ENABLE, null, comments, null, null, parentUserId, searchPath);
			getEntityDao().create(account);
			return account;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error(serviceName + "失败", e);
			throw new BusinessException(serviceName + "失败");
		}
	}

	@Override
	@Transactional
	public void freeze(FreezeInfo freezeInfo) {
		String serviceName = "[冻结]";
		/** 参数验证 */
		Validators.assertJSR303(freezeInfo);
		freezeInfo.check();
		try {
			Account account = getAccount(freezeInfo.getUserId(), freezeInfo.getAccountType(), freezeInfo.getAccountNo(), serviceName);
			//冻结
			changeAccountFreeze(null, account, freezeInfo.getFreezeType(), AccountChangeDirectionEnum.IN,
					freezeInfo.getFreezeAmount(), freezeInfo.getBizOrderNo(), freezeInfo.getComments(), 
					freezeInfo.getTradeType(), freezeInfo.getBusinessId(), null, serviceName);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error(serviceName+"失败",e);
			throw new BusinessException(serviceName+"失败");
		}
	}

	@Override
	@Transactional
	public void batchFreeze(List<FreezeInfo> freezeInfos) {
		String serviceName = "[冻结]";
		/** 参数验证 */
		if (freezeInfos == null){
			throw new OrderCheckException("freezeInfos", "冻结列表不能为空");
		}
		try {
			for (FreezeInfo freezeInfo : freezeInfos){
				freeze(freezeInfo);
			}
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error(serviceName+"失败",e);
			throw new BusinessException(serviceName+"失败");
		}
	}

	@Override
	@Transactional
	public void unFreeze(UnFreezeInfo unFreezeInfo) {
		String serviceName = "[解冻]";
		/** 参数验证 */
		Validators.assertJSR303(unFreezeInfo);
		unFreezeInfo.check();
		try {
			Account account = getAccount(unFreezeInfo.getUserId(), unFreezeInfo.getAccountType(), unFreezeInfo.getAccountNo(), serviceName);
			//解冻
			changeAccountFreeze(null, account, unFreezeInfo.getFreezeType(), AccountChangeDirectionEnum.OUT, 
					unFreezeInfo.getUnFreezeAmount(), unFreezeInfo.getBizOrderNo(), unFreezeInfo.getComments(), 
					unFreezeInfo.getTradeType(), unFreezeInfo.getBusinessId(), null, serviceName);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error(serviceName+"失败",e);
			throw new BusinessException(serviceName+"失败");
		}
		
	}

	@Override
	@Transactional
	public void batchUnFreeze(List<UnFreezeInfo> unFreezeInfos) {
		String serviceName = "[解冻]";
		/** 参数验证 */
		if (unFreezeInfos == null){
			throw new OrderCheckException("unFreezeInfos", "解冻列表不能为空");
		}
		try {
			for (UnFreezeInfo unFreezeInfo : unFreezeInfos){
				unFreeze(unFreezeInfo);
			}
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error(serviceName+"失败",e);
			throw new BusinessException(serviceName+"失败");
		}
	}

	@Override
	@Transactional
	public Account deposit(DepositInfo depositInfo) {
		String serviceName = "[上账]";
		/** 参数验证 */
		Validators.assertJSR303(depositInfo);
		depositInfo.check();
		if (Strings.isNotBlank(depositInfo.getFreezeType()) && depositInfo.getFreezeAmount() == null){			
			depositInfo.setFreezeAmount(depositInfo.getAmount());
		}
		try {
			//更新用户账户
			Account account = getAccount(depositInfo.getUserId(), depositInfo.getAccountType(), depositInfo.getAccountNo(), serviceName);
			account.setBalance(account.getBalance() + depositInfo.getAmount().getCent());
			//保存账户变动记录
			AccountChange change = new AccountChange(depositInfo.getBizOrderNo(), account.getPartnerId(), depositInfo.getTradeType(), account.getUserId(),
					account.getUserName(), account.getAccountType(), account.getAccountNo(), AccountChangeTypeEnum.UP,
					depositInfo.getAmount().getCent(), account.getBalance(), 0l, account.getFreeze(), AccountOrderStatusEnum.SUCCESS,
					null, null, null, null, depositInfo.getComments(), null, depositInfo.getBusinessId(), account.getLastChangeId(), depositInfo.getTradeTime());
			accountChangeDao.create(change);
			
			account.setLastChangeId(change.getId()); 
			getEntityDao().update(account);
			
			if (Strings.isNotBlank(depositInfo.getFreezeType())){
				changeAccountFreeze(change, account, depositInfo.getFreezeType(), AccountChangeDirectionEnum.IN, 
						depositInfo.getFreezeAmount(), depositInfo.getBizOrderNo(), depositInfo.getComments(), 
						depositInfo.getTradeType(), depositInfo.getBusinessId(), null, serviceName);
			}
			
			//日志
			logger.info("deposit amount:{},accountNo:{},account.balance:{}",
					depositInfo.getAmount().toString(), account.getAccountNo(), Money.cent(account.getBalance()).toString());
			return account;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error(serviceName+"失败",e);
			throw new BusinessException(serviceName+"失败");
		}
	}

	@Override
	@Transactional
	public Account withdraw(WithdrawInfo withdrawInfo) {
		String serviceName = "[下账]";
		/** 参数验证 */
		Validators.assertJSR303(withdrawInfo);
		withdrawInfo.check();
		if (Strings.isNotBlank(withdrawInfo.getFreezeType()) && withdrawInfo.getFreezeAmount() == null){			
			withdrawInfo.setFreezeAmount(withdrawInfo.getAmount());
		}
		try {
			//更新用户账户
			Account account = getAccount(withdrawInfo.getUserId(), withdrawInfo.getTradeType(), withdrawInfo.getAccountNo(), serviceName);
			
			AccountChange change = null;
			
			//如需要解冻，先行解冻
			if (Strings.isNotBlank(withdrawInfo.getFreezeType())){
				change = changeAccountFreeze(null, account, withdrawInfo.getFreezeType(), AccountChangeDirectionEnum.OUT, 
						withdrawInfo.getFreezeAmount(), withdrawInfo.getBizOrderNo(), withdrawInfo.getComments(), 
						withdrawInfo.getTradeType(), withdrawInfo.getBusinessId(), withdrawInfo.getFreezeOrigOrdeNo(), serviceName);
			}
			
			//check余额
			checkBalance(account, withdrawInfo.getAmount().getCent(), serviceName);
			
			account.setBalance(account.getBalance() - withdrawInfo.getAmount().getCent());
			//保存账户变动记录
			if (change == null){
				change = new AccountChange(withdrawInfo.getBizOrderNo(), account.getPartnerId(), withdrawInfo.getTradeType(), account.getUserId(),
						account.getUserName(), account.getAccountType(), account.getAccountNo(), AccountChangeTypeEnum.DOWN,
						-withdrawInfo.getAmount().getCent(), account.getBalance(), 0l, account.getFreeze(), AccountOrderStatusEnum.SUCCESS,
						null, null, null, null, withdrawInfo.getComments(), null, withdrawInfo.getBusinessId(), account.getLastChangeId(), withdrawInfo.getTradeTime());
				accountChangeDao.create(change);
			}else{
				change.setChangeType(AccountChangeTypeEnum.DOWN);
				change.setBalance(account.getBalance());
				change.setAmount(-withdrawInfo.getAmount().getCent());
				change.setTransDate(withdrawInfo.getTradeTime());
				accountChangeDao.update(change);
			}
			
			
			account.setLastChangeId(change.getId()); 
			getEntityDao().update(account);
			
			//日志
			logger.info("withdraw amount:{},accountNo:{},account.balance:{}",
					withdrawInfo.getAmount().toString(), account.getAccountNo(), Money.cent(account.getBalance()).toString());
			return account;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error(serviceName+"失败",e);
			throw new BusinessException(serviceName+"失败");
		}
	}

	@Override
	@Transactional
	public void transfer(TransferInfo transferInfo) {
		String serviceName = "[转账]";
		/** 参数验证 */
		Validators.assertJSR303(transferInfo);
		transferInfo.check();
		if (Strings.isNotBlank(transferInfo.getPayerFreezeType()) && transferInfo.getPayerFreezeAmount() == null){			
			transferInfo.setPayerFreezeAmount(transferInfo.getAmount());
		}
		if (Strings.isNotBlank(transferInfo.getPayeeFreezeType()) && transferInfo.getPayeeFreezeAmount() == null){			
			transferInfo.setPayeeFreezeAmount(transferInfo.getAmount());
		}
		try {
			Account payerAccount = null;
			Account payeeAccount = null;
			// 固定排序，防止死锁，及解决自己转自己问题
			if (Strings.isBlank(transferInfo.getPayerAccountNo())){
				if (transferInfo.getPayerUserId().compareTo(transferInfo.getPayeeUserId()) > 0){
					payerAccount = getAccount(transferInfo.getPayerUserId(), transferInfo.getPayerAccountType(), transferInfo.getPayerAccountNo(), serviceName);
					payeeAccount = getAccount(transferInfo.getPayeeUserId(), transferInfo.getPayeeAccountType(), transferInfo.getPayeeAccountNo(), serviceName);
				}else if (transferInfo.getPayerUserId().compareTo(transferInfo.getPayeeUserId()) < 0){
					payeeAccount = getAccount(transferInfo.getPayeeUserId(), transferInfo.getPayeeAccountType(), transferInfo.getPayeeAccountNo(), serviceName);
					payerAccount = getAccount(transferInfo.getPayerUserId(), transferInfo.getPayerAccountType(), transferInfo.getPayerAccountNo(), serviceName);
				}else{
					if (transferInfo.getPayerAccountType().compareTo(transferInfo.getPayeeAccountType()) > 0){
						payerAccount = getAccount(transferInfo.getPayerUserId(), transferInfo.getPayerAccountType(), transferInfo.getPayerAccountNo(), serviceName);
						payeeAccount = getAccount(transferInfo.getPayeeUserId(), transferInfo.getPayeeAccountType(), transferInfo.getPayeeAccountNo(), serviceName);
					}else if (transferInfo.getPayerAccountType().compareTo(transferInfo.getPayeeAccountType()) < 0){
						payeeAccount = getAccount(transferInfo.getPayeeUserId(), transferInfo.getPayeeAccountType(), transferInfo.getPayeeAccountNo(), serviceName);
						payerAccount = getAccount(transferInfo.getPayerUserId(), transferInfo.getPayerAccountType(), transferInfo.getPayerAccountNo(), serviceName);
					}else{
						payerAccount = getAccount(transferInfo.getPayerUserId(), transferInfo.getPayerAccountType(), transferInfo.getPayerAccountNo(), serviceName);
						payeeAccount = payerAccount;
					}
				}
			}else{
				if (transferInfo.getPayerAccountNo().compareTo(transferInfo.getPayeeAccountNo()) > 0){
					payerAccount = getAccount(transferInfo.getPayerUserId(), transferInfo.getPayerAccountType(), transferInfo.getPayerAccountNo(), serviceName);
					payeeAccount = getAccount(transferInfo.getPayeeUserId(), transferInfo.getPayeeAccountType(), transferInfo.getPayeeAccountNo(), serviceName);
				}else if (transferInfo.getPayerAccountNo().compareTo(transferInfo.getPayeeAccountNo()) < 0){
					payeeAccount = getAccount(transferInfo.getPayeeUserId(), transferInfo.getPayeeAccountType(), transferInfo.getPayeeAccountNo(), serviceName);
					payerAccount = getAccount(transferInfo.getPayerUserId(), transferInfo.getPayerAccountType(), transferInfo.getPayerAccountNo(), serviceName);
				}else{
					payerAccount = getAccount(transferInfo.getPayeeUserId(), transferInfo.getPayeeAccountType(), transferInfo.getPayeeAccountNo(), serviceName);
					payeeAccount = payerAccount;
				}
			}
			
			/** 转出方操作 */
			AccountChange payerChange = null;
			//如需要解冻，先行解冻
			if (Strings.isNotBlank(transferInfo.getPayerFreezeType())){
				payerChange = changeAccountFreeze(null, payerAccount, transferInfo.getPayerFreezeType(), AccountChangeDirectionEnum.OUT, 
						transferInfo.getPayerFreezeAmount(), transferInfo.getBizOrderNo(), transferInfo.getComments(), 
						transferInfo.getTradeTypeOut(), transferInfo.getBusinessId(), transferInfo.getFreezeOrigOrdeNo(), serviceName);
			}
			//check余额
			checkBalance(payerAccount, transferInfo.getAmount().getCent(), serviceName);
			payerAccount.setBalance(payerAccount.getBalance() - transferInfo.getAmount().getCent());
			//保存账户变动记录
			if (payerChange == null){
				payerChange = new AccountChange(transferInfo.getBizOrderNo(), payerAccount.getPartnerId(), transferInfo.getTradeTypeOut(), payerAccount.getUserId(),
						payerAccount.getUserName(), payerAccount.getAccountType(), payerAccount.getAccountNo(), AccountChangeTypeEnum.TRANSFER,
						-transferInfo.getAmount().getCent(), payerAccount.getBalance(), 0l, payerAccount.getFreeze(), AccountOrderStatusEnum.SUCCESS,
						payeeAccount.getUserId(), payeeAccount.getUserName(), payeeAccount.getAccountNo(), null, transferInfo.getComments(), transferInfo.getTransferType(), 
						transferInfo.getBusinessId(), payerAccount.getLastChangeId(), transferInfo.getTradeTime());
				accountChangeDao.create(payerChange);
			}else{
				payerChange.setChangeType(AccountChangeTypeEnum.TRANSFER);
				payerChange.setBalance(payerAccount.getBalance());
				payerChange.setAmount(-transferInfo.getAmount().getCent());
				payerChange.setRefUserId(payeeAccount.getUserId());
				payerChange.setRefUserName(payeeAccount.getUserName());
				payerChange.setRefAccountNo(payeeAccount.getAccountNo());
				payerChange.setTransDate(transferInfo.getTradeTime());
				accountChangeDao.update(payerChange);
			}
			
			
			payerAccount.setLastChangeId(payerChange.getId()); 
			getEntityDao().update(payerAccount);
			
			/** 转入方操作 */
			//更新转入用户账户
			payeeAccount.setBalance(payeeAccount.getBalance() + transferInfo.getAmount().getCent());
			//保存账户变动记录
			AccountChange payeechange = new AccountChange(transferInfo.getBizOrderNo(), payeeAccount.getPartnerId(), transferInfo.getTradeTypeIn(), payeeAccount.getUserId(),
					payeeAccount.getUserName(), payeeAccount.getAccountType(), payeeAccount.getAccountNo(), AccountChangeTypeEnum.TRANSFER,
					transferInfo.getAmount().getCent(), payeeAccount.getBalance(), 0l, payeeAccount.getFreeze(), AccountOrderStatusEnum.SUCCESS,
					payerAccount.getUserId(), payerAccount.getUserName(), payerAccount.getAccountNo(), null, transferInfo.getComments(), 
					transferInfo.getTransferType(), transferInfo.getBusinessId(), payeeAccount.getLastChangeId(), transferInfo.getTradeTime());
			accountChangeDao.create(payeechange);
			
			payeeAccount.setLastChangeId(payeechange.getId()); 
			getEntityDao().update(payeeAccount);
			
			if (Strings.isNotBlank(transferInfo.getPayeeFreezeType())){
				changeAccountFreeze(payeechange, payeeAccount, transferInfo.getPayeeFreezeType(), AccountChangeDirectionEnum.IN, 
						transferInfo.getPayeeFreezeAmount(), transferInfo.getBizOrderNo(), transferInfo.getComments(), 
						transferInfo.getTradeTypeIn(), transferInfo.getBusinessId(), null, serviceName);
			}
			
			//日志
			logger.info(
				"transfer amount:{},payerAccountNo:{},payerAccount.balance:{},payeeAccountNo:{},payeeAccount.balance:{}",
				transferInfo.getAmount(), payerAccount.getAccountNo(), Money.cent(payerAccount.getBalance()).toString(), 
				payeeAccount.getAccountNo(), Money.cent(payeeAccount.getBalance()).toString());
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error(serviceName+"失败",e);
			throw new BusinessException(serviceName+"失败");
		}
	}
	
	@Override
	@Transactional
	public void batchTransfer(List<TransferInfo> transferInfos) {
		if (transferInfos != null){
			for (TransferInfo transferInfo : transferInfos){
				transfer(transferInfo);	
			}
		}
	}

	@Override
	@Transactional
	public Account findByAccountNoAndLock(String accountNo) {
		return getEntityDao().findByAccountNoAndLock(accountNo);
	}

	@Override
	@Transactional
	public Account findByAccountNo(String accountNo) {
		return getEntityDao().findByAccountNo(accountNo);
	}
	
	/**
	 * 账户金额冻结解冻操作
	 * @param accountChange 主账户变动记录
	 * @param account 账户
	 * @param freezeType 冻结/解冻类型
	 * @param changeDirection 方向
	 * @param changeAmount 变动金额
	 * @param orderNo 订单号
	 * @param comments 备注
	 * @param tradeType 交易类型
	 * @param businessId 业务id
	 * @param orgOrderNo 特殊解冻传原订单号
	 * @param serviceName 业务名称
	 */
	private AccountChange changeAccountFreeze(AccountChange accountChange, Account account, String freezeType,
										AccountChangeDirectionEnum changeDirection, Money changeAmount, 
										String orderNo, String comments, String tradeType, String businessId,
										String orgOrderNo, String serviceName) {
		AccountFreeze accountFreeze = null;
		if (AccountChangeDirectionEnum.IN.equals(changeDirection)) {
			//检查可用余额
			checkBalance(account, changeAmount.getCent(), serviceName);
			//账户冻结金额更新
			account.setFreeze(account.getFreeze() + changeAmount.getCent());
			
			if (accountChange == null){
				accountChange = new AccountChange(orderNo, account.getPartnerId(), tradeType, account.getUserId(),
						account.getUserName(), account.getAccountType(), account.getAccountNo(), AccountChangeTypeEnum.KEEP,
						0l, account.getBalance(), changeAmount.getCent(), account.getFreeze(), AccountOrderStatusEnum.SUCCESS,
						null, null, null, null, comments, null, businessId, account.getLastChangeId(), null);
				accountChangeDao.create(accountChange);
				account.setLastChangeId(accountChange.getId());
			}else{
				accountChange.setFreeze(changeAmount.getCent());
				accountChange.setFreezeBalance(account.getFreeze());
				accountChangeDao.update(accountChange);
			}
			getEntityDao().update(account);
			
			//冻结记录更新
			accountFreeze = accountFreezeDao.findByAccountNoAndFreezeType(account.getAccountNo(), freezeType);
			if (accountFreeze == null) {
				accountFreeze = new AccountFreeze(account.getPartnerId(), account.getUserId(), account.getUserName(),
						account.getAccountType(), account.getAccountNo(), freezeType, 0l,
						null, null, null);
			}
			accountFreeze.setFreezeBalance(accountFreeze.getFreezeBalance() + changeAmount.getCent());
			accountFreeze.setComments(comments);
			
			//冻结记录变更记录
			AccountFreezeChange accountFreezeChange = new AccountFreezeChange(orderNo, accountFreeze.getPartnerId(),
					tradeType, accountFreeze.getUserId(), accountFreeze.getUserName(), accountFreeze.getAccountNo(),
					accountFreeze.getAccountType(), accountFreeze.getFreezeType(), changeAmount.getCent(), accountFreeze.getFreezeBalance(),
					null, comments, businessId, 0l, accountFreeze.getLastFreezeChangeId());
			
			if (AccountConstants.ACCOUNT_FREEZE_TYPE_SPECIFY.equals(freezeType)){
				//指定冻结
				accountFreezeChange.setFreezeLeave(changeAmount.getCent());
			}
			accountFreezeChangeDao.create(accountFreezeChange);
			
			accountFreeze.setLastFreezeChangeId(accountFreezeChange.getId());
			if (accountFreeze.getId() == null){
				accountFreezeDao.create(accountFreeze);
			}else{
				accountFreezeDao.update(accountFreeze);
			}
			
			logger.info(serviceName+",冻结资金成功,accountNo:{},accountFreezeId:{},freezeAmount:{},freezeBalance:{}",
				account.getAccountNo(), accountFreeze.getId(), changeAmount.toString(),
				Money.cent(accountFreeze.getFreezeBalance()).toString());
		} else {
			AccountFreezeChange orgChange = null;
			if (AccountConstants.ACCOUNT_FREEZE_TYPE_SPECIFY.equals(freezeType)){
				List<AccountFreezeChange> orgChanges = accountFreezeChangeDao.findByOrgFreeze(orgOrderNo, account.getAccountNo(), freezeType);
				if (orgChanges == null || orgChanges.size() < 1){
					logger.warn(serviceName+",解冻资金失败,未找到原冻结商户订单号.orgOrderNo:{},accountNo:{},freezeType:{}",orgOrderNo, account.getAccountNo(), freezeType);
					throw new BusinessException("未找到原冻结商户订单号", AccountResultCodeEnum.ORDERNO_NOT_EXISTS.getCode());
				}
				orgChange = orgChanges.get(0);
				if (orgChange.getFreezeLeave() < changeAmount.getCent()){
					logger.warn(serviceName+",解冻资金失败,原冻结金额余额不足.orgChangeId:{},orgFreezeLeave:{},unFreezeAmount:{}",orgChange.getId(),orgChange.getFreezeLeave(),changeAmount.getCent());
					throw new BusinessException("原冻结金额余额不足",AccountResultCodeEnum.ACCOUNT_FREEZE_BALANCE_NOT_ENOUGH.getCode());
				}
				orgChange.setFreezeLeave(orgChange.getFreezeLeave() - changeAmount.getCent());
				accountFreezeChangeDao.update(orgChange);
			}
			
			if (account.getFreeze() < changeAmount.getCent()){
				logger.warn(AccountResultCodeEnum.ACCOUNT_FREEZE_BALANCE_NOT_ENOUGH.getMessage()
					+ ",accountNo:{},freezeBalance:{},unFreezeAmount:{}", account.getAccountNo(),
					account.getFreeze() == null ? 0 : Money.cent(account.getFreeze()).toString(),
						changeAmount.toString());
				throw new BusinessException(AccountResultCodeEnum.ACCOUNT_FREEZE_BALANCE_NOT_ENOUGH.getMessage(),
						AccountResultCodeEnum.ACCOUNT_FREEZE_BALANCE_NOT_ENOUGH.getCode());
			}
			//账户冻结金额更新
			account.setFreeze(account.getFreeze() - changeAmount.getCent());
			
			if (accountChange == null){
				accountChange = new AccountChange(orderNo, account.getPartnerId(), tradeType, account.getUserId(),
						account.getUserName(), account.getAccountType(), account.getAccountNo(), AccountChangeTypeEnum.KEEP,
						0l, account.getBalance(), -changeAmount.getCent(), account.getFreeze(), AccountOrderStatusEnum.SUCCESS,
						null, null, null, null, comments, null, businessId, account.getLastChangeId(), null);
				accountChangeDao.create(accountChange);
				account.setLastChangeId(accountChange.getId());
			}else{
				accountChange.setFreeze(changeAmount.getCent());
				accountChange.setFreezeBalance(account.getFreeze());
				accountChangeDao.update(accountChange);
			}
			getEntityDao().update(account);
			
			//冻结记录更新
			accountFreeze = checkFreezeAmount(account.getAccountNo(), freezeType, changeAmount, serviceName);
			accountFreeze.setFreezeBalance(accountFreeze.getFreezeBalance() - changeAmount.getCent());
			accountFreezeDao.update(accountFreeze);
			//冻结记录变更记录
			AccountFreezeChange accountFreezeChange = new AccountFreezeChange(orderNo, accountFreeze.getPartnerId(),
					tradeType, accountFreeze.getUserId(), accountFreeze.getUserName(), accountFreeze.getAccountNo(),
					accountFreeze.getAccountType(), accountFreeze.getFreezeType(), -changeAmount.getCent(), accountFreeze.getFreezeBalance(),
					null, comments, businessId, 0l, accountFreeze.getLastFreezeChangeId());
			
			if (orgChange != null){
				accountFreezeChange.setFreezeLeave(orgChange.getFreezeLeave());
			}
			accountFreezeChangeDao.create(accountFreezeChange);
			logger.info(serviceName+",解冻资金成功,accountNo:{},accountFreezeId:{},unFreezeAmount:{},freezeBalance:{}",
				account.getAccountNo(), accountFreeze.getId(), changeAmount.toString(),
				Money.cent(accountFreeze.getFreezeBalance()).toString());
		}
		return accountChange;
	}
	
	/**
	 * 获取冻结记录并检测冻结金额是否足够
	 * @param accountNo
	 * @param freezeType
	 * @param unFreezeAmount
	 * @return
	 */
	private AccountFreeze checkFreezeAmount(String accountNo, String freezeType, Money unFreezeAmount, String serviceName) {
		AccountFreeze accountFreeze = accountFreezeDao.findByAccountNoAndFreezeType(accountNo, freezeType);
		if (accountFreeze == null || accountFreeze.getFreezeBalance() - unFreezeAmount.getCent() < 0) {
			logger.warn(serviceName+","+AccountResultCodeEnum.ACCOUNT_FREEZE_BALANCE_NOT_ENOUGH.getMessage()
						+ ",accountNo:{},freezeType:{},freezeBalance:{},unFreezeAmount:{}", accountNo, freezeType,
				accountFreeze == null ? 0 : Money.cent(accountFreeze.getFreezeBalance()).toString(),
				unFreezeAmount.toString());
			throw new BusinessException(AccountResultCodeEnum.ACCOUNT_FREEZE_BALANCE_NOT_ENOUGH.getMessage(),
					AccountResultCodeEnum.ACCOUNT_FREEZE_BALANCE_NOT_ENOUGH.getCode());
		}
		return accountFreeze;
	}
	
	/**
	 * 获取账户信息
	 * <li>同时锁定账户
	 * <li>userId,type 和 accountNo 两组参数必须选填其一
	 * @param userId 用户ID
	 * @param type 账户类型
	 * @param accountNo 账户号
	 * @return
	 */
	private Account getAccount(String userId, String type, String accountNo, String serviceName){
		Account account = null;
		
		if (Strings.isEmpty(accountNo)){
			account = getEntityDao().findByUserIdAndAccountTypeAndLock(userId, type);
			if (account == null){
				logger.warn(serviceName+",交易账户不存在,userId:{},type:{}", userId, type);
				throw new BusinessException(AccountResultCodeEnum.ACCOUNT_NOT_EXIST.getMessage(), 
						AccountResultCodeEnum.ACCOUNT_NOT_EXIST.getCode());
			}
		}else{
			account = getEntityDao().findByAccountNoAndLock(accountNo);
			if (account == null){
				logger.warn(serviceName+",交易账户不存在,accountNo:{}", accountNo);
				throw new BusinessException(AccountResultCodeEnum.ACCOUNT_NOT_EXIST.getMessage(), 
						AccountResultCodeEnum.ACCOUNT_NOT_EXIST.getCode());
			}
			if (Strings.isNotEmpty(userId) && !account.getUserId().equals(userId)){
				logger.warn(serviceName+",交易账户不属于指定用户.userId:{},account.userId:{},accountNo:{}",userId,account.getUserId(),account.getAccountNo());
				throw new BusinessException("交易账户不属于指定用户", 
						AccountResultCodeEnum.ACCOUNT_INFO_ERROR.getCode());
			}
		}
		return account;
	}
	
	private void checkBalance(Account account, long amount, String serviceName) {
		if (account.getBalance() - account.getFreeze() < amount) {
			if (account.getBalance() - account.getFreeze() < amount) {
				logger.warn(serviceName+","+AccountResultCodeEnum.ACCOUNT_BALANCE_NOT_ENOUGH.getMessage()
							+ "userId:{},userName:{},accountNo:{},balance:{},freeze:{},freezeAmount:{}", account.getUserId(), account.getUserName(), account.getAccountNo(),
					Money.cent(account.getBalance()).toString(), Money.cent(account.getFreeze()).toString(), Money
						.cent(amount).toString());
				throw new BusinessException(AccountResultCodeEnum.ACCOUNT_BALANCE_NOT_ENOUGH.getMessage(),
						AccountResultCodeEnum.ACCOUNT_BALANCE_NOT_ENOUGH.getCode());
			}
		}
	}

	@Override
	@Transactional
	public Account findByUserId(String userId) {
		return getEntityDao().findByUserId(userId);
	}

}
