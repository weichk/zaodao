package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 订单完成
 *
 * @author xiaohong
 * @create 2017-12-11 14:07
 **/
@Data
public class PlatOrderFinishOrder  extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "订单号", constraint = "订单号")
    private String platOrderNo;
}
