/*
 * 修订记录:
 * zhike@yiji.com 2017-05-24 11:01 创建
 *
 */
package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@OpenApiMessage(service = "zdModifyPassword", type = ApiMessageType.Response)
@Data
public class ZdModifyPasswordResponse extends ApiResponse{
}
