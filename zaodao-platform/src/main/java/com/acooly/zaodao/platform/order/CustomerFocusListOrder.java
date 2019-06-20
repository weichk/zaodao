package com.acooly.zaodao.platform.order;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 关注/粉丝列表请求
 *
 * @author xiaohong
 * @create 2017-10-31 9:31
 **/
@Data
public class CustomerFocusListOrder extends PageOrder {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;

    @NotNull
    @OpenApiField(desc = "关注类型", constraint = "关注类型", demo = "0-粉丝;1-我关注的人")
    private int focusFlag;
}
