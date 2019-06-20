package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 屏蔽用户
 *
 * @author xiaohong
 * @create 2018-01-10 10:23
 **/
@Data
public class CustomerIngoreOrder extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "被屏蔽用户ID", constraint = "被屏蔽用户ID")
    private String ingoreUserId;
}
