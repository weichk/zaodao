package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.core.utils.Money;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 钙片交易
 *
 * @author xiaohong
 * @create 2017-11-28 20:06
 **/
@Data
public class ExchangeCaTradeOrder extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID", demo = "O00117052610240611600000")
    private String userId;

    @NotNull
    @OpenApiField(desc = "钙片数量", constraint = "钙片数量", demo = "100")
    private Integer caNumber;

    @NotBlank
    @OpenApiField(desc = "用户ip", constraint = "用户ip")
    private String userIp;
}
