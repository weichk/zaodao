/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 20:38 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.gateway.base.NotifyBase;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class WithdrawNotify extends NotifyBase{
    @OpenApiField(desc = "交易订单号 ", constraint = "交易订单号", demo = "O00116110620463801800001")
    private String tradeNo;

    @Length(max = 32)
    @OpenApiField(desc = "用户UserId", constraint = "用户UserId", demo = "16103115491603800000")
    private String userId;

    @OpenApiField(desc = "交易金额", constraint = "交易金额，单位：元", demo = "12.00")
    private String amount;

    @OpenApiField(desc = "收费金额", constraint = "收费金额", demo = "2.00")
    private String chargeAmount;

    @OpenApiField(desc = "实际到账金额", constraint = "实际到账金额", demo = "12.00")
    private String actualAmount;

    @OpenApiField(desc = "交易时间", constraint = "交易时间,格式：yyyy-MM-dd HH:mm:ss")
    private String tradeTime;

    @OpenApiField(desc = "交易备注", constraint = "交易备注", demo = "测试网银充值")
    private String tradeMemo;

    @OpenApiField(desc = "交易订单状态 ", constraint = "交易订单状态", demo = "PROCESSING")
    private String fundStatus;
}
