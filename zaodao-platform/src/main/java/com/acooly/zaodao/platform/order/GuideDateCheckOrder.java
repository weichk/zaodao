package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 检查订单约导
 *
 * @author xiaohong
 * @create 2017-11-21 14:19
 **/
@Data
public class GuideDateCheckOrder extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "导游ID", constraint = "导游ID")
    private String guideUserId;

    @NotNull
    @OpenApiField(desc = "开始时间", constraint = "开始时间")
    private Date startTime;

    @NotNull
    @OpenApiField(desc = "结束时间", constraint = "结束时间")
    private Date endTime;
}
