/*
 * www.yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-04-11 20:25 创建
 */
package com.acooly.zaodao.account.info;

import com.acooly.core.utils.Money;

/**
 * 客户账户常用统计数据 dto
 *
 * @author zhangpu
 */
public class AccountStatisticsInfo {

	/**
	 * 散标投资额
	 */
	private Money principalTotal = new Money(0);

	/**
	 * 散标回款本金金额
	 */
	private Money principalReceived = new Money(0);

	/**
	 * 散标应收利息
	 */
	private Money interestTotal = new Money(0);
	/**
	 * 散标已回利息
	 */
	private Money interestReceived = new Money(0);

	/**
	 * 散标应付服务费
	 */
	private Money borrowServiceFee = new Money(0);

	/**
	 * 散标已付服务费
	 */
	private Money borrowServiceFeePayed = new Money(0);


	/** 散标债权转出总额 */
	private Money outPrincipalSaleTotal = new Money(0);
	/** 散标债权转出利息 */
	private Money outInterestSaleTotal = new Money(0);
	/** 散标债权转出利息服务费 */
	private Money outInterestSaleServiceFee = new Money(0);

	/** 债转标债权转出总额 */
	private Money outDebtPrincipalSaleTotal = new Money(0);
	/** 债转标债权转出利息 */
	private Money outDebtInterestSaleTotal = new Money(0);
	/** 债转标债权转出利息服务费 */
	private Money outDebtInterestSaleServiceFee = new Money(0);


	/** 债权转入总额 */
	private Money inPrincipalSaleTotal = new Money(0);
	/** 债权转入利息 */
	private Money inInterestSaleTotal = new Money(0);
	/** 债权转入利息应付服务费 */
	private Money inInterestSaleServiceFee = new Money(0);
	/** 债权转入利息已付服务费 */
	private Money claimServiceFeePayed = new Money(0);
	/**
	 * 债转回款本金金额
	 */
	private Money claimPrincipalReceived = new Money(0);
	/**
	 * 债转已回利息
	 */
	private Money claimInterestReceived = new Money(0);

	/**
	 * 推荐佣金收入总额 一度+二度
	 */
	private Money commissionTotal = new Money(0);


	/**
	 * 充值总额 充值+离线充值
	 */
	private Money depositTotal = new Money(0);
	/**
	 * 提现总额 提现+离线提现
	 */
	private Money withdrawTotal = new Money(0);

	/**
	 * 我的债权总资产 待收本金+待收利息
	 */
	public Money getTotalAsset() {
		return this.getPrincipal().add(this.getInterest());
	}

	/**
	 * 累计投资收益 散标已收利息+债权买入已收利息
	 */
	public Money getTotalIncome() {
		return this.interestReceived.add(this.claimInterestReceived);
	}

	/**
	 * 累计投资投资金额 投资总额+债权买入总额
	 */
	public Money getTotalInvest() {
		return this.principalTotal.add(this.inPrincipalSaleTotal);
	}

	/**
	 * 待收本金 散标投资额+债权买入-散标回款本金金额-散标债权卖出本金-债转回款本金金额-债转标债权卖出
	 */
	public Money getPrincipal() {
		return this.principalTotal
				.subtract(this.principalReceived)
				.subtract(this.outPrincipalSaleTotal)
				.add(inPrincipalSaleTotal)
				.subtract(this.claimPrincipalReceived)
				.subtract(this.outDebtPrincipalSaleTotal);
	}

	/**
	 * 待收利息 总利息+债权买入利息-散标已收利息-散标债权卖出利息-债转已回利息-债转标债权卖出利息
	 */
	public Money getInterest() {
		return this.interestTotal.add(this.inInterestSaleTotal)
				.subtract(this.interestReceived)
				.subtract(this.outInterestSaleTotal).subtract(this.claimInterestReceived)
				.subtract(this.outDebtInterestSaleTotal);
	}

	public Money getPrincipalTotal() {
		return principalTotal;
	}

	public void setPrincipalTotal(Money principalTotal) {
		this.principalTotal = principalTotal;
	}

	public Money getPrincipalReceived() {
		return principalReceived;
	}

	public void setPrincipalReceived(Money principalReceived) {
		this.principalReceived = principalReceived;
	}

	public Money getInterestTotal() {
		return interestTotal;
	}

	public void setInterestTotal(Money interestTotal) {
		this.interestTotal = interestTotal;
	}

