package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 订单详情
 *
 * @author xiaohong
 * @create 2017-11-22 18:21
 **/
@Data
public class PlatOrderDetailOrder extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "订单号", constraint = "订单号")
    private String platOrderNo;
}
