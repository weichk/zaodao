package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.core.utils.Money;
import com.acooly.zaodao.gateway.base.ResponseBase;
import com.acooly.zaodao.gateway.gsy.message.enums.ProfitStatus;
import com.acooly.zaodao.gateway.gsy.message.enums.TradeStatusEnum;
import com.acooly.zaodao.gateway.gsy.message.enums.TradeTypeEnum;
import lombok.Data;

/**
 * 观世宇余额支付响应
 * @author xiaohong
 * @create 2017-11-28 20:24
 **/
@Data
public class BalancePayResponse extends ResponseBase {
    /**
     * 分润金额
     */
    private Money shareProfitAmount;
    /**
     * 清分金额
     */
    private Money profitAmount;
    /**
     * 计费分润状态
     */
    private ProfitStatus profitStatus;

    /**
     * 交易名称
     */
    private String tradeName;

    /**
     * 付款人商户用户id
     */
    private String payerMerchUserId;

    /**
     * 收款商户用户id
     */
    private String payeeMerchUserId;

    /**
     * 交易金额
     */
    private Money amount;

    /**
     * 交易类型
     */
    private TradeTypeEnum tradeType;

    /**
     * 交易状态
     */
    private TradeStatusEnum tradeStatus;

    /**
     * 交易时间
     */
    private String tradeTime;

    /**
     * 交易完成时间
     */
    private String finishTime;
}
