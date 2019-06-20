/*
 * 修订记录:
 * zhike@yiji.com 2017-05-27 11:12 创建
 *
 */
package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.MessageInfoDto;
import lombok.Data;

/**
 * 修订记录：
 *
 * @author zhike@yiji.com
 */
@Data
@OpenApiMessage(service = "messageList", type = ApiMessageType.Response)
public class MessageListResponse extends PageApiResponse<MessageInfoDto> {

}
