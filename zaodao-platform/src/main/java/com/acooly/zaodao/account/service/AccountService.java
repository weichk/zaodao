package com.acooly.zaodao.account.service;

import com.acooly.core.common.service.EntityService;
import com.acooly.zaodao.account.entity.Account;
import com.acooly.zaodao.account.info.*;

import java.util.List;

/**
 * 账户表 Service接口
 */
public interface AccountService extends EntityService<Account> {
	/**
	 * 开户
	 * @param partnerId 接入商ID[必填]
	 * @param userId 用户ID[必填]
	 * @param userName 用户名/外部订用户ID[必填]
	 * @param type 用户类型[选填,不填默认MAIN]
	 * @param parentUserId 上级用户ID[选填,不填默认partnerId]
	 * @param comments
	 * @return
	 */
	Account createAccount(String partnerId, String userId, String userName, String type, String parentUserId, String comments);
	
	/**
	 * 冻结金额
	 */
	void freeze(FreezeInfo freezeInfo);
	
	/**
	 * 批量冻结金额
	 */
	void batchFreeze(List<FreezeInfo> freezeInfos);
	
	/**
	 * 解冻金额
	 */
	void unFreeze(UnFreezeInfo unFreezeInfo);
	
	/**
	 * 解冻金额
	 */
	void batchUnFreeze(List<UnFreezeInfo> unFreezeInfos);
	
	/**
	 * 充值上账
	 */
	Account deposit(DepositInfo depositInfo);
	
	/**
	 * 提现下账(同时解冻相应金额)
	 */
	Account withdraw(WithdrawInfo withdrawInfo);
	
	/**
	 * 转账
	 */
	void transfer(TransferInfo transferInfo);
	
	/**
	 * 批量转账
	 * @param transferInfos
	 * @return
	 */
	void batchTransfer(List<TransferInfo> transferInfos);
	
	/**
	 * 获取指定账户号
	 * @param accountNo
	 * @return
	 */
	Account findByAccountNo(String accountNo);
	
	/**
	 * 获取指定账户号
	 * @param accountNo
	 * @return
	 */
	Account findByAccountNoAndLock(String accountNo);

	/**
	 * 获取客户账户
	 * @param userId
	 * @return
	 */
	Account findByUserId(String userId);
}
