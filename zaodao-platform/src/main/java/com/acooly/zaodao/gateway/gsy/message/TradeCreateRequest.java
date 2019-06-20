/*
 * 修订记录:
 * zhike@yiji.com 2017-08-08 10:57 创建
 *
 */
package com.acooly.zaodao.gateway.gsy.message;

import com.acooly.core.utils.Money;
import com.acooly.zaodao.gateway.base.RequestBase;
import com.acooly.zaodao.gateway.gsy.message.dto.TradeCustomerInfo;
import com.acooly.zaodao.gateway.gsy.message.enums.TradeProfitTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
public class TradeCreateRequest extends RequestBase{

    /**
     * 	付款人信息
     */
    private TradeCustomerInfo tradeCustomerInfo;

    /**
     * 交易名称
     */
    @NotBlank
    private String tradeName;

    /**
     *卖家id
     */
    @NotBlank
    private String sellerUserId;

    /** 清分类型:{手动,自动} */
    private TradeProfitTypeEnum tradeProfitType = TradeProfitTypeEnum.AUTO;

    /** 交易金额 */
    @NotNull
    private Money amount;

    /** 交易时间 */
    @NotBlank
    private Date tradeTime;

    /** 交易备注 **/
    @Size(max = 128)
    private String tradeMemo;
}
