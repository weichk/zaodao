package com.acooly.zaodao.account.entity;

import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.zaodao.account.enums.AccountChangeTypeEnum;
import com.acooly.zaodao.account.enums.AccountOrderStatusEnum;
import com.acooly.zaodao.account.enums.AccountTransferTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 用户账户变动表 Entity
 */
@Entity
@Table(name = "act_account_change")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountChange extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 订单号 */
	@NotEmpty
	@Size(max=32)
    private String orderNo;

	/** 接入商ID */
	@NotEmpty
	@Size(max=32)
    private String partnerId;
	
	/** 上层交易类型 */
	private String tradeType;
	
	/** 用户ID */
	@NotEmpty
	@Size(max=32)
    private String userId;

	/** 用户名/外部用户id */
	@NotEmpty
	@Size(max=32)
    private String userName;
	
	/** 账户类型 */
    private String accountType;

	/** 账户号 */
	@NotEmpty
	@Size(max=64)
    private String accountNo;

	/** 变动类型 */
	@NotNull
	@Enumerated(EnumType.STRING)
    private AccountChangeTypeEnum changeType;

	/** 变动金额 */
	@NotNull
    private Long amount = 0l;

	/** 交易后余额 */
	@NotNull
    private Long balance = 0l;
	
	/** 冻结/解冻金额 */
	@NotNull
    private Long freeze = 0l;

	/** 交易后冻结余额 */
	@NotNull
    private Long freezeBalance = 0l;

	/** 状态 */
	@NotNull
	@Enumerated(EnumType.STRING)
    private AccountOrderStatusEnum status = AccountOrderStatusEnum.SUCCESS;

	/** 相关用户ID */
	@NotEmpty
	@Size(max=32)
    private String refUserId;
	
	/** 相关用户名/外部用户id */
	@NotEmpty
	@Size(max=32)
    private String refUserName;

	/** 相关账户号 */
	@NotEmpty
	@Size(max=32)
    private String refAccountNo;

	/** 内部备注 */
	@NotEmpty
	@Size(max=128)
    private String memo;

	/** 外部备注 */
	@NotEmpty
	@Size(max=128)
    private String comments;

	/** 操作类型 */
	@NotNull
	@Enumerated(EnumType.STRING)
	private AccountTransferTypeEnum transferType;
	
	/** 上传业务id */
	private String businessId;
	
	/** 前置变动id */
	private Long previouChangeId;
	
	/** 交易时间/网关时间 */
    private Date transDate = getUpdateTime();
    
}
