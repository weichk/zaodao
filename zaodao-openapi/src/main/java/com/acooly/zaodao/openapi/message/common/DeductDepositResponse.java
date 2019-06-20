/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 14:56 创建
 *
 */
package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "deductDeposit", type = ApiMessageType.Response)
public class DeductDepositResponse extends ApiResponse{
    @OpenApiField(desc = "二维码http路径", constraint = "二维码http路径")
    private String codeUrl;

    @OpenApiField(desc = "二维码链接内容", constraint = "二维码链接内容")
    private String codeUrlContent;
}
