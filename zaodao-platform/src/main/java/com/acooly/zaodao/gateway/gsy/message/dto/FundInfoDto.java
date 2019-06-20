package com.acooly.zaodao.gateway.gsy.message.dto;

import com.acooly.core.utils.Money;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaohong
 * @create 2018-06-07 9:42
 **/
@Data
public class FundInfoDto implements Serializable {
    /**
     * 商户订单号
     */
    private String merchOrderNo;

    /**
     * 交易金额
     */
    private String amount;

    /**
     * 交易后余额
     */
    private String balance;

    /**
     * 实际到账金额
     */
    private String actualAmount;

    /**
     * 交易状态
     */
    private String status;

    /**
     * 交易状态描述
     */
    private String statusText;

    /**
     * 交易时间
     */
    private String tradeTime;

    /***
     * 交易完成时间
     */
    private String finishTime;

    /**
     * 交易备注
     */
    private String tradeMemo;
}
