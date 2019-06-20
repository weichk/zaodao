package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.zaodao.platform.enums.RewardTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by xiaohong on 2017/9/27.
 */
@Data
public class RewardOrder extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "用户唯一标识", constraint = "用户唯一标识")
    private String userId;

    @NotNull
    @OpenApiField(desc = "业务管理ID", constraint = "业务管理ID")
    private String businessId;

    @NotNull
    @OpenApiField(desc = "打赏类型", constraint = "打赏类型")
    private RewardTypeEnum type;

    @NotNull
    @OpenApiField(desc = "打赏积分数", constraint = "打赏积分数")
    private Long point;
}
