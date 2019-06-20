package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;

/**
 * 消息个数
 *
 * @author xiaohong
 * @create 2017-11-08 16:42
 **/
@Data
@OpenApiMessage(service = "messageCount", type = ApiMessageType.Response)
public class MessageCountResponse extends ApiResponse {
    @OpenApiField(desc = "消息个数", constraint = "消息个数")
    private Integer messageCount;
}
