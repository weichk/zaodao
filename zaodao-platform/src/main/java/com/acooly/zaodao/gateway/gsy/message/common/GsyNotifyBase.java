/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 20:32 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message.common;

import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.gateway.base.NotifyBase;
import com.acooly.zaodao.gateway.gsy.message.enums.TradeStatusEnum;
import com.acooly.zaodao.gateway.gsy.message.enums.TradeTypeEnum;
import lombok.Data;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class GsyNotifyBase extends NotifyBase{
    @OpenApiField(desc = "交易名称", constraint = "交易名称")
    private String tradeName;

    @OpenApiField(desc = "支付交易号", constraint = "支付交易号", demo = "O00116110421302701800001")
    private String tradeNo;

    @OpenApiField(desc = "付款人UserId", constraint = "付款人UserId", demo = "16102516453501800000")
    private String payerUserId;

    @OpenApiField(desc = "收款人id", constraint = "收款人id", demo = "16102517212801800000")
    private String payeeUserId;

    @OpenApiField(desc = "交易金额", constraint = "交易金额，单位：元", demo = "55.00")
    private Money amount;

    @OpenApiField(desc = "交易类型", constraint = "交易类型", demo = "NET_DEPOSIT_PAY")
    private TradeTypeEnum tradeType;

    @OpenApiField(desc = "交易状态", constraint = "交易状态")
    private TradeStatusEnum tradeStatus;

    @OpenApiField(desc = "交易时间", constraint = "交易时间,格式：yyyy-MM-dd HH:mm:ss")
    private String tradeTime;
}
