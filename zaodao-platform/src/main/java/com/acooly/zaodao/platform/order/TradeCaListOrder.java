package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * 钙片明细列表
 *
 * @author xiaohong
 * @create 2017-11-29 19:31
 **/
@Data
public class TradeCaListOrder extends PageOrder {
    @NotBlank
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识", demo = "O00117052610240611600000")
    private String userId;

    @OpenApiField(desc = "开始时间", constraint = "开始时间", demo = "2017-11-10")
    private Date startTime;

    @OpenApiField(desc = "结束时间", constraint = "结束时间", demo = "2017-11-15")
    private Date endTime;

}
