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
 * 冻结账户表 Entity
 */
@Entity
@Table(name = "act_account_freeze")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountFreeze extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 接入商ID */
	@NotEmpty
	@Size(max=32)
    private String partnerId;

	/** 用户ID */
	@NotEmpty
	@Size(max=32)
    private String userId;

	/** 用户名/外部用户id */
	@NotEmpty
	@Size(max=32)
    private String userName;
	
	/** 账户类型 */
	@NotEmpty
	@Size(max=32)
    private String accountType;

	/** 账户号 */
	@NotEmpty
	@Size(max=64)
    private String accountNo;

	/** 冻结类型 */
	@NotEmpty
    private String freezeType = "NORMAL";

	/** 冻结金额余额 */
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

	/** 最后变动记录id */
	private Long lastFreezeChangeId;
}
