package com.acooly.zaodao.openapi.message.customer;

import com.acooly.openapi.framework.common.annotation.OpenApiMessage;
import com.acooly.openapi.framework.common.enums.ApiMessageType;
import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.zaodao.openapi.message.dto.CustomerFocusInfoDto;
import lombok.Data;

/**
 * 用户关注/粉丝列表响应
 *
 * @author xiaohong
 * @create 2017-10-31 9:26
 **/
@Data
@OpenApiMessage(service = "customerFocusList", type = ApiMessageType.Response)
public class CustomerFocusListResponse extends PageApiResponse<CustomerFocusInfoDto> {
}
