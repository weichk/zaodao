package com.acooly.zaodao.account.info;

import com.acooly.core.common.exception.OrderCheckException;
import com.acooly.core.common.facade.InfoBase;
import com.acooly.zaodao.account.enums.AccountMixTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 混合支付
 *
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MixtureInfo extends InfoBase{
	
	private static final long serialVersionUID = 638718913946059008L;

	/**
	 * 混合交易类型
	 */
	@NotNull
	private AccountMixTypeEnum mixType;
	
	/**
	 * 冻结信息
	 * <li>mixType为FREEZE时不能为空并且有效
	 */
	private FreezeInfo freezeInfo;
	
	/**
	 * 转账信息
	 * <li>mixType为TRANSFER时不能为空并且有效
	 */
	private TransferInfo transferInfo;
	
	/**
	 * 解冻信息
	 * <li>mixType为UNFREEZE时不能为空并且有效
	 */
	private UnFreezeInfo unFreezeInfo;
	
	/**
	 * 提现信息
	 * <li>mixType为WITHDRAW时不能为空并且有效
	 */
	private WithdrawInfo withdrawInfo;
	
	/**
	 * 充值信息
	 * <li>mixType为DEPOSIT时不能为空并且有效
	 */
	private DepositInfo depositInfo;
	
	public void check(){
		super.check();
		if (AccountMixTypeEnum.FREEZE.equals(mixType) && freezeInfo == null){
			throw new OrderCheckException("freezeInfo", "冻结类型时冻结信息不能为空");	
		}else if (AccountMixTypeEnum.UNFREEZE.equals(mixType) && unFreezeInfo == null){
			throw new OrderCheckException("unFreezeInfo", "解冻类型时解冻信息不能为空");	
		}else if (AccountMixTypeEnum.TRANSFER.equals(mixType) && transferInfo == null){
			throw new OrderCheckException("transferInfo", "转账类型时转账信息不能为空");	
		}else if (AccountMixTypeEnum.DEPOSIT.equals(mixType) && depositInfo == null){
			throw new OrderCheckException("depositInfo", "充值类型时充值信息不能为空");	
		}else if (AccountMixTypeEnum.WITHDRAW.equals(mixType) && withdrawInfo == null){
			throw new OrderCheckException("withdrawInfo", "提现类型时提现信息不能为空");	
		}
	}
}

    