package com.acooly.zaodao.gateway.gsy.message.dto;

import com.acooly.core.utils.Money;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 账户信息
 */
@Getter
@Setter
public class AccountInfo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7668348305342277648L;

    /**
     * 会员userId
     */
    private String userId;

    /**
     * 账户余额
     */
    private Money balance;

    /**
     * 冻结金额
     */
    private Money freeze;

    /**
     * 有效余额
     */
    private Money effectiveBalance;
}