	public Money getInterestReceived() {
		return interestReceived;
	}

	public void setInterestReceived(Money interestReceived) {
		this.interestReceived = interestReceived;
	}

	public Money getCommissionTotal() {
		return commissionTotal;
	}

	public void setCommissionTotal(Money commissionTotal) {
		this.commissionTotal = commissionTotal;
	}

	public Money getOutDebtPrincipalSaleTotal() {
		return outDebtPrincipalSaleTotal;
	}

	public void setOutDebtPrincipalSaleTotal(Money outDebtPrincipalSaleTotal) {
		this.outDebtPrincipalSaleTotal = outDebtPrincipalSaleTotal;
	}

	public Money getOutDebtInterestSaleTotal() {
		return outDebtInterestSaleTotal;
	}

	public void setOutDebtInterestSaleTotal(Money outDebtInterestSaleTotal) {
		this.outDebtInterestSaleTotal = outDebtInterestSaleTotal;
	}

	public Money getOutDebtInterestSaleServiceFee() {
		return outDebtInterestSaleServiceFee;
	}

	public void setOutDebtInterestSaleServiceFee(Money outDebtInterestSaleServiceFee) {
		this.outDebtInterestSaleServiceFee = outDebtInterestSaleServiceFee;
	}

	public Money getDepositTotal() {
		return depositTotal;
	}

	public void setDepositTotal(Money depositTotal) {
		this.depositTotal = depositTotal;
	}

	public Money getWithdrawTotal() {
		return withdrawTotal;
	}

	public void setWithdrawTotal(Money withdrawTotal) {
		this.withdrawTotal = withdrawTotal;
	}

	public Money getOutPrincipalSaleTotal() {
		return outPrincipalSaleTotal;
	}

	public void setOutPrincipalSaleTotal(Money outPrincipalSaleTotal) {
		this.outPrincipalSaleTotal = outPrincipalSaleTotal;
	}

	public Money getOutInterestSaleTotal() {
		return outInterestSaleTotal;
	}

	public void setOutInterestSaleTotal(Money outInterestSaleTotal) {
		this.outInterestSaleTotal = outInterestSaleTotal;
	}

	public Money getInPrincipalSaleTotal() {
		return inPrincipalSaleTotal;
	}

	public void setInPrincipalSaleTotal(Money inPrincipalSaleTotal) {
		this.inPrincipalSaleTotal = inPrincipalSaleTotal;
	}

	public Money getInInterestSaleTotal() {
		return inInterestSaleTotal;
	}

	public void setInInterestSaleTotal(Money inInterestSaleTotal) {
		this.inInterestSaleTotal = inInterestSaleTotal;
	}

	public Money getBorrowServiceFee() {
		return borrowServiceFee;
	}

	public void setBorrowServiceFee(Money borrowServiceFee) {
		this.borrowServiceFee = borrowServiceFee;
	}

	public Money getBorrowServiceFeePayed() {
		return borrowServiceFeePayed;
	}

	public void setBorrowServiceFeePayed(Money borrowServiceFeePayed) {
		this.borrowServiceFeePayed = borrowServiceFeePayed;
	}

	public Money getOutInterestSaleServiceFee() {
		return outInterestSaleServiceFee;
	}

	public void setOutInterestSaleServiceFee(Money outInterestSaleServiceFee) {
		this.outInterestSaleServiceFee = outInterestSaleServiceFee;
	}

	public Money getInInterestSaleServiceFee() {
		return inInterestSaleServiceFee;
	}

	public void setInInterestSaleServiceFee(Money inInterestSaleServiceFee) {
		this.inInterestSaleServiceFee = inInterestSaleServiceFee;
	}

	public Money getClaimServiceFeePayed() {
		return claimServiceFeePayed;
	}

	public void setClaimServiceFeePayed(Money claimServiceFeePayed) {
		this.claimServiceFeePayed = claimServiceFeePayed;
	}

	public Money getClaimPrincipalReceived() {
		return claimPrincipalReceived;
	}

	public void setClaimPrincipalReceived(Money claimPrincipalReceived) {
		this.claimPrincipalReceived = claimPrincipalReceived;
	}

	public Money getClaimInterestReceived() {
		return claimInterestReceived;
	}

	public void setClaimInterestReceived(Money claimInterestReceived) {
		this.claimInterestReceived = claimInterestReceived;
	}
}
