package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户关注个数
 *
 * @author xiaohong
 * @create 2017-10-30 18:04
 **/
@Data
public class CustomerFocusCountOrder extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;
}
