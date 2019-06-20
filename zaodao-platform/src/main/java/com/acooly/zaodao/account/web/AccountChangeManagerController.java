/*
* acooly.cn Inc.
* Copyright (c) 2018 All Rights Reserved.
* create by acooly
* date:2018-05-27
*/
package com.acooly.zaodao.account.web;


import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.core.utils.Dates;
import com.acooly.core.utils.Money;
import com.acooly.zaodao.account.entity.AccountChange;
import com.acooly.zaodao.account.enums.AccountChangeTypeEnum;
import com.acooly.zaodao.account.enums.AccountOrderStatusEnum;
import com.acooly.zaodao.account.enums.AccountTransferTypeEnum;
import com.acooly.zaodao.account.service.AccountChangeService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * act_account_change 管理控制器
 * 
 * @author acooly
 * Date: 2018-05-27 21:03:26
 */
@Controller
@RequestMapping(value = "/manage/account/accountChange")
public class AccountChangeManagerController extends AbstractJQueryEntityController<AccountChange, AccountChangeService> {
	

	{
		allowMapping = "*";
	}
	private static Map<String, String> allAccountTypes = Maps.newLinkedHashMap();
	static {
		allAccountTypes.put("MAIN", "主账户");
		allAccountTypes.put("NORMAL", "通用冻结账户");
		allAccountTypes.put("SPECIFY", "特殊冻结账户");
	}

	@SuppressWarnings("unused")
	@Autowired
	private AccountChangeService accountChangeService;

	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allChangeTypes", AccountChangeTypeEnum.maps());
		model.put("allAccountOrderStatus", AccountOrderStatusEnum.maps());
		model.put("allTransferTypes", AccountTransferTypeEnum.maps());
		model.put("allAccountTypes", allAccountTypes);
	}

	/**
	 * 导出标题
	 */
	@Override
	protected List<String> getExportTitles() {
		return Lists.newArrayList(new String[]{"编号", "会员ID", "会员名", "账户号", "账户类型", "交易金额(元)",
				"交易后余额(元)", "订单编号", "流水号", "变更类型", "交易会员ID", "交易用户名","状态", "备注", "创建时间", "修改时间"});
	}

	/**
	 * 导出处理
	 */
	@Override
	protected List<String> doExportEntity(AccountChange entity) {
		List<String> row = Lists.newArrayList();
		row.add(String.valueOf(entity.getId()));
		row.add(entity.getUserId());
		row.add(entity.getUserName());
		row.add(entity.getAccountNo());
		row.add(allAccountTypes.get(entity.getAccountType()));
		row.add(Money.cent(entity.getAmount()).toCNString());
		row.add(Money.cent(entity.getBalance()).toCNString());
		row.add(entity.getBusinessId());
		row.add(entity.getOrderNo());
		row.add(entity.getChangeType().getCode());
		row.add(entity.getRefUserId());
		row.add(entity.getRefUserName());
		row.add(entity.getStatus().getCode());
		row.add(entity.getMemo());
		row.add(Dates.format(entity.getCreateTime()));
		row.add(Dates.format(entity.getUpdateTime()));
		return row;
	}

}
