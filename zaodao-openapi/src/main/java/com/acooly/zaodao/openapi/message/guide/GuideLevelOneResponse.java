package com.acooly.zaodao.openapi.message.guide;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 导游提交一级审核
 *
 * @author xiaohong
 * @create 2018-05-22 11:45
 **/
@Data
@OpenApiMessage(service = "guideLevelOne", type = ApiMessageType.Response)
public class GuideLevelOneResponse  extends ApiResponse {
}
