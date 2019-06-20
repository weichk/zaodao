package com.acooly.zaodao.account.info;

import com.acooly.core.common.exception.OrderCheckException;
import com.acooly.core.common.facade.InfoBase;
import com.acooly.core.utils.Money;
import com.acooly.core.utils.validate.jsr303.MoneyConstraint;
import com.acooly.zaodao.account.util.AccountConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 冻结/解冻信息
 *
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FreezeInfo extends InfoBase{
	
	private static final long serialVersionUID = -889222821723795599L;
	
	/**
	 * 业务订单号
	 */
	@NotEmpty
	@Size(max = 32)
	private String bizOrderNo;
	
	/**
	 * 用户ID
	 * <li> userId 与 accountNo 两者必填其一
	 */
	@Size(max = 32)
	private String userId;
	
	/**
	 * 账户类型
	 * <li>在填写userId时有效切必填
	 * <li>不填默认 MAIN - 主账户
	 */
	private String accountType = AccountConstants.ACCOUNT_TYPE_DEFAULT;
	
	/**
	 * 账户号
	 * <li>userId 与 accountNo 两者必填其一
	 */
	@Size(max = 32)
	private String accountNo;
	
	/**
	 * 冻结码
	 * <li>SPECIFY - 特殊冻结 ：此冻结解冻时需传入冻结订单号
	 * <li>NORMAL - 通用冻结 ：通用冻结及其他自定义冻结采用冻结池模式,解冻只要冻结池金额够就行,无需关联原冻结订单
	 * <li>不填默认 NORMAL - 通用冻结
	 */
	@NotEmpty
	private String freezeType = AccountConstants.ACCOUNT_FREEZE_TYPE_DEFAULT;
	
	/**
	 * 冻结金额
	 */
	@NotNull
	@MoneyConstraint(min = 1)
	private Money freezeAmount;

	/**
	 * 相关业务流水号
	 */
	@Size(max = 64)
	private String businessId;
	
	/**
	 * 上层交易类型/交易码
	 */
	@Size(max = 32)
	private String tradeType;
	
	/**
	 * 备注
	 */
	@Size(max = 128)
	private String comments;
	
	@Override
	public void check() throws OrderCheckException {
		super.check();
		if (StringUtils.isNotBlank(userId) && StringUtils.isBlank(accountType)){
			throw new OrderCheckException("accountType","填写userId时,accountType必填");
		}
		if (StringUtils.isBlank(userId) && StringUtils.isBlank(accountNo)){
			throw new OrderCheckException("userId,accountNo","userId,accountNo两者必填其一");
		}
	}
}

    