/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 17:39 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.gateway.base.ResponseBase;
import com.acooly.zaodao.gateway.gsy.message.enums.CardStatus;
import lombok.Data;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class SignCardResponse extends ResponseBase {
    /** 用户ID*/
    @OpenApiField(desc = "用户ID", constraint = "用户ID",demo="16110717564501800000")
    private String merchUserId;

    /** 签约流水号*/
    @OpenApiField(desc = "签约流水号", constraint = "签约流水号",demo="16110717564501800000")
    private String bindId;

    /** 银行名称 */
    @OpenApiField(desc = "银行名称 ", constraint = "银行名称 ",demo="中信银行")
    private String bankName;

    /** 银行简称 */
    @OpenApiField(desc = "银行简称 ", constraint = "银行简称 ",demo="ICBC")
    private String bankCode;

    /** 银行卡产品名称 */
    @OpenApiField(desc = "卡种", constraint = "卡种",demo="DEBIT")
    private String bankCardType;

    /** 绑卡状态 **/
    @OpenApiField(desc = "绑卡状态", constraint = "绑卡状态",demo="APPLY")
    private CardStatus bindStatus;
}
