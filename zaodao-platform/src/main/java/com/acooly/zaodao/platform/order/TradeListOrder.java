package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.TradingType;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * @author xiaohong
 * @create 2017-11-24 10:11
 **/
@Data
public class TradeListOrder extends PageOrder {
    @NotBlank
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识", demo = "O00117052610240611600000")
    private String userId;

    @OpenApiField(desc = "开始时间", constraint = "开始时间", demo = "2017-11-10")
    private Date startTime;

    @OpenApiField(desc = "结束时间", constraint = "结束时间", demo = "2017-11-15")
    private Date endTime;

    @OpenApiField(desc = "收支类型", constraint = "收支类型")
    private TradingType tradingType;
}
