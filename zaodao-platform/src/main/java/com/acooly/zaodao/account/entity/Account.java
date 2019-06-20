package com.acooly.zaodao.account.entity;

import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.zaodao.account.enums.AccountStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 用户账户 Entity
 */
@Entity
@Table(name = "act_account")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends AbstractEntity {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 接入商ID */
	@NotEmpty
	@Size(max = 32)
	private String partnerId;

	/** 用户ID */
	@NotEmpty
	@Size(max = 32)
	private String userId;

	/** 用户名/外部用户id */
	@NotEmpty
	@Size(max = 32)
	private String userName;

	/** 账户类型 */
	@NotEmpty
	@Size(max = 32)
	private String accountType = "MAIN";

	/** 账户号 */
	@NotEmpty
	@Size(max = 64)
	private String accountNo;

	/** 账户余额 */
	@NotNull
	private Long balance = 0l;

	/** 冻结金额 */
	@NotNull
	private Long freeze = 0l;
	
	/** 状态 */
	@NotNull
	@Enumerated(EnumType.STRING)
	private AccountStatusEnum status = AccountStatusEnum.ENABLE;

	/** 内部备注 */
	@NotEmpty
	@Size(max = 128)
	private String memo;

	/** 外部备注 */
	@NotEmpty
	@Size(max = 128)
	private String comments;

	/** 扩展字段json */
	@Size(max = 1024)
	private String extContextJson;

	/** 最后变动记录id */
	private Long lastChangeId;

	/** 上级用户id */
	private String parentUserId;

	/** 查询归类(上级userid,....,自身userid) */
	private String searchPath;

	/**
	 * 可用余额：余额 - 冻结金额
	 */
	@Transient
	public Long getAvailable() {
		return getBalance() - getFreeze();
	}

}
