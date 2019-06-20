/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 15:43 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.gateway.base.ResponseBase;
import com.acooly.zaodao.gateway.gsy.message.enums.FundStatusEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class WithdrawResponse extends ResponseBase{

    @Length(max = 32)
    @OpenApiField(desc = "用户UserId", constraint = "用户UserId", demo = "16103115491603800000")
    private String merchUserId;

    @OpenApiField(desc = "交易金额", constraint = "交易金额，单位：元", demo = "12.00")
    private Money amount = Money.cent(0);

    @OpenApiField(desc = "收费金额", constraint = "收费金额", demo = "2.00")
    private Money chargeAmount = Money.cent(0);

    @OpenApiField(desc = "实际到账金额", constraint = "实际到账金额", demo = "12.00")
    private Money actualAmount = Money.cent(0);

    @OpenApiField(desc = "交易时间", constraint = "交易时间,格式：yyyy-MM-dd HH:mm:ss")
    private String tradeTime;

    @OpenApiField(desc = "交易订单状态 ", constraint = "交易订单状态", demo = "PROCESSING")
    private FundStatusEnum fundStatus;
}
