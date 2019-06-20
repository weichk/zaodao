package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author xiaohong
 * @create 2017-10-30 9:57
 **/
@Data
public class TravelVoiceDetailOrder extends OrderBase {
    @NotNull
    @OpenApiField(desc = "旅声ID", constraint = "旅声ID")
    private Long travelVoiceId;

    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;
}
