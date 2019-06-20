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
import com.acooly.zaodao.account.entity.Account;
import com.acooly.zaodao.account.enums.AccountStatusEnum;
import com.acooly.zaodao.account.service.AccountService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * act_account 管理控制器
 *
 * @author acooly
 * Date: 2018-05-27 21:03:46
 */
@Controller
@RequestMapping(value = "/manage/account/account")
public class AccountManagerController extends AbstractJQueryEntityController<Account, AccountService> {


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
    private AccountService accountService;


    @Override
    protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
        model.put("allStatus", AccountStatusEnum.maps());
    }

    /**
     * 导出标题
     */
    @Override
    protected List<String> getExportTitles() {
        return Lists.newArrayList(new String[]{"编号", "会员ID", "会员名", "账户类型", "账户余额(元)", "冻结金额(元)", "可用余额(元)", "状态", "备注", "创建时间", "修改时间"});
    }

    /**
     * 导出处理
     */
    @Override
    protected List<String> doExportEntity(Account entity) {
        List<String> row = Lists.newArrayList();
        row.add(String.valueOf(entity.getId()));
        row.add(entity.getUserId());
        row.add(entity.getUserName());
        row.add(allAccountTypes.get(entity.getAccountType()));
        row.add(Money.cent(entity.getBalance()).toCNString());
        row.add(Money.cent(entity.getFreeze()).toCNString());
        row.add(Money.cent(entity.getAvailable()).toCNString());
        row.add(entity.getStatus().getCode());
        row.add(entity.getMemo());
        row.add(Dates.format(entity.getCreateTime()));
        row.add(Dates.format(entity.getUpdateTime()));
        return row;
    }
}
