package com.acooly.zaodao.account.info;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import com.acooly.core.common.exception.OrderCheckException;
import com.acooly.core.common.facade.InfoBase;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.validate.jsr303.MoneyConstraint;
import com.acooly.zaodao.account.enums.AccountTransferTypeEnum;
import com.acooly.zaodao.account.util.AccountConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 转账信息
 *
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransferInfo extends InfoBase{
	
	private static final long serialVersionUID = 3518347060077204873L;
	

	/**
	 * 业务订单号
	 */
	@NotEmpty
	@Size(max = 32)
	private String bizOrderNo;
	
	/**
	 * 转账类型
	 */
	@NotNull
	private AccountTransferTypeEnum transferType;
	
	/**
	 * 付款方用户ID
	 * <li> payerUserId 与 payerAccountNo 两者必填其一
	 */
	@Size(max = 32)
	private String payerUserId;
	
	/**
	 * 付款方账户类型
	 * <li>在填写userId时有效切必填
	 * <li>不填默认 MAIN - 主账户
	 */
	private String payerAccountType = AccountConstants.ACCOUNT_TYPE_DEFAULT;
	
	/**
	 * 付款方账户号
	 * <li>payerUserId 与 payerAccountNo 两者必填其一
	 */
	@Size(max = 32)
	private String payerAccountNo;
	
	/**
	 * 收款方用户ID
	 * <li> payeeUserId 与 payeeAccountNo 两者必填其一
	 */
	@Size(max = 32)
	private String payeeUserId;
	
	/**
	 * 收款方账户类型
	 * <li>在填写userId时有效切必填
	 * <li>不填默认 MAIN - 主账户
	 */
	private String payeeAccountType = AccountConstants.ACCOUNT_TYPE_DEFAULT;
	
	/**
	 * 收款方账户号
	 * <li>payeeUserId 与 payeeAccountNo 两者必填其一
	 */
	@Size(max = 32)
	private String payeeAccountNo;
	
	/**
	 * 金额
	 */
	@NotNull
	@MoneyConstraint(min = 1)
	private Money amount;
	
	/**
	 * 付款方冻结码
	 * <li>如果本字段不为空,那么付款方转出前同步进行指定冻结类型的解冻金额(payerFreezeAmount金额)
	 * <li>SPECIFY - 特殊冻结 ：此冻结解冻时需传入冻结订单号
	 * <li>NORMAL - 通用冻结 ：通用冻结及其他自定义冻结采用冻结池模式,解冻只要冻结池金额够就行,无需关联原冻结订单
	 */
	private String payerFreezeType;
	
	/**
	 * 付款方解冻金额
	 * <li>本字段在payerFreezeType不为空是有效
	 * <li>本字段指需要同步解冻的金额,在payerFreezeType不为空的情况下,如本字段不填,则默认解冻本次转账金额.
	 */
	private Money payerFreezeAmount;
	
	/**
	 * 付款方商户原冻结流水号
	 * <li>本字段于payerFreezeType为特殊冻结时有效切必填.
	 * <li>本字段是指冻结是传入的merchantOrderNo字段.
	 */
	@Size(max = 32)
	private String freezeOrigOrdeNo;
	
	/**
	 * 收款方解冻类型
	 * <li>如果本字段不为空,那么收款方收款后同步进行指定冻结类型的冻结金额(payeeFreezeAmount金额)
	 * <li>SPECIFY - 特殊冻结 ：此冻结解冻时需传入冻结订单号
	 * <li>NORMAL - 通用冻结 ：通用冻结及其他自定义冻结采用冻结池模式,解冻只要冻结池金额够就行,无需关联原冻结订单
	 */
	private String payeeFreezeType;
	
	/**
	 * 收款方解冻金额
	 * <li>本字段在payeeFreezeType不为空是有效
	 * <li>本字段指需要同步冻结的金额,在payeeFreezeType不为空的情况下,如本字段不填,则默认冻结本次转账金额.
	 */
	private Money payeeFreezeAmount;
	
	/**
	 * 相关业务流水号
	 */
	@Size(max = 64)
	private String businessId;
	
	/**
	 * 上层交易类型/交易码(出金方)
	 */
	@Size(max = 32)
	private String tradeTypeOut;
	
	/**
	 * 上层交易类型/交易码(入金方)
	 */
	@Size(max = 32)
	private String tradeTypeIn;
	
	/**
	 * 备注
	 */
	@Size(max = 128)
	private String comments;
	
	/**
	 * 交易时间
	 * <li>不填默认createTime
	 */
	private Date tradeTime;

	public void check() {
		super.check();
		
		if (StringUtils.isNotBlank(payerUserId) && StringUtils.isBlank(payerAccountType)){
			throw new OrderCheckException("payerAccountType","填写payerUserId时,payerAccountType必填");
		}
		if (StringUtils.isBlank(payerUserId) && StringUtils.isBlank(payerAccountNo)){
			throw new OrderCheckException("payerUserId,payerAccountNo","payerUserId,payerAccountNo两者必填其一");
		}
		if (StringUtils.isNotBlank(payeeUserId) && StringUtils.isBlank(payeeAccountType)){
			throw new OrderCheckException("payeeAccountType","填写payeeUserId时,payeeAccountType必填");
		}
		if (StringUtils.isBlank(payeeUserId) && StringUtils.isBlank(payeeAccountNo)){
			throw new OrderCheckException("payeeUserId,payeeAccountNo","payeeUserId,payeeAccountNo两者必填其一");
		}
		if (StringUtils.isBlank(payerAccountNo) != StringUtils.isBlank(payeeAccountNo)){
			throw new OrderCheckException("payerAccountNo,payeeAccountNo","用户ID 或 账户ID,2种模式只能选择一种");
		}
		
		if (StringUtils.isNotBlank(payerFreezeType) && AccountConstants.ACCOUNT_FREEZE_TYPE_SPECIFY.equals(payerFreezeType) && StringUtils.isBlank(freezeOrigOrdeNo)){
			throw new OrderCheckException("freezeOrigOrdeNo", "特殊冻结类型必须填写商户原冻结流水号");	
		}
		
		if (StringUtils.isBlank(payeeFreezeType)){
			if (payeeFreezeAmount == null || payeeFreezeAmount.getCent() < 1){
				payeeFreezeAmount = amount;
			}
			if (payeeFreezeAmount.getCent() > amount.getCent()){
				throw new OrderCheckException("payeeFreezeAmount,amount", "冻结金额不能大于本次交易金额");
			}
		}
	}
}

    