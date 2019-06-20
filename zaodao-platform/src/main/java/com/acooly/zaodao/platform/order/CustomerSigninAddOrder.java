package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户签到
 *
 * @author xiaohong
 * @create 2017-11-03 11:36
 **/
@Data
public class CustomerSigninAddOrder extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;
}
