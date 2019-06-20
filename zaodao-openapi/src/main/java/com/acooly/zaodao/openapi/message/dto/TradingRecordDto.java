package com.acooly.zaodao.openapi.message.dto;

import com.acooly.core.utils.Money;
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
 * @author xiaohong
 * @create 2017-11-24 9:45
 **/
@Data
public class TradingRecordDto implements Serializable{
    @OpenApiField(desc = "交易记录ID", constraint = "交易记录ID")
    private Long tradingRecordId;

    @OpenApiField(desc = "交易金额", constraint = "交易金额", demo = "2000.00")
    private Money tradingAmount;

    @OpenApiField(desc = "余额", constraint = "余额", demo = "1120.00")
    private Money balance;

    @OpenApiField(desc = "交易类型", constraint = "交易类型", demo = "旅游,充值,提现,钙片交易")
    private TradingType tradingType;

    @OpenApiField(desc = "交易方式", constraint = "交易方式", demo = "支付宝,微信,账户余额")
    private String tradeMethod;

    @OpenApiField(desc = "交易时间", constraint = "交易时间", demo = "2017-11-27 19:01:37")
    private Date createTime;

    @OpenApiField(desc = "出入金类型", constraint = "出入金类型", demo = "in=收入,out=支出")
    @Enumerated(EnumType.STRING)
    private InOutType inOutType;
}
