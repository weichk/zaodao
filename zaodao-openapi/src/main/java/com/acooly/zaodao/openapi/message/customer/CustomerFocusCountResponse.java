package com.acooly.zaodao.openapi.message.customer;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户关注个数响应
 *
 * @author xiaohong
 * @create 2017-10-30 17:58
 **/
@Data
@OpenApiMessage(service = "customerFocusCount", type = ApiMessageType.Response)
public class CustomerFocusCountResponse extends ApiResponse {
    @OpenApiField(desc = "关注个数", constraint = "关注个数")
    private int count;

    @OpenApiField(desc = "粉丝数", constraint = "粉丝数")
    private int focusCount;
}
