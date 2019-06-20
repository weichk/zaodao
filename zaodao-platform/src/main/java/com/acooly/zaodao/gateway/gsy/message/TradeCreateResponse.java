/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 10:57 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.core.utils.Money;
import com.acooly.zaodao.gateway.base.ResponseBase;
import com.acooly.zaodao.gateway.gsy.message.enums.TradeStatusEnum;
import com.acooly.zaodao.gateway.gsy.message.enums.TradeTypeEnum;
import lombok.Data;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class TradeCreateResponse extends ResponseBase{
    /**
     * 交易名称
     */
    private String tradeName;

    /**
     * 支付交易号
     */
    private String tradeNo;

    /**
     * 付款人UserId
     */
    private String payerUserId;

    /**
     * 收款人id
     */
    private String payeeUserId;

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
