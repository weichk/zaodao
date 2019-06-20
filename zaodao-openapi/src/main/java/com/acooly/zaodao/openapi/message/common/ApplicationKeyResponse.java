package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.zaodao.openapi.message.dto.ApplicationKeyDto;
import lombok.Data;

/**
 * App关键字
 *
 * @author xiaohong
 * @create 2018-06-14 9:54
 **/
@Data
@OpenApiMessage(service = "applicationKey", type = ApiMessageType.Response)
public class ApplicationKeyResponse extends ApiResponse {

    @OpenApiField(desc = "App关键字", constraint = "App关键字")
    private ApplicationKeyDto applicationKeyDto;
}
