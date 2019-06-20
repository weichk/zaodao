package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.gateway.base.NotifyBase;
import com.acooly.zaodao.gateway.gsy.message.enums.FundStatusEnum;
import lombok.Data;

/**
 * 提现到卡(汇付到卡)
 *
 * @author xiaohong
 * @create 2018-06-06 10:31
 **/
@Data
public class WithdrawCardNotify extends NotifyBase {

    @OpenApiField(desc = "用户UserId", constraint = "用户UserId", demo = "16103115491603800000")
    private String merchUserId;

    @OpenApiField(desc = "交易金额", constraint = "交易金额，单位：元", demo = "12.00")
    private String amount;

    @OpenApiField(desc = "收费金额", constraint = "收费金额", demo = "2.00")
    private String chargeAmount;

    @OpenApiField(desc = "实际到账金额", constraint = "实际到账金额", demo = "12.00")
    private String actualAmount;

    @OpenApiField(desc = "交易时间", constraint = "交易时间,格式：yyyy-MM-dd HH:mm:ss")
    private String tradeTime;

    @OpenApiField(desc = "交易备注", constraint = "交易备注")
    private String tradeMemo;

    @OpenApiField(desc = "交易订单状态 ", constraint = "交易订单状态", demo = "PROCESSING")
    private String fundStatus;
}
