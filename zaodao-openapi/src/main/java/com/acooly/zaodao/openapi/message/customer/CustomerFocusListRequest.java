package com.acooly.zaodao.openapi.message.customer;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.NotNull;

/**
 * 用户关注/粉丝列表请求
 *
 * @author xiaohong
 * @create 2017-10-31 9:25
 **/
@Data
@OpenApiMessage(service = "customerFocusList", type = ApiMessageType.Request)
public class CustomerFocusListRequest extends PageApiRequest {
    @NotBlank
    @OpenApiField(desc = "用户ID", constraint = "用户ID")
    private String userId;

    @NotNull
    @OpenApiField(desc = "关注类型", constraint = "关注类型", demo = "0-粉丝;1-我关注的人")
    private int focusFlag;
}
