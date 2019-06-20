package com.acooly.zaodao.openapi.message.course;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * Created by okobboko on 2017/10/12.
 */
@Data
@OpenApiMessage(service = "coursePurchase", type = ApiMessageType.Response)
public class CoursePurchaseResponse extends ApiResponse {
    @OpenApiField(desc = "课程购买ID", constraint = "课程购买ID")
    private Long coursePurchaseId;
}
