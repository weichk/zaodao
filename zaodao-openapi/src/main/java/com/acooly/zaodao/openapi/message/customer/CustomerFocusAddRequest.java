package com.acooly.zaodao.openapi.message.customer;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 用户关注请求
 *
 * @author xiaohong
 * @create 2017-10-30 17:38
 **/
@Data
@OpenApiMessage(service = "customerFocusAdd", type = ApiMessageType.Request)
public class CustomerFocusAddRequest extends ApiRequest {
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
