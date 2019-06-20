package com.acooly.zaodao.platform.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.gateway.gsy.message.enums.TradeTypeEnum;
import com.acooly.zaodao.platform.enums.InOutType;
import com.acooly.zaodao.platform.enums.TradingType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Date;

/**
 * 交易明细
 *
 * @author xiaohong
 * @create 2017-11-24 10:17
 **/
@Data
public class TradingRecordDto implements Serializable {
    /**
     * 交易记录ID
     */
    private Long tradingRecordId;

    /**
     * 交易金额
     */
    private Long tradingAmount = 0l;

    /**
     * 余额
     */
    private Long balance = 0l;

    /**
     * 交易类型(旅游,充值,提现,钙片交易)
     */
    private TradingType tradingType;

    /**
     * 支付宝,微信,账户余额
     */
    private String tradeMethod;

    /**
     * 交易时间
     */
    private Date createTime;

    /**
     * 出入金类型
     */
    private InOutType inOutType;
}
