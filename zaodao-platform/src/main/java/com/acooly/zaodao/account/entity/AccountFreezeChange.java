package com.acooly.zaodao.account.entity;


import com.acooly.core.common.domain.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 冻结账户变动表 Entity
 */
@Entity
@Table(name = "act_account_freeze_change")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountFreezeChange extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 支付订单号 */
	@NotEmpty
	@Size(max=32)
    private String orderNo;

	/** 接入商ID */
	@NotEmpty
	@Size(max=32)
    private String partnerId;

	/** 交易类型 */
	@Size(max=32)
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
	@Size(max=32)
    private String accountNo;

	/** 冻结类型 */
	@NotEmpty
	@Size(max=32)
    private String freezeType;
	
	/** 交易金额 */
	@NotNull
    private Long freezeAmount = 0l;

	/** 交易后余额 */
	@NotNull
    private Long freezeBalance = 0l;

	/** 内部备注 */
	@NotEmpty
	@Size(max=128)
    private String memo;

	/** 外部备注 */
	@NotEmpty
	@Size(max=128)
    private String comments;

	/** 上传业务id */
	private String businessId;
	
	/** 剩余未解冻金额 */
	@NotNull
    private Long freezeLeave = 0l;
	
	/** 前置变动id */
	private Long previouFreezeChangeId;
}
