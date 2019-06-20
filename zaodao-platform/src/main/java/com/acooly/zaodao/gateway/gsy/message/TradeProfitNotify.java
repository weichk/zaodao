package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.gateway.base.NotifyBase;
import lombok.Data;

/**
 * @author cuifuqiang
 *         Date:2017-5-11.
 */
@Data
public class TradeProfitNotify extends NotifyBase{

    @OpenApiField(desc = "清分金额", constraint = "清分金额")
    private String profitAmount = "0";

    @OpenApiField(desc = "分润金额", constraint = "分润金额")
    private String shareProfitAmount = "0";

    @OpenApiField(desc = "计费分润状态", constraint = "计费分润状态")
    private String profitStatus = "PROFIT_INIT";

    @OpenApiField(desc = "交易名称", constraint = "交易名称")
    private String tradeName;

    @OpenApiField(desc = "支付交易号", constraint = "支付交易号", demo = "O00116110421302701800001")
    private String tradeNo;

    @OpenApiField(desc = "付款人UserId", constraint = "付款人UserId", demo = "16102516453501800000")
    private String payerUserId;

    @OpenApiField(desc = "收款人id", constraint = "收款人id", demo = "16102517212801800000")
    private String payeeUserId;

    @OpenApiField(desc = "交易金额", constraint = "交易金额，单位：元", demo = "55.00")
    private String amount;

    @OpenApiField(desc = "交易类型", constraint = "交易类型", demo = "NET_DEPOSIT_PAY")
    private String tradeType;

    @OpenApiField(desc = "交易状态", constraint = "交易状态")
    private String tradeStatus;

    @OpenApiField(desc = "交易时间", constraint = "交易时间,格式：yyyy-MM-dd HH:mm:ss")
    private String tradeTime;
}
