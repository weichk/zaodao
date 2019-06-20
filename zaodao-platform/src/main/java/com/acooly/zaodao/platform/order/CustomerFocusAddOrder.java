package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 关注用户
 *
 * @author xiaohong
 * @create 2017-10-30 17:46
 **/
@Data
public class CustomerFocusAddOrder extends OrderBase {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;

    @NotBlank
    @OpenApiField(desc = "被关注用户ID", constraint = "被关注用户ID")
    private String focusUserId;

    @NotNull
    @OpenApiField(desc = "关注类型", constraint = "关注类型", demo = "0-取消关注; 1-关注")
    private Integer customerFocusFlag;
}
