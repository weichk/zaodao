package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 修改订单金额
 *
 * @author xiaohong
 * @create 2018-05-08 17:47
 **/
@Data
public class AmountModifyOrder extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "订单号", constraint = "订单号", demo = "O00117052610240611600000")
    private String platOrderNo;

    @NotNull
    @OpenApiField(desc = "订单金额", constraint = "修改后的订单金额")
    private Money orderAmount;
}
