package com.acooly.zaodao.openapi.message.common;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.CustomerCardInfoDto;
import lombok.Data;

/**
 * @author xiaohong
 * @create 2017-11-24 19:08
 **/
@Data
@OpenApiMessage(service = "bankCardList", type = ApiMessageType.Response)
public class BankCardListResponse extends PageApiResponse<CustomerCardInfoDto> {
}
